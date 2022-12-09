package mft.model.repository;

import mft.model.entity.Book;
import mft.model.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDA implements AutoCloseable{

    private Connection connection;
    private PreparedStatement preparedStatement;

    public BookDA() throws SQLException {
    connection = ConnectionProvider.getConnection();
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

    public void add(Book book) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO BOOK VALUES(BOOK_SEQ.NEXTVAL,?,?,?)");
        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2,book.getWriter());
        preparedStatement.setInt(3,book.getCount());
        preparedStatement.executeUpdate();

    }
    public void edit(Book book) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE BOOK SET NAME=? , WRITER=? , BOOK_COUNT=? WHERE  ID=?");
        preparedStatement.setString(1,book.getName());
        preparedStatement.setString(2,book.getWriter());
        preparedStatement.setInt(3,book.getCount());
        preparedStatement.setInt(4,book.getId());
        preparedStatement.executeUpdate();

    }
    public void remove(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM BOOK WHERE ID = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();

    }
    public List<Book> findAll() throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM BOOK ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> bookList = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setWriter(resultSet.getString("writer"))
                    .setCount(resultSet.getInt("book_count"));
            bookList.add(book);
        }
        return bookList;
    }

    public List<Book> findAllAvailable() throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM BOOK WHERE book_count > 0");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> bookList = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setWriter(resultSet.getString("writer"))
                    .setCount(resultSet.getInt("book_count"));
            bookList.add(book);
        }
        return bookList;
    }

    public Book findById(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM BOOK WHERE ID = ?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
            Book book = new Book()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setWriter(resultSet.getString("writer"))
                    .setCount(resultSet.getInt("book_count"));

        return book;
    }


    public void borrowBook(int bookId) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE BOOK SET BOOK_COUNT = BOOK_COUNT-1 WHERE ID=?");
        preparedStatement.setInt(1,bookId);
        preparedStatement.executeUpdate();

    }

    public void returnBook(int bookId) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE BOOK SET BOOK_COUNT = BOOK_COUNT+1 WHERE ID=?");
        preparedStatement.setInt(1,bookId);
        preparedStatement.executeUpdate();
    }

}
