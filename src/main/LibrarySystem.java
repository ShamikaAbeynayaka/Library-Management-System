package main;

import java.sql.SQLException;
import java.util.Scanner;

import dbManager.DatabaseManager;
import exceptions.ItemUnavailableException;
import models.Book;
import models.DVD;

public class LibrarySystem {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager dbManager = new DatabaseManager();

        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Borrow Item");
            System.out.println("3. Return Item");
            System.out.println("4. Delete Item");
            System.out.println("5. Display All Items");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add Item
                    System.out.println("1. Add Book");
                    System.out.println("2. Add DVD");
                    int type = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();

                    if (type == 1) {
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter genre: ");
                        String genre = scanner.nextLine();
                        Book book = new Book(0, title, author, genre);
                        try {
                            dbManager.addItem(book);
                            System.out.println("Book added successfully!");
                        } catch (SQLException e) {
                            System.out.println("Failed to add book: " + e.getMessage());
                        }
                    } else if (type == 2) {
                        System.out.print("Enter director: ");
                        String director = scanner.nextLine();
                        System.out.print("Enter duration: ");
                        String duration = scanner.nextLine();
                        DVD dvd = new DVD(0, title, director, duration);
                        try {
                            dbManager.addItem(dvd);
                            System.out.println("DVD added successfully!");
                        } catch (SQLException e) {
                            System.out.println("Failed to add DVD: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid type!");
                    }
                    break;

                case 2:
                    // Borrow Item
                    System.out.print("Enter item ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    try {
                        dbManager.borrowItem(borrowId);
                        System.out.println("Item borrowed successfully!");
                    } catch (SQLException e) {
                        System.out.println("Failed to borrow item: " + e.getMessage());
                    } catch (ItemUnavailableException e) {
                        System.out.println("Failed to borrow item: " + e.getMessage());
                    }
                    break;

                case 3:
                    // Return Item
                    System.out.print("Enter item ID to return: ");
                    int returnId = scanner.nextInt();
                    try {
                        dbManager.returnItem(returnId);
                        System.out.println("Item returned successfully!");
                    } catch (SQLException e) {
                        System.out.println("Failed to return item: " + e.getMessage());
                    }
                    break;

                case 4:
                    // Delete Item
                    System.out.print("Enter item ID to delete: ");
                    int deleteId = scanner.nextInt();
                    try {
                        dbManager.deleteItem(deleteId);
                        System.out.println("Item deleted successfully!");
                    } catch (SQLException e) {
                        System.out.println("Failed to delete item: " + e.getMessage());
                    }
                    break;

                case 5:
                    // Display All Items
                    try {
                        dbManager.displayAllItems();
                    } catch (SQLException e) {
                        System.out.println("Failed to retrieve items: " + e.getMessage());
                    }
                    break;

                case 6:
                    // Exit
                    dbManager.close(); // Close the database connection before exiting
                    System.out.println("Exiting the system...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}