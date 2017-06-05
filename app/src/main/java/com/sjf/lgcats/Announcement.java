

package com.sjf.lgcats;

public class Announcement {

    private String date;
    private String content;

    public Announcement(String date, String content) {
        this.date = date;
        this.content = content.replaceAll("<line>", "\r\n");
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
