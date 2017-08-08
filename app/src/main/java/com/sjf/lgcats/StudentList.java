//
// StudentList.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// StudentList - holds a list of students at LGHS.
//

package com.sjf.lgcats;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;

public class StudentList {

    private ArrayList<Student> list;

    // pre: none
    // post: fetches student logins from file, adds them to list
    public StudentList(Context context) {
        list = new ArrayList<>();

        // reads content of TSV file into this string
        String file = FileUtil.readFromFile(FileUtil.FILE_LOGINS, context);

        // if the file doesn't exist then end method
        if (file == null) {
            System.out.println("Empty file");
            return;
        }

        // try splitting the array up by linefeed and tab
        // most of this code has error-checking built in to prevent crashes at runtime
        // in the case of spreadsheet errors
        try {
            String[] rows = file.split("\r");
            for (String row : rows) {
                String[] cells = row.split("\t");
                if (cells.length > 4) {
                    // here's a row of 5 cells
                    // create a new student & add to list
                    list.add(new Student(cells[2], cells[1], cells[3], cells[0], cells[4]));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR STUDENT LIST");
            System.out.println(e);
        }
    }

    // pre: none
    // post: returns the student with given id
    public Student getStudent(String id) {
        for (Student student : list) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // pre: none
    // post: returns true if the lastname and the id match (student login verification)
    public boolean checkLogin(String email) {
        for (Student student : list) {
            if (student.getEmail().toLowerCase().equals(email)) {
                return true;
            }
        }
        return false;
    }

    // pre: none
    // post: gets the current student, if signed in
    // returns null if current student DNE (if user is a parent, for example)
    public Student getCurrentStudent(SharedPreferences prefs) {
        String userType = prefs.getString("UserType", null);
        String studentID = prefs.getString("StudentID", null);
        if (userType != null && userType.equals("Student")) {
            if (studentID != null) {
                return getStudent(studentID);
            }
        }
        return null;
    }

    public Student getStudentFromEmail(String email) {
        for (Student student : list) {
            if (student.getEmail().toLowerCase().equals(email)) {
                return student;
            }
        }
        return null;
    }
}
