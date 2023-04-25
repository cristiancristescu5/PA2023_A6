package org.example;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        try {
            ArtistDAO artistDAO = new ArtistDAO();
            artistDAO.create("Pink Floyd");
            artistDAO.create("Michael Jackson");
            GenreDAO genreDAO = new GenreDAO();
            genreDAO.create("Rock");
            genreDAO.create("Funk");
            genreDAO.create("Soul");
            genreDAO.create("Pop");

            Database.getConnection().commit();
            AlbumDAO albumDAO = new AlbumDAO();

            albumDAO.create(1979, "The Wall", "Pink Floyd", "Rock");
            albumDAO.create(1982, "Thriller", "Michael Jackson", "Funk,Soul,Pop");
            Database.getConnection().commit();

            System.out.println(albumDAO.toString());
            Database.getConnection().close();
        }catch (SQLException e){
            System.err.println(e);
            Database.rollback();
        }
    }
}