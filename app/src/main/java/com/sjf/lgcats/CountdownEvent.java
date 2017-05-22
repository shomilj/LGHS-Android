package com.sjf.lgcats;

import java.util.Date;

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
