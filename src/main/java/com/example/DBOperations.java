package com.example;

import java.sql.*;

public class DBOperations {

    // method to connect with the database
    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(Details.URL, Details.USER, Details.PASSWORD);
            System.out.println("Connected to the database!");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            connection = null;
        }
        return connection;
    }
}
