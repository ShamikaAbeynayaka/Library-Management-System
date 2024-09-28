package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/LibraryManagement";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "mysql123"; // Replace with your MySQL password
    private static Connection connection = null;

    // Get a connection to the database
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establish the connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully.");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Failed to connect to the database.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Close the connection to the database
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Reset the connection to null after closing
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close the database connection.");
                e.printStackTrace();
            }
        }
    }
}

