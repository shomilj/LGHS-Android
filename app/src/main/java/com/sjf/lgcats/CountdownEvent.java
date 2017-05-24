package com.sjf.lgcats;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by shomil on 5/21/17.
 */

public class CountdownEvent {

    private String description;
    private String audience;
    private Date eventDate;
    private boolean isLongWeekend;

    public CountdownEvent(String description, String audience, Date eventDate) {
        this.description = description;
        this.audience = audience;
        this.eventDate = eventDate;
        isLongWeekend = (description.contains("LW"));
    }

    public int getDaysFromToday() {
        Date now = new Date();
        long diff = eventDate.getTime() - now.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String getDateString() {
        DateFormat df = new SimpleDateFormat("EEEE, MMM dd, yyyy");
        return (df.format(eventDate));
    }

    public String getDescription() {
        return description;
    }

    public String getAudience() {
        return audience;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public boolean isLongWeekend() {
        return isLongWeekend;
    }

    @Override
    public String toString() {
        return "CountdownEvent{" +
                "description='" + description + '\'' +
                ", audience='" + audience + '\'' +
                ", eventDate=" + eventDate +
                ", isLongWeekend=" + isLongWeekend +
                '}';
    }
}
