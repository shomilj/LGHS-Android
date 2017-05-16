package com.sjf.lgcats;

/**
 * Created by QDL on 5/16/17.
 */

public class Announcement {
    private String date;
    private String content;

    public Announcement(String date, String content) {
        this.date = date;
        this.content = content;
    }

    public Announcement() {
        this.date = "";
        this.content = "";
    }

    public String getDate() {
        return this.date;
    }

    public String getContent() {
        return this.content;
    }
}
