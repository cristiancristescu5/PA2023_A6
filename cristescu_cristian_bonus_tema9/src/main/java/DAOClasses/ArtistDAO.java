package DAOClasses;

import Factories.AbstractFactory;
import Factories.JDBCFactory;
import EntitiesJDBC.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO extends DataDAO{
//    private Database database = new Database();
    private Connection connection;
    public ArtistDAO(AbstractFactory abstractFactory) throws SQLException {
        if(abstractFactory instanceof JDBCFactory){
            connection = ((JDBCFactory)abstractFactory).getConnection();
        }else {
            throw new IllegalStateException("not an instance of JDBCFactory");
        }
    }

    public void create(String name) throws SQLException {
//        Connection con = database.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(
                "insert into artists (name) values (?)")) {
            stmt.setString(1, name);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    @Override
    public Entity findByName(String name) throws SQLException {
//        Connection con = database.getConnection();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select id from artists where name='" + name + "'")) {
//            Database.closeConnection();
            return rs.next() ? new Artist(rs.getInt(1), name) : null;
        }

    }
    @Override
    public Entity findById(int id) throws SQLException {
//        Connection connection = database.getConnection();
        try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "select name from artists where id = '" + id + "'")){
//            Database.closeConnection();
            return rs.next() ? new Artist(id,rs.getString(1)) : null;
        }
    }

    @Override
    public List<Entity> findAll() throws SQLException {
        List<Entity> artists = new ArrayList<>();
//        Connection connection = database.getConnection();
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
