package com.traffic.report;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        try {
            // Attempt to get a connection
            Connection connection = DatabaseUtil.getConnection();

            // If the connection is successful, print a success message
            if (connection != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Connection failed!");
            }

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            // Print any errors that occur during connection
            System.out.println("Error connecting to the database:");
            e.printStackTrace();
        }
    }
}
