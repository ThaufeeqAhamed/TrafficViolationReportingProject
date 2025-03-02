package com.traffic.report;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserSignUpForm extends JFrame {

    public UserSignUpForm() {
        setTitle("User Sign-Up");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Background color or image
        getContentPane().setBackground(new Color(240, 240, 240)); // Light grey background

        // Panel for form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255)); // White background
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2)); // Border
        formPanel.setPreferredSize(new Dimension(350, 250));
        formPanel.setOpaque(true);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        JLabel titleLabel = new JLabel("User Sign-Up");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 204)); // Blue color
        formPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridy = 1;
        formPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Confirm Password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(confirmPasswordLabel, gbc);

        JPasswordField confirmPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);

        // Submit button
        JButton submitButton = new JButton("Sign Up");
        submitButton.setBackground(new Color(0, 102, 204)); // Blue background
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setPreferredSize(new Dimension(150, 40));
        submitButton.setFocusPainted(false);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(submitButton, gbc);

        add(formPanel);

        // Action listener for sign-up button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // Simple validation
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(UserSignUpForm.this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(UserSignUpForm.this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    UserDAO userDAO = new UserDAO();
                    userDAO.addUser(username, password);
                    JOptionPane.showMessageDialog(UserSignUpForm.this, "User registered successfully!");
                    dispose(); // Close the form after successful sign-up
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserSignUpForm().setVisible(true));
    }
}
