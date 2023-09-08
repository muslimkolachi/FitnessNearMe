package com.example.fitnessnearme;

public class Exercise {
    private String name;
    private String description;
    private String imageUrl;
    private String repRange;
    private int gifResource;

    public Exercise(String name, String description, String imageUrl, String repRange, int gifResource) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.repRange = repRange;
        this.gifResource = gifResource; // Initialize GIF resource ID
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

    public int getGifResource() {
        return gifResource;
    }

    public long getDuration() {
        return 0;
    }

}

