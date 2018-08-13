//
// CountdownEvent.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// CountdownEvent - holds a countdown event object
//

package com.avinalabs.losgatoshighschool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CountdownEvent {

    private String description;
    private String audience;
    private Date eventDate;
    private boolean isLongWeekend;

    // pre: none
    // post: initializes CountdownEvent
    public CountdownEvent(String description, String audience, Date eventDate) {
        this.description = description;
        this.audience = audience;
        this.eventDate = eventDate;
        isLongWeekend = (description.contains("LW"));
    }

    // pre: none
    // post: returns days to countdown event
    public int getDaysFromToday() {
        Date now = new Date();
        long diff = eventDate.getTime() - now.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    // pre: none
    // post: returns date as a string
    public String getDateString() {
        DateFormat df = new SimpleDateFormat("EEEE, MMM dd, yyyy");
        return (df.format(eventDate));
    }

    // pre: none
    // post: returns description
    public String getDescription() {
        return description;
    }

    // pre: none
    // post: returns audience
    public String getAudience() {
        return audience;
    }

    // pre: none
    // post: returns date
    public Date getEventDate() {
        return eventDate;
    }

    // pre: none
    // post: returns true if the event is a long weekend
    public boolean isLongWeekend() {
        return isLongWeekend;
    }

    // pre: none
    // post: returns the event in a readable format
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
