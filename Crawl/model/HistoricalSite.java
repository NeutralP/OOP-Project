package model;

public class HistoricalSite extends HistoricalObject {
    private String name;
    private String date;
    private String location;
    private String details;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public HistoricalSite(String name, String date, String location, String details) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.details = details;
    }

    public HistoricalSite() {
        super();
    }
}
