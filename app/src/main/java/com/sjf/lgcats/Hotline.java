//
// Hotline.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// Hotline - holds a hotline object. A hotline is an important phone number and contains a title/descriptiion
//

package com.sjf.lgcats;

public class Hotline {

    // i.e. SafeRides
    private String title;

    // full content of the description
    private String description;

    // pre: none
    // post: initializes a new Hotline object with title/description
    public Hotline(String title, String description) {
        this.title = title;

        // on the server linefeeds in strings are represented as <line>
        // this replaces those with actual linefeeds
        this.description = description.replaceAll("<line>", "\r\n");
    }

    // pre: none
    // post: returns title
    public String getTitle() {
        return title;
    }

    // pre: none
    // post: returns description
    public String getDescription() {
        return description;
    }

    // pre: none
    // post: returns a string representation of Hotline object
    @Override
    public String toString() {
        return "Hotline{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}