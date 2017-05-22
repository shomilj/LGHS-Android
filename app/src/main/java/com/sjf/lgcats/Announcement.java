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

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
