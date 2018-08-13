//
// Club.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// Club - a simple club data object, holds various aspects of a club
//

package com.avinalabs.losgatoshighschool;

import java.io.Serializable;

public class Club implements Serializable {

    // references to the list view items for each cell
    public static String lv_item_1_key = "Item 1";
    public static String lv_item_2_key = "Item 2";

    // this is the key used when passing data between screens
    public static String intent_key = "Club";

    // club information
    private String name;
    private String day;
    private String time;
    private String location;
    private String president;
    private String vicePresident;
    private String advisor;
    private String email;

    // pre: none
    // post: initializes the club object
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

    // pre: none
    // post: returns club name
    public String getName() {
        return name;
    }

    // pre: none
    // post: returns club meeting day
    public String getDay() {
        return day;
    }

    // pre: none
    // post: returns club meeting time (i.e. "Lunch")
    public String getTime() {
        return time;
    }

    // pre: none
    // post: returns club meeting location
    public String getLocation() {
        return location;
    }

    // pre: none
    // post: returns club president
    public String getPresident() {
        return president;
    }

    // pre: none
    // post: returns club VP
    public String getVicePresident() {
        return vicePresident;
    }

    // pre: none
    // post: returns advisor name
    public String getAdvisor() {
        return advisor;
    }

    // pre: none
    // post: returns email associated w/ club
    public String getEmail() {
        return email;
    }

    // pre: none
    // post: returns club info in readable format
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
