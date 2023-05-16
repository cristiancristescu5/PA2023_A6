package Factories;

import java.sql.SQLException;

public abstract class AbstractFactory<T> {
    public static AbstractFactory getFactory(int flag) throws SQLException {
        if(flag == 1){
            return new JDBCFactory();
        }
        if(flag == 2){
            return new JPAFactory();
        }
        throw new IllegalArgumentException("Invalid DAO type: " + flag);
    }
    public abstract T getAlbumsDAO() throws SQLException;
    public abstract T getGenreDAO() throws SQLException;
    public abstract T getBondsDAO() throws SQLException;
    public abstract T getArtistsDAO() throws SQLException;


}
