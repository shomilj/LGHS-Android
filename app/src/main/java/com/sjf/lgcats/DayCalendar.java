package com.sjf.lgcats;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by shomil on 5/20/17.
 */

public class DayCalendar {

    private ArrayList<DayType> types;

    public DayCalendar(Context context) {
        types = new ArrayList<>();

        String file = FileUtil.readFromFile(FileUtil.FILE_CALENDAR, context);
        if (file == null) {
            System.out.println("Empty file");
            return;
        }
        parseFile(file);
    }

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

    public DayType getToday() {
        for (DayType type : types) {
            if (type.checkToday())
                return type;
        }
        return null;
    }

    public DayType getTomorrow() {
        for (DayType type : types) {
            if (type.checkTomorrow())
                return type;
        }
        return null;
    }

    public int getTextColorID() {
        DayType current = getCurrent();
        if (current != null && current.isBlackDay()) return R.color.colorPrimary;
        return R.color.black;
    }

    public int getBackgroundColorID() {
        DayType current = getCurrent();
        if (current != null && current.isBlackDay()) return R.color.black;
        return R.color.colorPrimary;
    }

    public DayType getCurrent() {
        if (getToday() != null) return getToday();
        if (getTomorrow() != null) return getTomorrow();
        return null;
    }

    public String getDescription() {
        DayType today = getToday();
        if (today != null) return today.getTodayDescription();
        DayType tomorrow = getTomorrow();
        if (tomorrow != null) return tomorrow.getTomorrowDescription();
        return null;
    }

    // if today (or tomorrow, if today isn't a school day) is a black day, then return true.
    public boolean isNextSchoolDayBlack() {
        if (isSchoolDay()) {
            if (getToday().isBlackDay()) return true;
        } else {
            if (getTomorrow().isBlackDay()) return true;
        }
        return false;
    }

    public boolean isSchoolDay() {
        return (getToday() != null);
    }
}
