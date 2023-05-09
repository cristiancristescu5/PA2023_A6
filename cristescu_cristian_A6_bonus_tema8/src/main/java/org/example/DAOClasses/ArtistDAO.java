package org.example.DAOClasses;

import org.example.Classes.Artist;
import org.example.Classes.Entity;
import org.example.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO extends DataDAO{
//    private Database database = new Database();

    public ArtistDAO() {
    }

    public void create(String name, Database database) throws SQLException {
        Connection con = database.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(
                "insert into artists (name) values (?)")) {
            stmt.setString(1, name);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
        }
        Database.closeConnection();
    }
    @Override
    public Entity findByName(String name, Database database) throws SQLException {
        Connection con = database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id from artists where name='" + name + "'")) {
            Database.closeConnection();
            return rs.next() ? new Artist(rs.getInt(1), name) : null;
        }

    }
    @Override
    public Entity findById(int id, Database database) throws SQLException {
        Connection connection = database.getConnection();
        try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "select name from artists where id = '" + id + "'")){
            Database.closeConnection();
            return rs.next() ? new Artist(id,rs.getString(1)) : null;
        }
    }

    @Override
    public List<Entity> findAll(Database database) throws SQLException {
        List<Entity> artists = new ArrayList<>();
        Connection connection = database.getConnection();
        try(Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "select id, name from artists"
        )){
            while(rs.next()){
                artists.add(new Artist(rs.getInt(1), rs.getString(2)));
            }
        }
        return artists;
    }
}
