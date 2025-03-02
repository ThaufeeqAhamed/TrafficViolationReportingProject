package com.traffic.report;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthoritySignUpForm extends JFrame {

    public AuthoritySignUpForm() {
        setTitle("Authority Sign-Up");
        setSize(500, 400); // Increased size for better layout
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set gradient background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 255, 255), 0, getHeight(), new Color(230, 230, 250));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        add(backgroundPanel);

        // Form panel with rounded corners and shadow
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        formPanel.setPreferredSize(new Dimension(450, 320)); // Adjusted size
        formPanel.setOpaque(true);
        backgroundPanel.add(formPanel, gbc);

        // Title
        JLabel titleLabel = new JLabel("Authority Sign-Up");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 102, 204)); // Blue color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridy = 1;
        gbc.gridx = 0;
        formPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(20); // Increased width
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20); // Increased width
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Confirm Password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(confirmPasswordLabel, gbc);

        JPasswordField confirmPasswordField = new JPasswordField(20); // Increased width
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);

        // Authority ID (4 letters + 4 digits)
        JLabel idLabel = new JLabel("Authority ID (4 letters + 4 digits):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(idLabel, gbc);

        JTextField idField = new JTextField(20); // Increased width
        idField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        // Submit button with custom styling
        JButton submitButton = new JButton("Sign Up");
        submitButton.setBackground(new Color(0, 102, 204));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setPreferredSize(new Dimension(150, 40));
        submitButton.setFocusPainted(false);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.setBorder(BorderFactory.createEmptyBorder());
        submitButton.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(submitButton, gbc);

        // Action listener for sign-up button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String idNumber = idField.getText();

                // Simple validation
                if (username.isEmpty() || password.isEmpty() || idNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(AuthoritySignUpForm.this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(AuthoritySignUpForm.this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!idNumber.matches("[A-Za-z]{4}\\d{4}")) {
                    JOptionPane.showMessageDialog(AuthoritySignUpForm.this, "ID must be 4 letters followed by 4 digits!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    AuthorityDAO authorityDAO = new AuthorityDAO();
                    authorityDAO.addAuthority(username, password, idNumber);
                    JOptionPane.showMessageDialog(AuthoritySignUpForm.this, "Authority registered successfully!");
                    dispose(); // Close the form after successful sign-up
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AuthoritySignUpForm().setVisible(true));
    }
}
