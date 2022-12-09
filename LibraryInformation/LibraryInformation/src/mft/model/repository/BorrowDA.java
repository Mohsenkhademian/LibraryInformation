package mft.model.repository;

import mft.model.bl.BookBL;
import mft.model.bl.MemberBL;
import mft.model.entity.Borrow;
import mft.model.utils.ConnectionProvider;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BorrowDA implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public BorrowDA() throws SQLException {
        connection = ConnectionProvider.getConnection();
    }

    public void add(Borrow borrow) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO BORROW VALUES(BORROW_SEQ.NEXTVAL,?,?,?,?)");
        preparedStatement.setInt(1,borrow.getMember().getId());
        preparedStatement.setInt(2,borrow.getBook().getId());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
        preparedStatement.setBoolean(4,false);
        preparedStatement.executeUpdate();
    }

    public void edit(Borrow borrow) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE BORROW SET MEMBER_ID=?, BOOK_ID=?, IS_RETURNED=? WHERE ID=?");
        preparedStatement.setInt(1,borrow.getMember().getId());
        preparedStatement.setInt(2,borrow.getBook().getId());
        preparedStatement.setBoolean(3,borrow.isReturned());
        preparedStatement.setInt(4,borrow.getId());
        preparedStatement.executeUpdate();
    }

    public void remove(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM BORROW WHERE ID=?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    public List<Borrow> findAll() throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM BORROW");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Borrow> borrowList = new ArrayList<>();
        while (resultSet.next()) {
            Borrow borrow = new Borrow()
                    .setId(resultSet.getInt("id"))
                    .setMember(MemberBL.findById(resultSet.getInt("member_id")))
                    .setBook(BookBL.findById(resultSet.getInt("book_id")))
                    .setBorromTime(resultSet.getTimestamp("borrow_time").toLocalDateTime())
                    .setReturned(resultSet.getBoolean("is_returned"));
            borrowList.add(borrow);
        }
        return borrowList;
    }

    public Borrow findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM BORROW WHERE ID=?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
            Borrow borrow = new Borrow()
                    .setId(resultSet.getInt("id"))
                    .setMember(MemberBL.findById(resultSet.getInt("member_id")))
                    .setBook(BookBL.findById(resultSet.getInt("book_id")))
                    .setBorromTime(resultSet.getTimestamp("borrow_time").toLocalDateTime())
                    .setReturned(resultSet.getBoolean("is_returned"));
        return borrow;
    }

    public int memberBorrowedBook(int member_id) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS result FROM BORROW WHERE ID=? AND IS_RETURNED=0");
        preparedStatement.setInt(1,member_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("result");
    }

    public void returnBook(int borrowId) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE BORROW SET is_returned=1 WHERE id=?");
        preparedStatement.setInt(1, borrowId);
        preparedStatement.executeUpdate();
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
