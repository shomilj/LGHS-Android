//
// MainFragment.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// MainFragment - holds the content (XML button links) of the MainActivity class
//

package com.sjf.lgcats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    // buttons for each link on the homepage
    private Button mainFragmentAnnouncementButton;
    private Button mainFragmentCountdownButton;
    private Button mainFragmentCanvasButton;
    private Button mainFragmentCalendarButton;
    private Button mainFragmentStudentIDButton;
    private Button mainFragmentClubsListButton;
    private Button mainFragmentCollegesListButton;
    private Button mainFragmentHotlinesButton;
    private Button mainFragmentLGHSTwitterFeedButton;

    // Required empty public constructor
    // pre: none
    // post: none
    public MainFragment() {

    }

    // pre: none
    // post: returns a new instance of MainFragment
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    // pre: none
    // post: empty onCreate method, calls super method
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // pre: none
    // post: called when view is created, initializes buttons
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // link buttons
        mainFragmentAnnouncementButton = (Button) view.findViewById(R.id.main_fragment_announcement_button);
        mainFragmentCountdownButton = (Button) view.findViewById(R.id.main_fragment_countdown_button);
        mainFragmentCanvasButton = (Button) view.findViewById(R.id.main_fragment_canvas_button);
        mainFragmentCalendarButton = (Button) view.findViewById(R.id.main_fragment_calendar_button);
        mainFragmentStudentIDButton = (Button) view.findViewById(R.id.main_fragment_studentID_button);
        mainFragmentClubsListButton = (Button) view.findViewById(R.id.main_fragment_clubsList_button);
        mainFragmentCollegesListButton = (Button) view.findViewById(R.id.main_fragment_collegesList_button);
        mainFragmentHotlinesButton = (Button) view.findViewById(R.id.main_fragment_hotlines_button);
        mainFragmentLGHSTwitterFeedButton = (Button) view.findViewById(R.id.main_fragment_LGHSTwitterFeed_button);

        // setup button listeners
        initiateButtons();

        return view;
    }

    // pre: none
    // post: default fragment method
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    // pre: none
    // post: default fragment method
    @Override
    public void onDetach() {
        super.onDetach();
    }

    // pre: buttons are not null
    // post: sets a listener to each button, button presses lead to new screens
    public void initiateButtons () {

        mainFragmentAnnouncementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AnnouncementsActivity.class);
                startActivity(intent);
            }
        });

        mainFragmentCountdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CountdownActivity.class);
                startActivity(intent);
            }
        });

        mainFragmentCanvasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("app", 0);
                intent.putExtra("url", LinkUtils.getLink(LinkUtils.PORTAL_CANVAS, getContext()));
                startActivity(intent);
            }
        });

        mainFragmentCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivity(intent);
            }
        });

        mainFragmentStudentIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudentIDActivity.class);
                startActivity(intent);
            }
        });

        mainFragmentClubsListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClubsListActivity.class);
                startActivity(intent);
            }
        });

        mainFragmentCollegesListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CollegesListActivity.class);
                startActivity(intent);
            }
        });

        mainFragmentHotlinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HotlinesActivity.class);
                startActivity(intent);
            }
        });

        mainFragmentLGHSTwitterFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("app", R.string.twitter);
                intent.putExtra("url", getString(R.string.twitter_web_link));
                startActivity(intent);
            }
        });
    }
}
