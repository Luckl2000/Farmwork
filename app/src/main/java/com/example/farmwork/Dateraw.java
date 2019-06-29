package com.example.farmwork;


public class Dateraw {
    private int id;
    private String From;
    private String Until;
    private String Farm;
    private String DateID;
    private Integer Index;
    private Integer Klick;
   // private LinearLayout Linear;





    private Long TimestampID;



    public Dateraw() {

    }

    public Dateraw(int id, String from, String until, String farm, String dateID, Integer index,
                   Integer klick, Long timestampID) {
        this.id = id;
        this.From = from;
        this.Until = until;
        this.Farm = farm;
        this.DateID = dateID;
        this.Index = index;
        this.Klick = klick;

        this.TimestampID = timestampID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getUntil() {
        return Until;
    }

    public void setUntil(String until) {
        Until = until;
    }

    public String getFarm() {
        return Farm;
    }

    public void setFarm(String farm) {
        Farm = farm;
    }

    public String getDateID() {
        return DateID;
    }

    public void setDateID(String dateID) {
        DateID = dateID;
    }

    public Integer getIndex() {
        return Index;
    }

    public void setIndex(Integer index) {
        Index = index;
    }

    public Integer getKlick() {
        return Klick;
    }

    public void setKlick(Integer klick) {
        Klick = klick;
    }



    public Long getTimestampID() {
        return TimestampID;
    }

    public void setTimestampID(Long timestampID) {
        TimestampID = timestampID;
    }


}
