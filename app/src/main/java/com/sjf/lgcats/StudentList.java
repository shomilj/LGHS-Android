package com.sjf.lgcats;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.prefs.Preferences;

/**
 * Created by shomil on 5/21/17.
 */

public class StudentList {

    private ArrayList<Student> list;

    public StudentList(Context context) {
        list = new ArrayList<>();
        String file = FileUtil.readFromFile(FileUtil.FILE_LOGINS, context);

        if (file == null) {
            System.out.println("Empty file");
            return;
        }

        try {
            String[] rows = file.split("\r");
            for (String row : rows) {
                String[] cells = row.split("\t");
                if (cells.length > 3) {
                    list.add(new Student(cells[2], cells[1], cells[3], cells[0]));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR STUDENT LIST");
            System.out.println(e);
        }
    }

    public Student getStudent(String id) {
        for (Student student : list) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public boolean checkLogin(String last, String id) {
        for (Student student : list) {
            if (student.getLast().equalsIgnoreCase(last) && student.getId().equalsIgnoreCase(id))
                return true;
        }
        return false;
    }

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

}
