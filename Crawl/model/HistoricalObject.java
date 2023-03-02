package model;

public abstract class HistoricalObject {
    static int id_counter = 1;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HistoricalObject() {
        this.id = id_counter;
        id_counter++;
    }
}
