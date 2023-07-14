package com.example.fitnessnearme;

public class Event {
    private int eventId;
    private String title;
    private String date;
    private String venue;
    private String imageUrl; // Store the image URL instead of image resource identifier
    private double price;

    public Event(int eventId, String title, String date, String venue, String imageUrl, double price) {
        this.eventId = eventId;
        this.title = title;
        this.date = date;
        this.venue = venue;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
