package com.traffic.report;

import javax.swing.*;
import java.awt.*;

public class SignUpChoiceFrame extends JFrame {

    public SignUpChoiceFrame() {
        // Set frame properties
        setTitle("Choose Sign Up Type");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background image
        setContentPane(new JLabel(new ImageIcon("C:/Users/THAUFEEQ AHAMED/Downloads/bg4.jpg"))); // Replace with your image path
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create content panel with transparent background
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(contentPanel);

        // Title label
        JLabel titleLabel = new JLabel("Choose Sign Up Type");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 255, 255)); // White text color
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(titleLabel, gbc);

        // Sign-up options panel
        JPanel signUpOptionsPanel = new JPanel();
        signUpOptionsPanel.setLayout(new GridLayout(3, 1, 10, 10));
        signUpOptionsPanel.setOpaque(false);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPanel.add(signUpOptionsPanel, gbc);

        // User Sign Up button
        JButton userSignUpButton = new JButton("User Sign Up");
        userSignUpButton.setFont(new Font("Arial", Font.BOLD, 18));
        userSignUpButton.setForeground(Color.WHITE);
        userSignUpButton.setBackground(new Color(30, 144, 255)); // Blue color
        userSignUpButton.setPreferredSize(new Dimension(200, 50));
        userSignUpButton.setFocusPainted(false);
        userSignUpButton.setBorderPainted(false);
        signUpOptionsPanel.add(userSignUpButton);

        // Authority Sign Up button
        JButton authoritySignUpButton = new JButton("Authority Sign Up");
        authoritySignUpButton.setFont(new Font("Arial", Font.BOLD, 18));
        authoritySignUpButton.setForeground(Color.WHITE);
        authoritySignUpButton.setBackground(new Color(255, 69, 0)); // Red-orange color
        authoritySignUpButton.setPreferredSize(new Dimension(200, 50));
        authoritySignUpButton.setFocusPainted(false);
        authoritySignUpButton.setBorderPainted(false);
        signUpOptionsPanel.add(authoritySignUpButton);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.RED); // Red color
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        signUpOptionsPanel.add(backButton);

        // Action listeners for buttons
        userSignUpButton.addActionListener(e -> new UserSignUpForm().setVisible(true));
        authoritySignUpButton.addActionListener(e -> new AuthoritySignUpForm().setVisible(true));
        backButton.addActionListener(e -> dispose()); // Closes the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignUpChoiceFrame().setVisible(true));
    }
}
