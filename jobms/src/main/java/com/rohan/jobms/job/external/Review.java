package com.rohan.jobms.job.external;

public class Review {
    private Long id;
    private String title;
    private String description;
    private double rating;

    // Getter for id
    public Long getId() {
        return id;
    }

    // Setter for id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for rating
    public double getRating() {
        return rating;
    }

    // Setter for rating
    public void setRating(double rating) {
        this.rating = rating;
    }
}
