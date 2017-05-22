//
//  Hotline.java
//  LG CATS
//
//  Created by <name of developer> on <date>
//  Copyright © SJF Technologies, Inc. All rights reserved.
//
/*
A Hotline contains a title & description.

Attributes:
private String title; // i.e. "LG Police Department"
private String description;

Methods:
onCreate(Bundle savedInstanceState) // runs when the view is created
getter methods for title/description
*/

package com.sjf.lgcats;

public class Hotline {

    private String title;
    private String description;

    public Hotline(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Hotline{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}