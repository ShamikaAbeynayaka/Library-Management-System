package dbManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.ItemUnavailableException;
import models.Book;
import models.DVD;
import models.Item;
import util.DatabaseConnection;

public class DatabaseManager {
    private Connection conn;

    public DatabaseManager() {
        conn = DatabaseConnection.getConnection(); // Get the connection from the DatabaseConnection class
    }

    public void addItem(Item item) throws SQLException {
        String query = "INSERT INTO Items (title, author_or_director, genre_or_duration, itemType, isAvailable) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, item.getTitle());
            if (item instanceof Book) {
                stmt.setString(2, ((Book) item).getAuthor());
                stmt.setString(3, ((Book) item).getGenre());
                stmt.setString(4, "Book");
            } else if (item instanceof DVD) {
                stmt.setString(2, ((DVD) item).getDirector());
                stmt.setString(3, ((DVD) item).getDuration());
                stmt.setString(4, "DVD");
            }
            stmt.setBoolean(5, item.isAvailable());
            stmt.executeUpdate();
        }
    }

    public void borrowItem(int id) throws SQLException, ItemUnavailableException {
        String query = "SELECT * FROM Items WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Item with ID " + id + " does not exist.");
            }
            boolean isAvailable = rs.getBoolean("isAvailable");
            if (!isAvailable) {
                throw new ItemUnavailableException("Item is already borrowed.");
            }
            String updateQuery = "UPDATE Items SET isAvailable = false WHERE id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                updateStmt.setInt(1, id);
                updateStmt.executeUpdate();
            }
        }
    }

    public void returnItem(int id) throws SQLException {
        String query = "SELECT * FROM Items WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Item with ID " + id + " does not exist.");
            }
            String updateQuery = "UPDATE Items SET isAvailable = true WHERE id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                updateStmt.setInt(1, id);
                updateStmt.executeUpdate();
            }
        }
    }

    public void deleteItem(int id) throws SQLException {
        String query = "SELECT * FROM Items WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Item with ID " + id + " does not exist.");
            }
            String deleteQuery = "DELETE FROM Items WHERE id = ?";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                deleteStmt.setInt(1, id);
                deleteStmt.executeUpdate();
            }
        }
    }

    public void displayAllItems() throws SQLException {
        String query = "SELECT * FROM Items";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String authorOrDirector = rs.getString("author_or_director");
                String genreOrDuration = rs.getString("genre_or_duration");
                String itemType = rs.getString("itemType");
                boolean isAvailable = rs.getBoolean("isAvailable");

                System.out.println("ID: " + id);
                System.out.println("Title: " + title);
                if (itemType.equals("Book")) {
                    System.out.println("Author: " + authorOrDirector);
                    System.out.println("Genre: " + genreOrDuration);
                } else if (itemType.equals("DVD")) {
                    System.out.println("Director: " + authorOrDirector);
                    System.out.println("Duration: " + genreOrDuration);
                }
                System.out.println("Available: " + isAvailable);
                System.out.println("---------------------------");
            }
        }
    }

    // Call this method when done with the database operations to close the connection
    public void close() {
        DatabaseConnection.closeConnection();
    }
}
