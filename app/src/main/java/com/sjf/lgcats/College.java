package com.sjf.lgcats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Harry Wang on 5/15/17.
 *
 * Contains College objects, which have names, blah blah blah
 *
 * @author  Shomil Jain
 * @author  Quintin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
 */

public class College {

    private String name;
    private String time;
    private String location;
    private Date date;

    public College(String name, String time, String location, String date) {
        this.name = name;
        this.time = time;
        this.location = location;
        try {
            this.date = new SimpleDateFormat("MM/dd/yyyy").parse(date);
        } catch (ParseException e) {
            this.date = new Date();
        }
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public boolean isUpcoming() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        String today = fmt.format(new Date());
        String visit = fmt.format(date);

        // NOTE: THIS SHOULD BE <= 0. FOR TESTING PURPOSES (To make sure there's dummy data) change it to >= 0.
        return today.compareTo(visit) <= 0;
    }

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

    public String getDateString() {
        SimpleDateFormat fmt = new SimpleDateFormat("EEEE, MMM d");
        String dateString = fmt.format(date);
        return dateString + " @ " + time;
    }

}
