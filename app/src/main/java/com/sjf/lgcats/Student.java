package com.sjf.lgcats;

/**
 * Created by shomil on 5/21/17.
 */

public class Student {

    private String first; // first name
    private String last; // last name
    private String grade; // grade
    private String id; // student ID - 6 digit

    public Student(String first, String last, String grade, String id) {
        this.first = first;
        this.last = last;
        this.grade = grade;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", grade='" + grade + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getFull() {
        return first + " " + last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getGrade() {
        return grade;
    }

    public String getId() {
        return id;
    }
}
