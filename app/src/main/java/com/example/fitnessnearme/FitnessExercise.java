package com.example.fitnessnearme;

public class FitnessExercise {

    private String name;
    private String description;
    private int gifResourceId;
    private String repRange;

    public FitnessExercise(String name, String description, int gifResourceId, String repRange) {
        this.name = name;
        this.description = description;
        this.gifResourceId = gifResourceId;
        this.repRange = repRange;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getGifResourceId() {
        return gifResourceId;
    }

    public String getRepRange() {
        return repRange;
    }

    public long getDuration() {
        return 30; // Return the duration in seconds
    }
}
