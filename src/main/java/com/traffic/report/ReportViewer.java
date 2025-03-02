package com.traffic.report;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;
import java.util.Vector;

public class ReportViewer extends JFrame {
    private JTable table;
    private JButton approveButton;
    private JButton rejectButton;
    private JButton showPendingButton;
    private JButton viewImageButton;
    private DefaultTableModel tableModel;
    private boolean showPendingOnly = false; // Toggle for showing only pending reports

    public ReportViewer() {
        setTitle("Traffic Violation Reports");
        setSize(1200, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Gradient background for the main panel
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(135, 206, 250); // Light Sky Blue
                Color color2 = new Color(70, 130, 180);  // Steel Blue
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Table setup with custom styling
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Report ID");
        tableModel.addColumn("Vehicle Number");
        tableModel.addColumn("Description"); // Added column for description
        tableModel.addColumn("Image");
        tableModel.addColumn("Status");
        tableModel.addColumn("Date");
        tableModel.addColumn("Time");
        tableModel.addColumn("Location");

        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }

            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? new Color(240, 248, 255) : new Color(255, 255, 255)); // Alternating row colors
                }
                return c;
            }
        };

        // Custom table header font and background
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
        tableHeader.setBackground(new Color(70, 130, 180)); // Steel Blue background
        tableHeader.setForeground(Color.WHITE);

        // Enhanced cell borders
        table.setGridColor(new Color(192, 192, 192)); // Light Gray grid lines
        table.setRowHeight(30);

        // Center align table content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button setup with hover effect and icons
        approveButton = createStyledButton("Approve", new Color(60, 179, 113), "icons/approve.png", new Dimension(120, 40));
        rejectButton = createStyledButton("Reject", new Color(220, 20, 60), "icons/reject.png", new Dimension(120, 40));
        showPendingButton = createStyledButton("Show Pending Reports", new Color(255, 165, 0), "icons/pending.png", new Dimension(220, 40));
        viewImageButton = createStyledButton("View Image", new Color(150, 75, 0), "icons/view.png", new Dimension(140, 40));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false); // Transparent background to blend with gradient
        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(showPendingButton);
        buttonPanel.add(viewImageButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Load the reports
        loadReports(showPendingOnly);

        // Approve button action
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a report to approve.");
                } else {
                    int reportId = (int) tableModel.getValueAt(selectedRow, 0);
                    try {
                        updateReportStatus(reportId, "confirmed");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error updating report status: " + ex.getMessage());
                    }
                }
            }
        });

        // Reject button action
        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a report to reject.");
                } else {
                    int reportId = (int) tableModel.getValueAt(selectedRow, 0);
                    try {
                        updateReportStatus(reportId, "rejected");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error updating report status: " + ex.getMessage());
                    }
                }
            }
        });

        // Show Pending Reports button action
        showPendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPendingOnly = !showPendingOnly; // Toggle the filter
                loadReports(showPendingOnly); // Reload reports with the filter
                showPendingButton.setText(showPendingOnly ? "Show All Reports" : "Show Pending Reports");
            }
        });

        // View Image button action
        viewImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a report to view the image.");
                } else {
                    String imagePath = (String) tableModel.getValueAt(selectedRow, 3); // Column 3 contains the image path
                    if (imagePath != null && !imagePath.isEmpty()) {
                        showImage(imagePath); // Call the method to display the image
                    } else {
                        JOptionPane.showMessageDialog(null, "No image associated with this report.");
                    }
                }
            }
        });
    }

    // Helper method to create styled buttons with hover effect and dynamic width
    private JButton createStyledButton(String text, Color backgroundColor, String iconPath, Dimension size) {
        JButton button = new JButton(text);
        button.setPreferredSize(size); // Use the passed size for button width
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);  // Remove focus border
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setIcon(new ImageIcon(iconPath)); // Set the icon

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });

        return button;
    }

    // Method to load reports from the database
    private void loadReports(boolean pendingOnly) {
        try {
            Connection conn = DatabaseUtil.getConnection();
            String query;
            if (pendingOnly) {
                query = "SELECT id, vehicle_number, description, image_path, status, report_date, location FROM reports WHERE status = 'pending'";
            } else {
                query = "SELECT id, vehicle_number, description, image_path, status, report_date, location FROM reports";
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            tableModel.setRowCount(0); // Clear existing data before loading new data
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("id"));
                row.add(rs.getString("vehicle_number"));
                row.add(rs.getString("description")); // Added description
                row.add(rs.getString("image_path"));
                row.add(rs.getString("status"));
                row.add(rs.getDate("report_date")); // Date column
                row.add(rs.getTime("report_date")); // Time column
                row.add(rs.getString("location"));  // Location column
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update the status of the report
    private void updateReportStatus(int reportId, String status) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String query = "UPDATE reports SET status = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, status);
        pstmt.setInt(2, reportId);
        pstmt.executeUpdate();
        loadReports(showPendingOnly); // Reload the reports after updating the status
    }

    // Method to display image in a new JFrame
    private void showImage(String imagePath) {
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            JOptionPane.showMessageDialog(null, "Image file not found at " + imagePath);
            return;
        }

        JFrame imageFrame = new JFrame("View Image");
        imageFrame.setSize(600, 600);

        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(imageIcon);

        JScrollPane imageScrollPane = new JScrollPane(imageLabel); // Scroll pane to handle large images
        imageFrame.add(imageScrollPane);
        imageFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReportViewer viewer = new ReportViewer();
            viewer.setVisible(true);
        });
    }
}
