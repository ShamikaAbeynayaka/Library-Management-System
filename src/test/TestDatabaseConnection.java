//package test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class TestDatabaseConnection {
//    public static void main(String[] args) {
//        try {
//            // Test MySQL connection
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/LibraryManagement", "root", "mysql123");
//            System.out.println("Connection successful!");
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
