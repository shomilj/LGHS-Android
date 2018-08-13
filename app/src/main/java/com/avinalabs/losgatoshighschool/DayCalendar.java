//
// DayCalendar.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// DayCalendar - holds a black/orange day calendar
//

package com.avinalabs.losgatoshighschool;

import android.content.Context;
import java.util.ArrayList;

public class DayCalendar {

    private ArrayList<DayType> types;

    // pre: none
    // post: initializes DayCalendar
    // context is passed to read from shared preferences
    public DayCalendar(Context context) {
        types = new ArrayList<>();

        String file = null;//FileUtil.readFromFile(FileUtil.FILE_CALENDAR, context);
        if (file == null) {
            System.out.println("Empty calendar file");
            return;
        }
        parseFile(file);
    }

    // pre: none
    // post: parses the file from the string into the day calendar
    private void parseFile(String file) {
        try {
            String[] rows = file.split("\r");
            String[][] table = new String[rows.length][3];
            for (int i = 0; i < rows.length; i++) {
                String[] cols = rows[i].split("\t");
                table[i] = cols;
            }

            for (int i = 0; i < table[0].length; i++) {
                String today = table[1][i];
                String tomorrow = table[2][i];
                ArrayList<String> dates = new ArrayList<>();
                for (int j = 3; j < table.length; j++) {
                    if (i < table[j].length && table[j][i] != null && table[j][i].length() == 10) {
                        dates.add(table[j][i]);
                    }
                }
                DayType day = new DayType(today, tomorrow, dates);
                types.add(day);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("AN ERROR OCCURED!! DAYCALENDAR");
            System.out.println(e);
        }
    }

    // pre: none
    // post: returns today's day type
    public DayType getToday() {
        for (DayType type : types) {
            if (type.checkToday())
                return type;
        }
        return null;
    }


    // pre: none
    // post: returns tomorrow's day type
    public DayType getTomorrow() {
        for (DayType type : types) {
            if (type.checkTomorrow())
                return type;
        }
        return null;
    }

    // pre: none
    // post: returns the text color ID for the day type
    public int getTextColorID() {
        DayType current = getCurrent();
        if (current != null && current.isBlackDay()) return R.color.colorPrimary;
        return R.color.black;
    }

    // pre: none
    // post: returns the background color ID for the day type
    public int getBackgroundColorID() {
        DayType current = getCurrent();
        if (current != null && current.isBlackDay()) return R.color.black;
        return R.color.colorPrimary;
    }

    // pre: none
    // post: returns either today's or tomorrow's day type, or null if neither exists
    public DayType getCurrent() {
        if (getToday() != null) return getToday();
        if (getTomorrow() != null) return getTomorrow();
        return null;
    }

    // pre: null
    // post: returns description
    public String getDescription() {
        DayType today = getToday();
        if (today != null) return today.getTodayDescription();
        DayType tomorrow = getTomorrow();
        if (tomorrow != null) return tomorrow.getTomorrowDescription();
        return null;
    }

    // pre: none
    // post: if today (or tomorrow, if today isn't a school day) is a black day, then return true.
    public boolean isNextSchoolDayBlack() {
        if (isSchoolDay()) {
            if (getToday().isBlackDay()) return true;
        } else {
            if (getTomorrow().isBlackDay()) return true;
        }
        return false;
    }

    // pre: none
    // post: returns true if today is a school day
    public boolean isSchoolDay() {
        return (getToday() != null);
    }
}
