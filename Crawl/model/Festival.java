package model;

public class Festival extends HistoricalObject {
    private String date;
    private String location;
    private String name;
    private String firstDate;
    private String relatedNvat;
    private String Note;

    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getRelatedNvat() {
        return relatedNvat;
    }

    public void setRelatedNvat(String relatedNvat) {
        this.relatedNvat = relatedNvat;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public Festival() {
        super();
    }
}
