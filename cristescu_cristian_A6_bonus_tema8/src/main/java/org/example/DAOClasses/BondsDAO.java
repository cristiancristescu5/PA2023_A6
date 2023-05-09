package org.example.DAOClasses;

import org.example.Classes.Entity;
import org.example.Database;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BondsDAO {
//    public List<Entity> findAll(Database database){
//        List<Entity> bonds = new ArrayList<>();
//        return bonds;
//    }
    public List<Integer> findGenres(Database database, Integer id) throws SQLException {
        List<Integer> genres = new ArrayList<>();
        Connection connection = database.getConnection();
        try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "select genre_id from album_genre where album_id ='"+ id + "'"
            )){
            while(rs.next()){
                genres.add(rs.getInt(1));
            }
        }
        return genres;
    }
    public Entity findByArtist(Database database, Integer id){


        return null;
    }

    public Entity findByGenre(Database database, Integer id){


        return null;
    }
}
