package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/music";
    private static final String USER = "root";
    private static final String PASSWORD = "0505cri2002";
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;
    public Database() {
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(3000);
        dataSource = new HikariDataSource(config);
        }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeConnection() throws SQLException {
        dataSource.getConnection().close();
    }
    public void rollBack() throws SQLException {
        dataSource.getConnection().rollback();
    }
}
