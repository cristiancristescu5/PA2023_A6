package org.example.DAOClasses;

import org.example.Classes.Entity;
import org.example.Classes.Genre;
import org.example.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO extends DataDAO {

    public GenreDAO() {
    }

    public void create(String name, Database database)throws SQLException{
        Connection connection = database.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into genres (name) values (?)")){
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
        Database.closeConnection();
    }
    @Override
    public Entity findByName(String name, Database database) throws SQLException{
        Connection connection = database.getConnection();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select id from genres where name = '" + name +"'"
            )){
            Database.closeConnection();
            return resultSet.next() ? new Genre(name,resultSet.getInt(1)) : null;
        }
    }
    @Override
    public Entity findById (int id, Database database) throws SQLException{
        Connection connection = database.getConnection();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select name from genres where id = '" + id + "'")){
            Database.closeConnection();
            return  resultSet.next() ? new Genre(resultSet.getString(1), id) : null;
        }
    }

    @Override
    public List<Entity> findAll(Database database) throws SQLException {
        List<Entity>genres = new ArrayList<>();
        Connection connection = database.getConnection();
        try(Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select name from genres"
        )){
            while(resultSet.next()){
                genres.add(new Genre(resultSet.getString(1)));
            }
        }
        return genres;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
