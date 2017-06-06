//
// ResourcesFragment.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// ResourcesFragment - fragment for the resources home screen
//

package com.sjf.lgcats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;

public class ResourcesFragment extends Fragment {

    private ArrayList<Object> items;

    private HashMap<String, String> teachersData;
    private HashMap<String, String> libraryData;
    private HashMap<String, String> portalsData;
    private HashMap<String, String> athleticsData;
    private HashMap<String, String> courseData;
    private HashMap<String, String> policyData;
    private HashMap<String, String> communityData;
    private HashMap<String, String> gradNightData;
    private HashMap<String, String> enrollmentData;
    private HashMap<String, String> specialEdData;
    private HashMap<String, String> altEducationData;
    private HashMap<String, String> postHSData;
    private HashMap<String, String> gradeLevelData;

    private ListView listView;

    public ResourcesFragment () {
        // Required empty public constructor

    }

    private void fillGradeLevel() {
        gradeLevelData = new HashMap<>();
        gradeLevelData.put("Freshmen", LinkUtils.GL_FRESHMEN);
        gradeLevelData.put("Sophomores", LinkUtils.GL_SOPHOMORE);
        gradeLevelData.put("Juniors", LinkUtils.GL_JUNIOR);
        gradeLevelData.put("Seniors", LinkUtils.GL_SENIOR);
    }

    private void fillPostHS() {
        postHSData = new HashMap<>();
        postHSData.put("College & Career Center", LinkUtils.PHS_COLLEGECAREER);
        postHSData.put("Naviance", LinkUtils.PHS_NAVIANCE);
    }

    private void fillAltEducation() {
        altEducationData = new HashMap<>();
        altEducationData.put("Middle College", LinkUtils.AE_MIDDLE);
        altEducationData.put("College Advantage", LinkUtils.AE_MIDDLE);
        altEducationData.put("Alternative Education", LinkUtils.AE_MIDDLE);
        altEducationData.put("SVCTE", LinkUtils.AE_MIDDLE);
        altEducationData.put("English Language Learners", LinkUtils.AE_MIDDLE);

    }

    private void fillSpecialEd() {
        specialEdData = new HashMap<>();
        specialEdData.put("Staff/Resources", LinkUtils.SE_STAFF);
        specialEdData.put("Services & Programs", LinkUtils.SE_SERVICES);
        specialEdData.put("IEP", LinkUtils.SE_IEP);
        specialEdData.put("Post-Secondary", LinkUtils.SE_POSTSECONDARY);
        specialEdData.put("Work Ability", LinkUtils.SE_WORKABILITY);
    }

    private void fillEnrollment() {
        enrollmentData = new HashMap<>();
        enrollmentData.put("Transcript Request", LinkUtils.ENROLLMENT_TRANSCRIPT);
        enrollmentData.put("Enrollment/Registration", LinkUtils.ENROLLMENT_REGISTRATION);
        enrollmentData.put("New Student Registration", LinkUtils.ENROLLMENT_NEW);
        enrollmentData.put("Incoming Freshmen Course Registration", LinkUtils.ENROLLMENT_FRESHMENREG);
        enrollmentData.put("Current Student Course Registration", LinkUtils.ENROLLMENT_CURRENTREG);
    }

    private void fillGrad() {
        gradNightData = new HashMap<>();
        gradNightData.put("Newsletters", LinkUtils.GN_NEWS);
        gradNightData.put("Calendar", LinkUtils.GN_CALENDAR);
        gradNightData.put("Coordinators", LinkUtils.GN_COORDINATORS);
        gradNightData.put("Admissions", LinkUtils.GN_ADMISSIONS);
        gradNightData.put("Decorations", LinkUtils.GN_DECORATIONS);
        gradNightData.put("Facilities", LinkUtils.GN_FACILITIES);
        gradNightData.put("Food & Hospitality", LinkUtils.GN_FOOD);
        gradNightData.put("Indoor Activities", LinkUtils.GN_INDOOR);
        gradNightData.put("Outdoor Activities", LinkUtils.GN_OUTDOOR);
        gradNightData.put("Senior Museum", LinkUtils.GN_SENIOR);
        gradNightData.put("Kiss a Senior Goodbye", LinkUtils.GN_KISS);
        gradNightData.put("Steering Committee", LinkUtils.GN_STEERING);
        gradNightData.put("Treasurer", LinkUtils.GN_TREASURER);
        gradNightData.put("Volunteers", LinkUtils.GN_VOLUNTEERS);
        gradNightData.put("Junior Parents", LinkUtils.GN_JUNIOR);
        gradNightData.put("Sophomore Parents", LinkUtils.GN_SOPHOMORE);
    }

    private void fillCommunity() {
        communityData = new HashMap<>();
        communityData.put("Community Organization", LinkUtils.PARENTS_HOME);
        communityData.put("CASSY", LinkUtils.PARENTS_CASSY);
        communityData.put("NMF", LinkUtils.PARENTS_NMF);
        communityData.put("Home and School Club", LinkUtils.PARENTS_HSC);
        communityData.put("ACTT", LinkUtils.PARENTS_ACTT);
        communityData.put("Project Cornerstone", LinkUtils.PARENTS_CORNERSTONE);
        communityData.put("Parenting Continuum", LinkUtils.PARENTS_CONTINUUM);
        communityData.put("CASA", LinkUtils.PARENTS_CASA);
    }

    private void fillPolicy() {
        policyData = new HashMap<>();
        policyData.put("Grading Policy", LinkUtils.POLICY_GRADING);
        policyData.put("Guidance Policy", LinkUtils.POLICY_ATTENDANCE);
        policyData.put("Student Course Change Policy", LinkUtils.POLICY_STUDENTCOURSECHANGE);
        policyData.put("Academic Integrity Policy", LinkUtils.POLICY_INTEGRITY);
        policyData.put("Attendance", LinkUtils.POLICY_ATTENDANCE);
        policyData.put("Code of Conduct", LinkUtils.POLICY_CODE);
        policyData.put("Dress Code", LinkUtils.POLICY_DRESS);
        policyData.put("Tech Agreement", LinkUtils.POLICY_TECH);
        policyData.put("Notification to Parents", LinkUtils.POLICY_PARENT);
    }

    private void fillCourse() {
        courseData = new HashMap<>();
        courseData.put("Sequence Charts", LinkUtils.COURSE_SEQUENCE);
        courseData.put("Course Descriptions", LinkUtils.COURSE_DESCRIPTIONS);
        courseData.put("AP/Honors Courses", LinkUtils.COURSE_AP);
        courseData.put("Graduation Requirements", LinkUtils.COURSE_GRADREQS);
        courseData.put("Electives", LinkUtils.COURSE_ELECTIVES);
        courseData.put("UC/CSU Entrance Requirements", LinkUtils.COURSE_UCCSU);
    }

    private void fillAthletics() {
        athleticsData = new HashMap<>();
        athleticsData.put("Athletics", LinkUtils.ACTIVITIES_ATHLETICS);
        athleticsData.put("Music", LinkUtils.ACTIVITIES_MUSIC);
        athleticsData.put("Theater", LinkUtils.ACTIVITIES_THEATER);
        athleticsData.put("Leadership", LinkUtils.ACTIVITIES_LEADERSHIP);
    }

    private void fillPortals() {
        portalsData = new HashMap<>();
        portalsData.put("Canvas", LinkUtils.PORTAL_CANVAS);
        portalsData.put("Aeries", LinkUtils.PORTAL_AERIES);
        portalsData.put("Naviance", LinkUtils.PORTAL_NAVIANCE);
    }

    private void fillLibary() {
        libraryData = new HashMap<>();
        libraryData.put("Library Home", LinkUtils.LIBRARY_HOME);
        libraryData.put("Library Info", LinkUtils.LIBRARY_INFO);
        libraryData.put("Textbook Office", LinkUtils.LIBRARY_TEXTBOOK);
        libraryData.put("Testing Center", LinkUtils.LIBRARY_TESTING);
        libraryData.put("Student Jobs", LinkUtils.LIBRARY_JOBS);
    }

    private void fillTeacher() {
        teachersData = new HashMap<>();
        teachersData.put("Principal's Page", LinkUtils.STAFF_PRINCIPAL);
        teachersData.put("Administration", LinkUtils.STAFF_ADMINISTRATION);
        teachersData.put("Staff Directory", LinkUtils.STAFF_DIRECTORY);
        teachersData.put("Teacher Pages", LinkUtils.STAFF_TEACHERPAGES);
        teachersData.put("Technology", LinkUtils.STAFF_TECHNOLOGY);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ResourcesFragment.
     */
    public static ResourcesFragment newInstance() {
        return new ResourcesFragment();
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resources, container, false);

        listView = (ListView) view.findViewById(R.id.resources_list_view);
        fillLinkMaps();

        String s1 = "QUICK LINKS";
        ResourceCellObject portals = new ResourceCellObject("Portals (Canvas, Aeries, etc.)", portalsData);
        ResourceCellObject activities = new ResourceCellObject("Athletics & Activities", athleticsData);

        String s2 = "STUDENTS";
        ResourceCellObject curriculum = new ResourceCellObject("Curriculum & Courses", courseData);
        ResourceCellObject policies = new ResourceCellObject("School Policies", policyData);

        String s3 = "DEPARTMENTS";
        ResourceCellObject staff = new ResourceCellObject("Staff", teachersData);

        ArrayList<ResourceCellObject> guidanceList = new ArrayList<>();
        guidanceList.add(new ResourceCellObject("Grade-Level Info", gradeLevelData));
        guidanceList.add(new ResourceCellObject("Post-High School Info", postHSData));
        guidanceList.add(new ResourceCellObject("Alternative Education", altEducationData));
        guidanceList.add(new ResourceCellObject("Special Education", specialEdData));
        ResourceStackCellObject guidance = new ResourceStackCellObject("Guidance Information", guidanceList);

        ResourceCellObject library = new ResourceCellObject("Library", libraryData);

        String s4 = "PARENTS";

        ResourceCellObject commLinks = new ResourceCellObject("Community Links", communityData);

        ArrayList<ResourceCellObject> parentList = new ArrayList<>();
        parentList.add(new ResourceCellObject("Enrollment", enrollmentData));
        parentList.add(new ResourceCellObject("Grad Night", gradNightData));

        ResourceStackCellObject parents = new ResourceStackCellObject("Parent Resources", parentList);

        items = new ArrayList<>();
        items.add(s1);
        items.add(portals);
        items.add(activities);
        items.add(s2);
        items.add(curriculum);
        items.add(policies);
        items.add(s3);
        items.add(staff);
        items.add(guidance);
        items.add(library);
        items.add(s4);
        items.add(commLinks);
        items.add(parents);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object object = items.get(position);
                if (object instanceof String) {
                    System.out.println("Header pressed!");
                } else if (object instanceof ResourceCellObject) {
                    Intent detailIntent = new Intent(getContext(), ResourcesListActivity.class);
                    detailIntent.putExtra("Map", ((ResourceCellObject) (object)).getLinkContent());
                    startActivity(detailIntent);
                } else if (object instanceof ResourceStackCellObject) {
                    Intent detailIntent = new Intent(getContext(), ResourcesStackActivity.class);
                    detailIntent.putExtra("Stack", ((ResourceStackCellObject) (object)).getList());
                    startActivity(detailIntent);
                }
            }
        });

        listView.setAdapter(new ResourcesAdapter(this.getContext(), items));

        return view;
    }

    private void fillLinkMaps() {
        fillTeacher();
        fillLibary();
        fillPortals();
        fillAthletics();
        fillCourse();
        fillPolicy();
        fillCommunity();
        fillGrad();
        fillEnrollment();
        fillSpecialEd();
        fillAltEducation();
        fillPostHS();
        fillGradeLevel();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
