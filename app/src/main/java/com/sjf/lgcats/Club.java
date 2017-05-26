package com.sjf.lgcats;

import java.io.Serializable;

/**
 * Created by Cassandra on 5/22/17.
 */

public class Club implements Serializable {
    private String name;

    // Meeting details
    private String day;
    private String time;
    private String location;

    // People
    private String president;
    private String vicePresident;
    private String advisor;

    private String email;

    public Club(String name, String day, String time, String location, String president, String vicePresident, String advisor, String email) {
        this.name = name;
        this.day = day;
        this.time = time;
        this.location = location;
        this.president = president;
        this.vicePresident = vicePresident;
        this.advisor = advisor;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getPresident() {
        return president;
    }

    public String getVicePresident() {
        return vicePresident;
    }

    public String getAdvisor() {
        return advisor;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Club{" +
                "name='" + name + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", president='" + president + '\'' +
                ", vicePresident='" + vicePresident + '\'' +
                ", advisor='" + advisor + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
