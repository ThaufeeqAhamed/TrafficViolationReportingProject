package com.traffic.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorityDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/traffic_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    public void addAuthority(String username, String password, String idNumber) {
        String sql = "INSERT INTO authorities (username, password, id_number) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, idNumber);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validateAuthority(String username, String password) {
        String sql = "SELECT * FROM authorities WHERE username = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // returns true if a matching record is found

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
