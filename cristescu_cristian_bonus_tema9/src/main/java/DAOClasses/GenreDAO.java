package DAOClasses;

import Factories.AbstractFactory;
import Factories.JDBCFactory;
import EntitiesJDBC.*;
import DAOClasses.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO extends DataDAO {
    Connection connection;

    public GenreDAO(AbstractFactory abstractFactory) throws SQLException {
        if (abstractFactory instanceof JDBCFactory) {
            connection = ((JDBCFactory) abstractFactory).getConnection();
        } else {
            throw new IllegalStateException("not an instance of JDBCFactory");
        }
    }

    public void create(String name) throws SQLException {
//        Connection connection = database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into genres (name) values (?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
//        Database.closeConnection();
    }

    @Override
    public Entity findByName(String name) throws SQLException {
//        Connection connection = database.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "select id from genres where name = '" + name + "'"
             )) {
//            Database.closeConnection();
            return resultSet.next() ? new Genre(name, resultSet.getInt(1)) : null;
        }
    }

    @Override
    public Entity findById(int id) throws SQLException {
//        Connection connection = database.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "select name from genres where id = '" + id + "'")) {
//            Database.closeConnection();
            return resultSet.next() ? new Genre(resultSet.getString(1), id) : null;
        }
    }

    @Override
    public List<Entity> findAll() throws SQLException {
        List<Entity> genres = new ArrayList<>();
//        Connection connection = database.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "select name from genres"
             )) {
            while (resultSet.next()) {
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
