public class Event {
    private int eventId;
    private String title;
    private String date;
    private String venue;
    private String image;
    private double price;

    public Event(int eventId, String title, String date, String venue, String image, double price) {
        this.eventId = eventId;
        this.title = title;
        this.date = date;
        this.venue = venue;
        this.image = image;
        this.price = price;
    }

    public int getEventId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }
}
