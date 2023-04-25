package org.example;

import java.sql.*;

public class GenreDAO {
    public void create(String name)throws SQLException{
        Connection connection = Database.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into genres (name) values (?)")){
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException{
        Connection connection = Database.getConnection();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select id from genres where name = '" + name +"'"
            )){
            return resultSet.next() ? resultSet.getInt(1) : null;
        }
    }

    public String findById (int id) throws SQLException{
        Connection connection = Database.getConnection();
        try(Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select name from genres where id = '" + id + "'")){
            return  resultSet.next() ? resultSet.getString(1) : null;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
