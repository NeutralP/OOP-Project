package model;

public abstract class Person extends HistoricalObject {
    private String name;

    public Person() {

    }

    public Person(String name) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
