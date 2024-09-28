package models;

import exceptions.ItemUnavailableException;

public class Item {
    protected int id;
    protected String title;
    protected boolean isAvailable;

    public Item(int id, String title) {
        this.id = id;
        this.title = title;
        this.isAvailable = true;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void borrow() throws ItemUnavailableException {
        if (!isAvailable) {
            throw new ItemUnavailableException("Item is unavailable for borrowing.");
        }
        isAvailable = false;
    }

    public void returnItem() {
        isAvailable = true;
    }

    public void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Available: " + isAvailable);
    }
}
