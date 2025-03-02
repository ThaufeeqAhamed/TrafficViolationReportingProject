package com.traffic.report;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportForm extends JFrame {

    public ReportForm() {
        setTitle("Traffic Violation Report");
        setSize(600, 500); // Increased size for better layout
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Custom panel with image background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:/Users/THAUFEEQ AHAMED/Downloads/bg6.jpg"); // Replace with your image path
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Traffic Violation Report");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Vehicle number
        JLabel vehicleLabel = new JLabel("Vehicle Number:");
        vehicleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        vehicleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(vehicleLabel, gbc);

        JTextField vehicleTextField = new JTextField();
        vehicleTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        vehicleTextField.setBorder(new LineBorder(Color.BLACK)); // Add black border
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(vehicleTextField, gbc);

        // Error label
        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(errorLabel, gbc);

        // Description
        JLabel descriptionLabel = new JLabel("Description of Violation:");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(descriptionLabel, gbc);

        JTextArea descriptionTextArea = new JTextArea(5, 20);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionTextArea.setBorder(new LineBorder(Color.BLACK)); // Add black border
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(descriptionScrollPane, gbc);

        // Upload image
        JLabel uploadLabel = new JLabel("Upload Image:");
        uploadLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        uploadLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(uploadLabel, gbc);

        JButton uploadButton = new JButton("Upload");
        uploadButton.setFont(new Font("Arial", Font.BOLD, 16));
        uploadButton.setBackground(Color.YELLOW);
        uploadButton.setForeground(Color.black);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(uploadButton, gbc);

        final String[] filePath = new String[1];

        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filePath[0] = selectedFile.getAbsolutePath();
                JOptionPane.showMessageDialog(this, "File uploaded: " + filePath[0]);
            }
        });

        // Location
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        locationLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(locationLabel, gbc);

        JTextField locationTextField = new JTextField();
        locationTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        locationTextField.setBorder(new LineBorder(Color.BLACK)); // Add black border
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(locationTextField, gbc);

        // Date
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(dateLabel, gbc);

        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setFont(new Font("Arial", Font.PLAIN, 16));
        dateSpinner.setBorder(new LineBorder(Color.BLACK)); // Add black border

        // Custom spinner UI for darkened arrows
        dateSpinner.setUI(new DarkSpinnerUI());

        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(dateSpinner, gbc);

        // Time
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        timeLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(timeLabel, gbc);

        JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setFont(new Font("Arial", Font.PLAIN, 16));
        timeSpinner.setBorder(new LineBorder(Color.BLACK)); // Add black border

        // Apply darkened spinner arrows
        timeSpinner.setUI(new DarkSpinnerUI());

        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(timeSpinner, gbc);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(Color.RED);
        submitButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(submitButton, gbc);

        submitButton.addActionListener(e -> {
            String vehicleNumber = vehicleTextField.getText().toUpperCase(); // Convert to uppercase
            String description = descriptionTextArea.getText();
            String imagePath = filePath[0];
            String location = locationTextField.getText();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            String reportDate = dateFormat.format((Date) dateSpinner.getValue());
            String reportTime = timeFormat.format((Date) timeSpinner.getValue());

            // Validate vehicle number
            if (!vehicleNumber.matches("[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{1,4}")) {
                errorLabel.setText("Please enter a valid vehicle number plate.");
                return;
            } else {
                errorLabel.setText(""); // Clear error if valid
            }

            // Validate input
            if (vehicleNumber.isEmpty() || description.isEmpty() || imagePath == null || location.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields and upload an image.");
            } else {
                // Call method to save the report to the database
                ReportDAO reportDAO = new ReportDAO();
                reportDAO.saveReport(vehicleNumber, description, imagePath, reportDate, reportTime, location);

                // Confirmation
                JOptionPane.showMessageDialog(this, "Report submitted successfully!");
            }
        });

        // Add the panel to the frame
        add(panel);
    }

    // Custom spinner UI with darkened arrows
    private static class DarkSpinnerUI extends BasicSpinnerUI {
        @Override
        protected Component createNextButton() {
            Component nextButton = super.createNextButton();
            if (nextButton instanceof BasicArrowButton) {
                ((BasicArrowButton) nextButton).setBackground(Color.DARK_GRAY);
                ((BasicArrowButton) nextButton).setForeground(Color.WHITE);
            }
            return nextButton;
        }

        @Override
        protected Component createPreviousButton() {
            Component prevButton = super.createPreviousButton();
            if (prevButton instanceof BasicArrowButton) {
                ((BasicArrowButton) prevButton).setBackground(Color.DARK_GRAY);
                ((BasicArrowButton) prevButton).setForeground(Color.WHITE);
            }
            return prevButton;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReportForm().setVisible(true));
    }
}
