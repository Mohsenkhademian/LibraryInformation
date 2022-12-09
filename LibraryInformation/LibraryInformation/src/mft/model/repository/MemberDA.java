package mft.model.repository;

import mft.model.entity.Member;
import mft.model.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDA implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;


    public MemberDA() throws SQLException {
        connection = ConnectionProvider.getConnection();
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

    public void add(Member member) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO MEMBER VALUES(MEMBER_SEQ.NEXTVAL,?,?)");
        preparedStatement.setString(1,member.getName());
        preparedStatement.setString(2,member.getFamily());
        preparedStatement.executeUpdate();
    }

    public void edit(Member member) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE MEMBER SET NAME=?, FAMILY=? WHERE ID=?");
        preparedStatement.setString(1,member.getName());
        preparedStatement.setString(2,member.getFamily());
        preparedStatement.setInt(3,member.getId());
        preparedStatement.executeUpdate();
    }

    public void remove(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM MEMBER WHERE ID = ?");
        preparedStatement.setInt(1 ,id);
        preparedStatement.executeUpdate();
    }

    public List<Member> findAll() throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM MEMBER ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Member> memberList = new ArrayList<>();
        while (resultSet.next()) {
            Member member = new Member()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setFamily(resultSet.getString("family"));
            memberList.add(member);
        }
        return memberList;
    }

    public Member findById(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM MEMBER WHERE ID=?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
            Member member = new Member()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setFamily(resultSet.getString("family"));
        return member;

    }
}
