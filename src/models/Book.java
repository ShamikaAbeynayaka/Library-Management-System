package models;

public class Book extends Item {
    private String author;
    private String genre;

    public Book(int id, String title, String author, String genre) {
        super(id, title);
        this.author = author;
        this.genre = genre;
    }

    // Getters and Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
    }
}
