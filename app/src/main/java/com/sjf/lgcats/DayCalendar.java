package com.sjf.lgcats;

import android.content.Context;

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
            System.out.println("AN ERROR OCCURED!!");
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

    public String getDescription() {
        DayType today = getToday();
        if (today != null) return today.getTodayDescription();
        DayType tomorrow = getTomorrow();
        if (tomorrow != null) return tomorrow.getTomorrowDescription();
        return "";
    }

    public boolean isSchoolDay() {
        return (getToday() != null);
    }
}
