package Factories;

import DAOClasses.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCFactory extends AbstractFactory<DataDAO> {
    private static final String URL = "jdbc:mysql://localhost:3306/musicjpa";
    private static final String USER = "root";
    private static final String PASSWORD = "0505cri2002";
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    public JDBCFactory() throws SQLException {
        config.setJdbcUrl(URL);
        config.setMaximumPoolSize(3000);
        config.setPassword(PASSWORD);
        config.setUsername(USER);
        dataSource = new HikariDataSource(config);
    }
    public  Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeConnection() throws SQLException {
        dataSource.getConnection().close();
    }
    public void rollBack() throws SQLException {
        dataSource.getConnection().rollback();
    }

    @Override
    public DataDAO getAlbumsDAO() throws SQLException {
        return new AlbumDAO(this);
    }

    @Override
    public DataDAO getGenreDAO() throws SQLException {
        return new GenreDAO(this);
    }

    @Override
    public DataDAO getBondsDAO() throws SQLException {
        return new BondsDAO(this);
    }

    @Override
    public DataDAO getArtistsDAO() throws SQLException {
        return new ArtistDAO(this);
    }
}
