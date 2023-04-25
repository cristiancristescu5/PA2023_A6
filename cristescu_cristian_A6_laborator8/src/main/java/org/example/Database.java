package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/music";
    private static final String USER = "root";
    private static final String PASSWORD = "0505cri2002";
    private static Connection connection = null;
    private Database()  {

    }
    public static Connection getConnection(){
        if(connection == null){
            createConnection();
        }
        return connection;

    }

    private static void createConnection(){
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

}
