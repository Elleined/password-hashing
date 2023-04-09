package com.passwordHashing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySQLConnection {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/croptrade_db";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD =  "root";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_PASSWORD, DB_USERNAME);
            System.out.println("Database connection success");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error Occurred! Database connection failed!");
        }
        return connection;
    }
}
