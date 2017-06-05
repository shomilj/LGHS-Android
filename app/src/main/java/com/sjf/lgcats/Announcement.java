//
// Announcement.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// Announcement - simple data structure to hold a date/content for an announcement
//

package com.sjf.lgcats;

public class Announcement {

    // MM/dd/yy format
    private String date;

    // the acutal announcement content (multiple lines long)
    private String content;

    // pre: none
    // post: sets the date/content
    public Announcement(String date, String content) {
        this.date = date;
        // this replaces all occurrences of <line> with an actual linefeed character
        // data downloaded from the server is formatted so that <line> is a linefeed
        this.content = content.replaceAll("<line>", "\r\n");
    }

    // pre: none
    // post: returns the date
    public String getDate() {
        return date;
    }

    // pre: none
    // post: returns the content
    public String getContent() {
        return content;
    }

    // pre: none
    // post: returns a string representation of the announcement
    @Override
    public String toString() {
        return "Announcement{" +
                "date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
