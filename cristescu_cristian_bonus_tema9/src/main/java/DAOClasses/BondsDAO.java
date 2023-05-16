package DAOClasses;

import Factories.AbstractFactory;
import Factories.JDBCFactory;
import EntitiesJDBC.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BondsDAO  extends DataDAO{
    Connection connection;
//    public List<Entity> findAll(Database database){
//        List<Entity> bonds = new ArrayList<>();
//        return bonds;
//    }
    public BondsDAO(AbstractFactory abstractFactory) throws SQLException {
        if(abstractFactory instanceof JDBCFactory){
            connection = ((JDBCFactory) abstractFactory).getConnection();
        }else {
            throw new IllegalStateException("not an instace of JDBCFactory");
        }
    }
    public List<Integer> findGenres(Integer id) throws SQLException {
        List<Integer> genres = new ArrayList<>();
//        Connection connection = database.getConnection();
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
    public Entity findByArtist( Integer id){


        return null;
    }

    public Entity findByGenre(Integer id){


        return null;
    }

    @Override
    public Entity findByName(String name) throws SQLException {
        return null;
    }

    @Override
    public Entity findById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Entity> findAll() throws SQLException {
        return null;
    }
}
