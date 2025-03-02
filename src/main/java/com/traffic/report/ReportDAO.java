package com.traffic.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReportDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/traffic_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    public void saveReport(String vehicleNumber, String description, String imagePath, String reportDate, String reportTime, String location) {
        String sql = "INSERT INTO reports (vehicle_number, description, image_path, report_date, report_time, location, status) VALUES (?, ?, ?, ?, ?, ?, 'pending')";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Convert reportDate to Timestamp
            String dateTimeString = reportDate + " " + reportTime;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp = new Timestamp(sdf.parse(dateTimeString).getTime());

            // Convert reportTime to Time
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Time time = new Time(timeFormat.parse(reportTime).getTime());

            statement.setString(1, vehicleNumber);
            statement.setString(2, description);
            statement.setString(3, imagePath);
            statement.setTimestamp(4, timestamp); // Insert as TIMESTAMP
            statement.setTime(5, time); // Insert as TIME
            statement.setString(6, location);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
