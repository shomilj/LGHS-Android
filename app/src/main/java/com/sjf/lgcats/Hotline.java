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

public class Hotline
{
    private String title;
    private String description;

    public Hotline(String title, String Description)
    {
        this.title = title;
        this.description = description;
    }

    //pre: none
    //post: returns title as String
    public String getTitle()
    {
        return this.title;
    }

    //pre: none
    //post: returns description as String
    public String getDescription()
    {
        return this.description;
    }
}