package com.traffic.report;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPageFrame extends JFrame {

    public LoginPageFrame() {
        // Set the frame properties
        setTitle("Choose Login Type");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background image
        setContentPane(new JLabel(new ImageIcon("C:/Users/THAUFEEQ AHAMED/Downloads/bg4.jpg"))); // Replace with your image path
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create a panel for content with a semi-transparent background
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setOpaque(false); // Make it transparent
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(contentPanel);

        // Title label
        JLabel titleLabel = new JLabel("Choose Login Type");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 255, 255)); // White text color
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(titleLabel, gbc);

        // Login options panel
        JPanel loginOptionsPanel = new JPanel();
        loginOptionsPanel.setLayout(new GridLayout(3, 1, 10, 10));
        loginOptionsPanel.setOpaque(false);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPanel.add(loginOptionsPanel, gbc);

        // User Login button
        JButton userLoginButton = new JButton("User Login");
        userLoginButton.setFont(new Font("Arial", Font.BOLD, 18));
        userLoginButton.setForeground(Color.WHITE);
        userLoginButton.setBackground(new Color(30, 144, 255)); // Blue color
        userLoginButton.setPreferredSize(new Dimension(200, 50));
        userLoginButton.setFocusPainted(false);
        userLoginButton.setBorderPainted(false);
        loginOptionsPanel.add(userLoginButton);

        // Authority Login button
        JButton authorityLoginButton = new JButton("Traffic Authority Login");
        authorityLoginButton.setFont(new Font("Arial", Font.BOLD, 18));
        authorityLoginButton.setForeground(Color.WHITE);
        authorityLoginButton.setBackground(new Color(255, 69, 0)); // Red-Orange color
        authorityLoginButton.setPreferredSize(new Dimension(200, 50));
        authorityLoginButton.setFocusPainted(false);
        authorityLoginButton.setBorderPainted(false);
        loginOptionsPanel.add(authorityLoginButton);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.RED); // Red color
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        loginOptionsPanel.add(backButton);

        // Add action listeners for buttons
        userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUserLogin();
            }
        });

        authorityLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAuthorityLogin();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame and return to the home page
            }
        });
    }

    private void openUserLogin() {
        // Open User Login Page
        new UserLoginFrame().setVisible(true);
    }

    private void openAuthorityLogin() {
        // Open Traffic Authority Login Page
        new AuthorityLoginFrame().setVisible(true);
    }

    public static void main(String[] args) {
        // Create and display the login page frame
        SwingUtilities.invokeLater(() -> new LoginPageFrame().setVisible(true));
    }
}
