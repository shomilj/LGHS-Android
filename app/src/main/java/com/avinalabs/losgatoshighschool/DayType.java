//
// DayType.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// DayType - holds a type of day (i.e. "Black Day", "Finals Day")
//

package com.avinalabs.losgatoshighschool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DayType {

    // string to display for today
    private String todayDescription;

    // string to display for tomorrow
    private String tomorrowDescription;

    // list of dates as strings
    private ArrayList<String> dates;

    // pre: none
    // post: initializes DayType object
    public DayType(String todayDescription, String tomorrowDescription, ArrayList<String> dates) {
        this.todayDescription = todayDescription;
        this.tomorrowDescription = tomorrowDescription;
        this.dates = dates;
    }

    // pre: none
    // post: returns true if this day type is a black day
    public boolean isBlackDay() {
        return (this.todayDescription.toLowerCase().contains("black"));
    }

    // pre: none
    // post: returns true if today is in the list of dates
    public boolean checkToday() {
        String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        return (dates.contains(date));
    }

    // pre: none
    // post: returns true if tomorrow is in the list of dates
    public boolean checkTomorrow() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        String tomorrow = sdf.format(c.getTime());
        return (dates.contains(tomorrow));
    }

    // pre: none
    // post: returns today description
    public String getTodayDescription() {
        return todayDescription;
    }

    // pre: none
    // post: returns tomorrow description
    public String getTomorrowDescription() {
        return tomorrowDescription;
    }

    // pre: none
    // post: returns this object as a string representation
    @Override
    public String toString() {
        return "DayType{" +
                "todayDescription='" + todayDescription + '\'' +
                ", tomorrowDescription='" + tomorrowDescription + '\'' +
                ", dates=" + dates +
                '}';
    }
}
