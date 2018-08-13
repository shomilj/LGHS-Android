//
// College.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// College - holds various data associated with a college visit at LGHS
//

package com.avinalabs.losgatoshighschool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class College {

    private String name;
    private String time;
    private String location;
    private Date date;

    // pre: none
    // post: sets up the college object
    public College(String name, String time, String location, String date) {
        this.name = name;
        this.time = time;
        this.location = location;

        // parses the date object from a string to a date
        try {
            this.date = new SimpleDateFormat("MM/dd/yyyy").parse(date);
        } catch (ParseException e) {
            this.date = new Date();
        }
    }

    // pre: none
    // post: returns name
    public String getName() {
        return name;
    }

    // pre: none
    // post: returns time
    public String getTime() {
        return time;
    }

    // pre: none
    // post: returns location
    public String getLocation() {
        return location;
    }

    // pre: none
    // post: returns date
    public Date getDate() {
        return date;
    }

    // pre: none
    // post: returns true if this visit is upcoming, false otherwise
    public boolean isUpcoming() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        String today = fmt.format(new Date());
        String visit = fmt.format(date);
        return today.compareTo(visit) <= 0;
    }

    // pre: none
    // post: returns college in readable String format
    @Override
    public String toString() {
        return "College{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", isUpcoming='" + isUpcoming() + '\'' +
                '}';
    }

    // pre: none
    // post: returns the date in a string format for the listview
    public String getDateString() {
        SimpleDateFormat fmt = new SimpleDateFormat("EEEE, MMM d");
        String dateString = fmt.format(date);
        return dateString + " @ " + time;
    }

}
