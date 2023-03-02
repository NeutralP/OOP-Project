package model;

public class Person_WarHero extends Person {
    private String date;
    private String ethnicity;
    private String released_year;
    private String birthplace;
    private String achivement;
    private String description;

    public Person_WarHero() {
        super();
    }

    public Person_WarHero(String name) {
        super(name);
    }

    public String getDate() {
        return date;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public String getReleased_year() {
        return released_year;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public String getAchivement() {
        return achivement;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public void setReleased_year(String released_year) {
        this.released_year = released_year;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public void setAchivement(String achivement) {
        this.achivement = achivement;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
