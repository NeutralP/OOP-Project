package model;

import java.util.List;

public class Dynasty extends HistoricalObject {
    private String name;
    private String kingname;
    private String lunardate;
    private String date;
    private String chinesename;
    private String chineseperiod;
    private String description;
    private List<String> NameList;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getKingname() {
        return kingname;
    }

    public String getLunardate() {
        return lunardate;
    }

    public String getDate() {
        return date;
    }

    public String getChinesename() {
        return chinesename;
    }

    public String getChineseperiod() {
        return chineseperiod;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKingname(String kingname) {
        this.kingname = kingname;
    }

    public void setLunardate(String lunardate) {
        this.lunardate = lunardate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setChinesename(String chinesename) {
        this.chinesename = chinesename;
    }

    public void setChineseperiod(String chineseperiod) {
        this.chineseperiod = chineseperiod;
    }

    public List<String> getNameList() {
        return NameList;
    }

    public void setNameList(List<String> namelist) {
        NameList = namelist;
    }

}
