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

package com.sjf.lgcats;

import android.content.Context;
import java.util.HashMap;

public class LinkUtils {

    // these links are hardcoded, since they are downloaded before the links file itself has been downloaded
    public static final String LINK_LINKS = "https://docs.google.com/spreadsheets/d/1SLgWueqyOlvqEvW5ZVi4-nrUzpJAYlikSDlnssoXKGc/export?format=tsv";
    public static final String LINK_HOTLINES = "https://docs.google.com/spreadsheets/d/1cGpoo0sXmSu4vz4d3D6TtmTaaKdkbejvG9LWWrpBKxo/export?format=tsv";
    public static final String LINK_COUNTDOWN = "https://docs.google.com/spreadsheets/d/1oU_gAjgVvUzYs3IJ0-iByAPM4WRodCPUZnCt3XNozK0/export?format=tsv";
    public static final String LINK_CALENDAR = "https://docs.google.com/spreadsheets/d/1DnkOQhamCZkuyPz72aWr8RmgrIQEPNu51pzreLVWlCI/export?format=tsv";
    public static final String LINK_LOGINS = "https://docs.google.com/spreadsheets/d/1WgsrbGMCIK1ardLnqorygSUs0dKksJFNVpiR5TzONV0/export?format=tsv";

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

    public static final String GENERAL_PROFILE = "General_Profile";
    public static final String GENERAL_MAP = "General_Map";
    public static final String GENERAL_DISTRICT = "General_District";
    public static final String GENERAL_BOCALENDAR = "General_BOCalendar";
    public static final String GENERAL_CALENDAR = "General_Calendar";
    public static final String GENERAL_APEXAMSCHEDULE = "General_APExamSchedule";
    public static final String GENERAL_APEXAMINFO = "General_APExamInfo";
    public static final String GENERAL_FINALSCHEDULE = "General_FinalSchedule";
    public static final String GENERAL_TWITTER = "General_Twitter";
    public static final String GENERAL_STRESSTEST = "General_StressTest";

    public static final String HOST_ANNOUNCEMENTS = "Host_Announcements";
    public static final String HOST_COUNTDOWN = "Host_Countdown";
    public static final String HOST_CALENDAR = "Host_Calendar";
    public static final String HOST_HOTLINES = "Host_Hotlines";
    public static final String HOST_COLLEGES = "Host_Colleges";
    public static final String HOST_CLUBS = "Host_Clubs";
    public static final String HOST_JOKEOFDAY = "Host_JokeOfDay";
    public static final String HOST_SUBMITANNOUNCEMENT = "Host_SubmitAnnouncement";
    public static final String HOST_PRIVACY = "Host_Privacy";
    public static final String HOST_MAIL = "Host_Mail";
    public static final String HOST_TERMS = "Host_Terms";
    public static final String HOST_LOGINS = "Host_Logins";
    public static final String HOST_EMAILS = "Host_Emails";
    public static final String HOST_COURSES = "Host_Courses";

    public static final String EG_HOME = "EG_Home";
    public static final String EG_CENTER = "EG_Center";
    public static final String EG_CULTURE = "EG_Culture";
    public static final String EG_HUMOR = "EG_Humor";
    public static final String EG_LOCAL = "EG_Local";
    public static final String EG_NATIONAL = "EG_National";
    public static final String EG_OPINION = "EG_Opinion";
    public static final String EG_PEOPLE = "EG_People";
    public static final String EG_SCHOOL = "EG_School";
    public static final String EG_SPORTS = "EG_Sports";
    public static final String EG_STUDENT = "EG_Student";
    public static final String EG_WEB = "EG_Web";
    public static final String EG_WORLD = "EG_World";

    // Returns the link associated with key.
    public static String getLink(String key, Context context) {
        String file = FileUtil.readFromFile(FileUtil.FILE_LINKS, context);

        HashMap<String, String> links = new HashMap<>();

        String[] list = file.split("\r");
        for (String s : list) {
            String[] cols = s.split("\t");
            if (cols.length > 2) {
                links.put(cols[1], cols[2]);
            }
        }

        return links.get(key);
    }

}
