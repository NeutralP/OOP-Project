package model;

public class Person_King2 extends Person {
    private String nienhieu;
    private String date;
    private String mienhieu;
    private String thuyhieu;
    private String thethu;
    private String period;
    private String description;
    private String url;

    public String getMienhieu() {
        return mienhieu;
    }

    public void setMienhieu(String mienhieu) {
        this.mienhieu = mienhieu;
    }

    public String getThuyhieu() {
        return thuyhieu;
    }

    public void setThuyhieu(String thuyhieu) {
        this.thuyhieu = thuyhieu;
    }

    public String getThethu() {
        return thethu;
    }

    public void setThethu(String thethu) {
        this.thethu = thethu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person_King2() {
        super();
    }

    public String getNienhieu() {
        return nienhieu;
    }

    public String getDate() {
        return date;
    }

    public void setNienhieu(String nienhieu) {
        this.nienhieu = nienhieu;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Person_King2(String name) {
        super(name);
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
