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
    private String grade; // grade
    private String id; // student ID - 6 digit

    // pre: none
    // post: initializes Student object
    public Student(String first, String last, String grade, String id) {
        this.first = first;
        this.last = last;
        this.grade = grade;
        this.id = id;
    }

    // pre: none
    // post: returns a string representation of the student object
    @Override
    public String toString() {
        return "Student{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", grade='" + grade + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    // pre: none
    // post: returns student's full name
    public String getFull() {
        return first + " " + last;
    }

    // pre: none
    // post: returns first name
    public String getFirst() {
        return first;
    }

    // pre: none
    // post: returns last name
    public String getLast() {
        return last;
    }

    // pre: none
    // post: returns grade
    public String getGrade() {
        return grade;
    }

    // pre: none
    // post: returns ID
    public String getId() {
        return id;
    }
}
