package com.sjf.lgcats;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by shomil on 6/4/17.
 */

public class UserUtils {

    public static String KEY_STUDENT = "Student";
    public static String KEY_PARENT = "Parent";
    public static String KEY_TEACHER = "Teacher";
    public static String REF_USER_TYPE = "UserType";
    public static String REF_USERNAME = "Username";

    public static void setUserType(String type, SharedPreferences.Editor editor) {
        editor.putString(REF_USER_TYPE, type);
        editor.commit();
    }

    // for a student, the username is the student ID number
    // otherwise, the username is null
    public static void setUsername(String username, SharedPreferences.Editor editor) {
        editor.putString(REF_USERNAME, username);
        editor.commit();
    }

    public static String getUserType(SharedPreferences prefs) {
        return (prefs.getString(REF_USER_TYPE, null));
    }

    public static String getUsername(SharedPreferences prefs) {
        return (prefs.getString(REF_USERNAME, null));
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
