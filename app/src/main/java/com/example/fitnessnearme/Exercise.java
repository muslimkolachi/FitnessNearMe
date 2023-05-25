package com.example.fitnessnearme;

public class Exercise {
    private String name;
    private String description;
    private String imageUrl;
    private String repRange;

    public Exercise(String name, String description, String imageUrl, String repRange) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.repRange = repRange;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRepRange() {
        return repRange;
    }
}
