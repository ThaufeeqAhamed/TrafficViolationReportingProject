package com.traffic.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    // Database connection parameters
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/traffic_db";
    private static final String USER = "postgres";
    private static final String PASS = "123";

    // Method to establish a connection
    public static Connection getConnection() throws SQLException {
        try {
            // Load PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        }

        // Return connection to the database
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
