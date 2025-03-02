package com.traffic.report;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePage extends JFrame {

    public HomePage() {
        setTitle("Traffic Reporting System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set the background image
        setContentPane(new JLabel(new ImageIcon("C:/Users/THAUFEEQ AHAMED/Downloads/Untitled design (11).png")));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title panel with rounded corners and semi-transparent white background
        JPanel titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 150)); // Semi-transparent white background
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Rounded corners
            }
        };
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setPreferredSize(new Dimension(400, 60));

        // Title label
        JLabel titleLabel = new JLabel("Traffic Violation Reporting System");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        titleLabel.setForeground(new Color(148, 0, 211));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(titleLabel);

        // Add title panel to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titlePanel, gbc);

        // User Login button
        JButton userLoginButton = new JButton("User Login");
        userLoginButton.setBackground(new Color(30, 144, 255));
        userLoginButton.setForeground(Color.WHITE);
        userLoginButton.setFont(new Font("Arial", Font.BOLD, 16));
        userLoginButton.setPreferredSize(new Dimension(200, 50));
        userLoginButton.setOpaque(true);
        userLoginButton.setBorderPainted(false);
        userLoginButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(userLoginButton, gbc);

        // Authority Login button
        JButton authorityLoginButton = new JButton("Authority Login");
        authorityLoginButton.setBackground(new Color(255, 69, 0));
        authorityLoginButton.setForeground(Color.WHITE);
        authorityLoginButton.setFont(new Font("Arial", Font.BOLD, 16));
        authorityLoginButton.setPreferredSize(new Dimension(200, 50));
        authorityLoginButton.setOpaque(true);
        authorityLoginButton.setBorderPainted(false);
        authorityLoginButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(authorityLoginButton, gbc);

        // Create the label with enlarged text and semi-transparent background
        JLabel signUpLabel = new JLabel("<html><span style='font-size:16px; color: blue;'><u>Don't have an account? Sign up</u></span></html>");
        signUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Create a panel for the background
        JPanel signUpPanel = new JPanel();
        signUpPanel.setOpaque(true);
        signUpPanel.setBackground(new Color(255, 255, 255, 150));
        signUpPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Add the label to the panel
        signUpPanel.add(signUpLabel);

        // Add the panel to the frame
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(signUpPanel, gbc);

        // Action listeners for buttons
        userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open User login frame
                new UserLoginFrame().setVisible(true);
            }
        });

        authorityLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Authority login frame
                new AuthorityLoginFrame().setVisible(true);
            }
        });

        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                // Open sign-up choice frame
                new SignUpChoiceFrame().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
    }
}
