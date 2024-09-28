package models;

public class DVD extends Item {
    private String director;
    private String duration;

    public DVD(int id, String title, String director, String duration) {
        super(id, title);
        this.director = director;
        this.duration = duration;
    }

    // Getters and Setters
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Director: " + director);
        System.out.println("Duration: " + duration);
    }
}