package com.sjf.lgcats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResourcesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResourcesFragment extends Fragment {

    private Button resourcesFragmentPortals;
    private Button resourcesFragmentAthleticsAndActivities;
    private Button resourcesFragmentCoursesAndCurricula;
    private Button resourcesFragmentSchoolPolicies;
    private Button resourcesFragmentStaff;
    private Button resourcesFragmentGuidanceInformation;
    private Button resourcesFragmentLibrary;
    private Button resourcesFragmentCommunityLinks;

    public ResourcesFragment () {
        // Required empty public constructor
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

        resourcesFragmentPortals = (Button) view.findViewById(R.id.resources_fragment_portals);
        resourcesFragmentAthleticsAndActivities = (Button) view.findViewById(R.id.resources_fragment_athletics_and_activities);
        resourcesFragmentCoursesAndCurricula = (Button) view.findViewById(R.id.resources_fragment_courses_and_curricula);
        resourcesFragmentSchoolPolicies = (Button) view.findViewById(R.id.resources_fragment_school_policies);
        resourcesFragmentStaff = (Button) view.findViewById(R.id.resources_fragment_staff);
        resourcesFragmentGuidanceInformation = (Button) view.findViewById(R.id.resources_fragment_guidance_information);
        resourcesFragmentLibrary = (Button) view.findViewById(R.id.resources_fragment_library);
        resourcesFragmentCommunityLinks = (Button) view.findViewById(R.id.resources_fragment_community_links);

        initiateButtons();

        return view;

    }

    @Override
    public void onAttach (Context context) {

        super.onAttach(context);

    }

    @Override
    public void onDetach () {

        super.onDetach();

    }

    public void initiateButtons () {

        resourcesFragmentPortals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AnnouncementsActivity.class);
                startActivity(intent);
            }
        });

        resourcesFragmentAthleticsAndActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AnnouncementsActivity.class);
                startActivity(intent);
            }
        });

        resourcesFragmentCoursesAndCurricula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        resourcesFragmentSchoolPolicies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivity(intent);
            }
        });

        resourcesFragmentStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudentIDActivity.class);
                startActivity(intent);
            }
        });

        resourcesFragmentGuidanceInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClubsListActivity.class);
                startActivity(intent);
            }
        });

        resourcesFragmentLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CollegesListActivity.class);
                startActivity(intent);
            }
        });

        resourcesFragmentCommunityLinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HotlinesActivity.class);
                startActivity(intent);
            }
        });

    }

}
