package model;

import java.util.ArrayList;
import java.util.List;

public class Event extends HistoricalObject {
    private String name;
    private String startYear;
    private String endYear;
    private String trieuDai;
    private String tieuTrieuDai;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getTrieuDai() {
        return trieuDai;
    }

    public void setTrieuDai(String trieuDai) {
        this.trieuDai = trieuDai;
    }

    public String getTieuTrieuDai() {
        return tieuTrieuDai;
    }

    public void setTieuTrieuDai(String tieuTrieuDai) {
        this.tieuTrieuDai = tieuTrieuDai;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }

    List<String> info = new ArrayList<String>();

    public Event() {
        super();
    }

    public Event(String name, String startYear, String endYear, String trieuDai, String tieuTrieuDai) {
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
        this.trieuDai = trieuDai;
        this.tieuTrieuDai = tieuTrieuDai;
    }
}
