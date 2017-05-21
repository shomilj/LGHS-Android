package com.sjf.lgcats;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by shomil on 5/20/17.
 */

public class DayType {

    private String todayDescription;
    private String tomorrowDescription;
    private ArrayList<String> dates;

    public DayType(String todayDescription, String tomorrowDescription, ArrayList<String> dates) {
        this.todayDescription = todayDescription;
        this.tomorrowDescription = tomorrowDescription;
        this.dates = dates;
    }

    public boolean isBlackDay() {
        return (this.todayDescription.toLowerCase().contains("black"));
    }

    public boolean checkToday() {
        String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        return (dates.contains(date));
    }

    public boolean checkTomorrow() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        String tomorrow = sdf.format(c.getTime());
        return (dates.contains(tomorrow));
    }

    public String getTodayDescription() {
        return todayDescription;
    }

    public String getTomorrowDescription() {
        return tomorrowDescription;
    }

    @Override
    public String toString() {
        return "DayType{" +
                "todayDescription='" + todayDescription + '\'' +
                ", tomorrowDescription='" + tomorrowDescription + '\'' +
                ", dates=" + dates +
                '}';
    }
}
