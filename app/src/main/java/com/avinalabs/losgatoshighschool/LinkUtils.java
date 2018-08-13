//
// LinkUtils.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// LinkUtils - holds keys to each link & getLink method
//
/*
Each key is a reference to a cell in a Google Sheet which holds keys and URLs associated with keys.
This is to allow remote modification of links without having to update the application through the play store.
 */

package com.avinalabs.losgatoshighschool;

import android.content.Context;
import java.util.HashMap;

public class LinkUtils {

    // these links are hardcoded, since they are downloaded before the links file itself has been downloaded
    public static final String LINK_F_LINKS = "https://docs.google.com/spreadsheets/d/1hb1KOBwpJxjj3atYJ2ke0yPHml8cFLW2IDlfW9voEM4/export?format=tsv";
    public static final String LINK_B_LINKS = "https://docs.google.com/spreadsheets/d/19vfUmdECU436hFn057MummlDcn-dH-TOK9GX9vff_lU/export?format=tsv";
    public static final String LINK_RESOURCES = "https://docs.google.com/spreadsheets/d/1rlH471GSvefe_D4qQ8hQhX5UZ0001fiTrPHBk4rR-lI/export?format=tsv";

    public static final String GENERAL_HOME = "General_Home";

    public static final String PORTAL_AERIES = "Portal_Aeries";
    public static final String PORTAL_NAVIANCE = "Portal_Naviance";
    public static final String PORTAL_CANVAS = "Portal_Canvas";

    public static final String ACTIVITIES_ATHLETICS = "Activities_Athletics";
    public static final String ACTIVITIES_MUSIC = "Activities_Music";
    public static final String ACTIVITIES_THEATER = "Activities_Theater";
    public static final String ACTIVITIES_LEADERSHIP = "Activities_Leadership";

    public static final String COURSE_SEQUENCE = "Course_Sequence";
    public static final String COURSE_DESCRIPTIONS = "Course_Descriptions";
    public static final String COURSE_AP = "Course_AP";
    public static final String COURSE_GRADREQS = "Course_GradReqs";
    public static final String COURSE_ELECTIVES = "Course_Electives";
    public static final String COURSE_UCCSU = "Course_UCCSU";

    public static final String POLICY_GRADING = "Policy_Grading";
    public static final String POLICY_GUIDANCE = "Policy_Guidance";
    public static final String POLICY_STUDENTCOURSECHANGE = "Policy_StudentCourseChange";
    public static final String POLICY_INTEGRITY = "Policy_Integrity";
    public static final String POLICY_ATTENDANCE = "Policy_Attendance";
    public static final String POLICY_CODE = "Policy_Code";
    public static final String POLICY_DRESS = "Policy_Dress";
    public static final String POLICY_TECH = "Policy_Tech";
    public static final String POLICY_PARENT = "Policy_Parent";

    public static final String STAFF_PRINCIPAL = "Staff_Principal";
    public static final String STAFF_ADMINISTRATION = "Staff_Administration";
    public static final String STAFF_DIRECTORY = "Staff_Directory";
    public static final String STAFF_TEACHERPAGES = "Staff_TeacherPages";
    public static final String STAFF_TECHNOLOGY = "Staff_Technology";

    public static final String GL_FRESHMEN = "GL_Freshmen";
    public static final String GL_SOPHOMORE = "GL_Sophomore";
    public static final String GL_JUNIOR = "GL_Junior";
    public static final String GL_SENIOR = "GL_Senior";

    public static final String PHS_COLLEGECAREER = "PHS_CollegeCareer";
    public static final String PHS_NAVIANCE = "PHS_Naviance";

    public static final String AE_MIDDLE = "AE_Middle";
    public static final String AE_COLLEGEADV = "AE_CollegeAdv";
    public static final String AE_ALTERNATIVE = "AE_Alternative";
    public static final String AE_SVCTE = "AE_SVCTE";
    public static final String AE_ENGLANG = "AE_EngLang";

    public static final String SE_STAFF = "SE_Staff";
    public static final String SE_SERVICES = "SE_Services";
    public static final String SE_IEP = "SE_IEP";
    public static final String SE_POSTSECONDARY = "SE_PostSecondary";
    public static final String SE_WORKABILITY = "SE_WorkAbility";

    public static final String LIBRARY_HOME = "Library_Home";
    public static final String LIBRARY_INFO = "Library_Info";
    public static final String LIBRARY_TEXTBOOK = "Library_Textbook";
    public static final String LIBRARY_TESTING = "Library_Testing";
    public static final String LIBRARY_JOBS = "Library_Jobs";

    public static final String PARENTS_HOME = "Parents_Home";
    public static final String PARENTS_CASSY = "Parents_CASSY";
    public static final String PARENTS_NMF = "Parents_NMF";
    public static final String PARENTS_HSC = "Parents_HSC";
    public static final String PARENTS_ACTT = "Parents_ACTT";
    public static final String PARENTS_CORNERSTONE = "Parents_Cornerstone";
    public static final String PARENTS_CONTINUUM = "Parents_Continuum";
    public static final String PARENTS_CASA = "Parents_CASA";

    public static final String ENROLLMENT_TRANSCRIPT = "Enrollment_Transcript";
    public static final String ENROLLMENT_REGISTRATION = "Enrollment_Registration";
    public static final String ENROLLMENT_NEW = "Enrollment_New";
    public static final String ENROLLMENT_FRESHMENREG = "Enrollment_FreshmenReg";
    public static final String ENROLLMENT_CURRENTREG = "Enrollment_CurrentReg";

    public static final String GN_NEWS = "GN_News";
    public static final String GN_CALENDAR = "GN_Calendar";
    public static final String GN_COORDINATORS = "GN_Coordinators";
    public static final String GN_ADMISSIONS = "GN_Admissions";
    public static final String GN_DECORATIONS = "GN_Decorations";
    public static final String GN_FACILITIES = "GN_Facilities";
    public static final String GN_FOOD = "GN_Food";
    public static final String GN_INDOOR = "GN_Indoor";
    public static final String GN_OUTDOOR = "GN_Outdoor";
    public static final String GN_SENIOR = "GN_Senior";
    public static final String GN_KISS = "GN_Kiss";
    public static final String GN_STEERING = "GN_Steering";
    public static final String GN_TREASURER = "GN_Treasurer";
    public static final String GN_VOLUNTEERS = "GN_Volunteers";
    public static final String GN_JUNIOR = "GN_Junior";
    public static final String GN_SOPHOMORE = "GN_Sophomore";


    public enum Config {
        linkResources,
        linkBackend,
        linkFrontend
    }

    public enum Backend {
        dailyAnnouncementsFeed,
        calendarMainFeed,
        calendarSportsFeed,
        calendarCollegesFeed,
        calendarClubsFeed,
        adminClubListTemplate,
        adminTeacherRosterTemplate,
        adminStudentRosterTemplate,
        adminDayCalendarTemplate,
        eventRosterTemplate
    }

    public enum Frontend {
        newsFeed,
        campusMap,
        canvas,
        dayCalendar,
        dailyAnnouncementMessageSubmit,
        dailyAnnouncementJokeSubmit,
        bellSchedule,
        twitterFeed,
        termsAndConditions,
        wetip,
        lgpdWebsite,
        cassyForm,
        additionalHotlines,
        studentDirectory,
        staffDirectory,
        tutoringProgram,
        canvasSupport,
        passwordPortal,
        wifiSupport,
        aeries,
        weather,
        attendance
    }

    // This takes a Google Drive common sharing link (user-facing) and converts it into a DIRECT DOWNLOAD link for tsv files
    // This MAY or MAY NOT work.
    // If google changes how they use links, this will fail.
    // TODO: Find a better algorithm for determining the KEY of a spreadsheet
    // Paste that key into the direct download format below.
    static String parseGoogleLink(String rawLink) {
        String[] arrayOfPieces = rawLink.split("/");
        String key = longestInArray(arrayOfPieces);
        return "https://docs.google.com/spreadsheets/d/" + key + "/export?format=tsv";
    }

    // Comp sci 101 (AKA Mr. B's first array lab)
    private static String longestInArray(String[] array) {
        int index = 0;
        int elementLength = array[0].length();
        for(int i = 1; i< array.length; i++) {
            if(array[i].length() > elementLength) {
                index = i; elementLength = array[i].length();
            }
        }
        return array[index];
    }

    // Returns the link associated with key.
    public static String getLink(Enum enumObj, Context context) {
        String name = enumObj.getClass().getName();
        name = name.substring(name.lastIndexOf('.')+1);
        System.out.println("name:"+name+", Enum:"+enumObj.toString());

        name = name.substring(name.lastIndexOf("$")+1);
        System.out.println("FileName:"+name+", Enum:"+enumObj.toString());
        String file = FileUtil.readFromFile(name+".txt", context);

        HashMap<String, String> links = new HashMap<>();

        String[] list = file.split("\r");
        for (String s : list) {
            String[] cols = s.split("\t");
            if (cols.length > 2) {
                links.put(cols[1], cols[2]);
                System.out.println("cols[1]"+cols[1]+", "+cols[2]);
            }
        }

        return links.get(enumObj.toString());
    }

}
