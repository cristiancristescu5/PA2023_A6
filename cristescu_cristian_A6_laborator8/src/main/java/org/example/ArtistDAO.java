package org.example;

import java.sql.*;

public class ArtistDAO {
    public void create(String name) {
        Connection con = Database.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(
                "insert into artists (name) values (?)")) {
            stmt.setString(1, name);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id from artists where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }

    }
    public String findById(int id) throws SQLException{
        Connection connection = Database.getConnection();
        try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
            "select name from artists where id = '" + id + "'")){
            return rs.next() ? rs.getString(1) : null;
        }
    }
}
