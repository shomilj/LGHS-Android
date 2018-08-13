//
// UserUtils.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// UserUtils - user util methods
//

package com.avinalabs.losgatoshighschool;

import android.content.SharedPreferences;

public class UserUtils {

    public static String KEY_STUDENT = "Student";
    public static String KEY_PARENT = "Parent";
    public static String KEY_TEACHER = "Teacher";
    public static String REF_USER_TYPE = "UserType";
    public static String REF_USERNAME = "Username";

    public static void setStudent(SharedPreferences pref, Student std)
    {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("id", std.getId());
        editor.putString("firstName", std.getFirst());
        editor.putString("lastName", std.getLast());
        editor.putString("email", std.getEmail());
        editor.putString("gradYear", std.getGradClass());
        setUserType(KEY_STUDENT, editor);
        System.out.println("Email, fname"+std.getEmail()+", "+std.getFirst());
    }

    public static void setUserType(String type, SharedPreferences.Editor editor) {
        editor.putString(REF_USER_TYPE, type);
        editor.commit();
    }

    public static Student getStudent(SharedPreferences prefs)
    {
        return new Student(
                prefs.getString("firstName", "fname"),
                prefs.getString("lastName", "lname"),
                prefs.getString("email", "email"),
                prefs.getString("id", "00000"),
                prefs.getString("gradYear", "1000"));
    }

    public static String getUserType(SharedPreferences prefs) {
        return (prefs.getString(REF_USER_TYPE, null));
    }

    public static boolean isStudent(SharedPreferences prefs) {
        return (prefs.getString(REF_USER_TYPE, "").equals(KEY_STUDENT));
    }

    public static boolean isTeacher(SharedPreferences prefs) {
        return (prefs.getString(REF_USER_TYPE, "").equals(KEY_TEACHER));
    }

    public static boolean isParent(SharedPreferences prefs) {
        return (prefs.getString(REF_USER_TYPE, "").equals(KEY_PARENT));
    }

    public static void logout(SharedPreferences.Editor editor) {
        editor.remove(REF_USER_TYPE);
        editor.remove(REF_USERNAME);
        editor.commit();
    }
}
