//
// Student.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// Student - holds a Student object
//

package com.sjf.lgcats;

public class Student {

    private String first; // first name
    private String last; // last name
    private String gradClass; // class of 2020, etc.
    private String id; // student ID - 6 digit
    private String email;

    public Student(String first, String last, String gradClass, String id, String email) {
        this.first = first;
        this.last = last;
        this.gradClass = gradClass;
        this.id = id;
        this.email = email;
    }

    public String getFull() { return first + " " + last; }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getGradClass() {
        return gradClass;
    }

    public void setGradClass(String gradClass) {
        this.gradClass = gradClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", gradClass='" + gradClass + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
