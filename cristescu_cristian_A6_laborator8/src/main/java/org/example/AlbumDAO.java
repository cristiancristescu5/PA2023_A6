package org.example;

import java.sql.*;

public class AlbumDAO {
    public void create(int releaseYear, String title, String artist, String genres) throws SQLException {
        Connection con = Database.getConnection();
        int idArtist = new ArtistDAO().findByName(artist);
        try(PreparedStatement preparedStatement = con.prepareStatement(
                "insert into albums (release_year, title, artist_id)" +
                        " values (?,?,?)")){
            preparedStatement.setInt(1, releaseYear);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, idArtist);

            preparedStatement.executeUpdate();
        }

        int idAlbum = new AlbumDAO().findByName(title);
        String[] genresVec = genres.split(",");
        for(String g : genresVec){
            int idGenres = new GenreDAO().findByName(g);
            try(PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into album_genre (album_id, genre_id) values (?,?)"
            )){
                preparedStatement.setInt(1, idAlbum);
                preparedStatement.setInt(2, idGenres);
                preparedStatement.executeUpdate();
            }

        }

    }

    public Integer findByName(String name) throws SQLException {
        Connection connection = Database.getConnection();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from albums where title = '" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }

    }

    public String findById(int id)throws SQLException{
        Connection con = Database.getConnection();
        try(Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select name from albums where id = '" + id +"'"
        )){
            return resultSet.next() ? resultSet.getString(1) : null;
        }
    }

    @Override
    public String toString()  {
        Connection connection = Database.getConnection();
        String afisare = new String();
        try(Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select * from albums"
        )){
            while(resultSet.next()){
                afisare = afisare.concat(resultSet.getString(1)).concat(" ")
                        .concat(resultSet.getString(2)).concat(" ")
                        .concat(resultSet.getString(3)).concat(" ")
                        .concat(resultSet.getString(4)).concat("\n");
            }
        }catch (SQLException e){
            System.err.println(e);
        }
        return afisare;

    }
}
