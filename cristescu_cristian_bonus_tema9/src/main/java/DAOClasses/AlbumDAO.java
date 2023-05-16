package DAOClasses;
import Factories.AbstractFactory;
import Factories.JDBCFactory;
import EntitiesJDBC.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO extends DataDAO {
    private Connection connection;
    private AbstractFactory abstractFactory;
    public AlbumDAO(AbstractFactory abstractFactory) throws SQLException {
        if(abstractFactory instanceof JDBCFactory){
            connection = ((JDBCFactory)abstractFactory).getConnection();
            abstractFactory = abstractFactory;
        }else {
            throw new IllegalStateException("not an instance of JDBCFactory");
        }
    }
    public void create(int releaseYear, String title, String artist, String[] genres) throws SQLException {
//        Connection con = Database.getConnection();
        int idArtist = new ArtistDAO(abstractFactory).findByName(artist).getId();//if exista
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into albums (release_year, title, artist_id)" +
                        " values (?,?,?)")){
            preparedStatement.setInt(1, releaseYear);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, idArtist);

            preparedStatement.executeUpdate();
        }

        int idAlbum = new AlbumDAO(abstractFactory).findByName(title).getId();
//        String[] genresVec = genres.split(",");
        for(String g : genres){
            int idGenres = new GenreDAO(abstractFactory).findByName(g).getId();
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into album_genre (album_id, genre_id) values (?,?)"
            )){
                preparedStatement.setInt(1, idAlbum);
                preparedStatement.setInt(2, idGenres);
                preparedStatement.executeUpdate();
            }

        }
//        Database.closeConnection();


    }
    @Override
    public Entity findByName(String name) throws SQLException {
//        Connection connection = database.getConnection();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select id, artist_id, release_year from albums where title = '" + name + "'")) {
//            Database.closeConnection();
            return rs.next() ? new Album(rs.getInt(1), name, rs.getInt(3), rs.getInt(2)) : null;
        }

    }
    @Override
    public Entity findById(int id)throws SQLException{
//        Connection con = database.getConnection();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select title, release_year, artist_id from albums where id = '" + id +"'")){
//            Database.closeConnection();
            return resultSet.next() ? new Album(id, resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3)) : null;
        }
    }

    @Override
    public List<Entity> findAll() throws SQLException {
        List<Entity> albums = new ArrayList<>();
//        Connection connection = database.getConnection();
        try(Statement statement  = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                "select id, title, artist_id, release_year from albums"
        )){
            while(resultSet.next()){
                albums.add(new Album(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(4), resultSet.getInt(3)));
            }
        }
        return albums;
    }


//    @Override
//    public String toString()  {
//        Connection connection = null;
//        try {
//            connection = Database.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        String afisare = new String();
//        try(Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(
//                    "select * from albums"
//            )){
//            while(resultSet.next()){
//                afisare = afisare.concat(resultSet.getString(1)).concat(" ")
//                        .concat(resultSet.getString(2)).concat(" ")
//                        .concat(resultSet.getString(3)).concat(" ")
//                        .concat(resultSet.getString(4)).concat("\n");
//            }
//        }catch (SQLException e){
//            System.err.println(e);
//        }
//        return afisare;
//
//    }
}
