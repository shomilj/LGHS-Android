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
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class MainFragment extends Fragment {

    private Button mainAnnouncementButton;
    private Button mainCountdownButton;
    private Button mainCanvasButton;
    private Button mainCalendarButton;
    private Button mainStudentIDButton;
    private Button mainClubsListButton;
    private Button mainCollegesListButton;
    private Button mainHotlinesButton;
    private Button mainLGHSTwitterFeedButton;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainFragment.
     */
    public static MainFragment newInstance() {

        return new MainFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        View view2 = getView();

        // Inflate the layout for this fragment
        mainAnnouncementButton = (Button) view.findViewById(R.id.main_announcement_button);
        mainCountdownButton = (Button) view.findViewById(R.id.main_countdown_button);
        mainCanvasButton = (Button) view.findViewById(R.id.main_canvas_button);
        mainCalendarButton = (Button) view.findViewById(R.id.main_calendar_button);
        mainStudentIDButton = (Button) view.findViewById(R.id.main_studentID_button);
        mainClubsListButton = (Button) view.findViewById(R.id.main_clubsList_button);
        mainCollegesListButton = (Button) view.findViewById(R.id.main_collegesList_button);
        mainHotlinesButton = (Button) view.findViewById(R.id.main_hotlines_button);
        mainLGHSTwitterFeedButton = (Button) view.findViewById(R.id.main_LGHSTwitterFeed_button);

        initiateButtons();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void initiateButtons () {

        mainAnnouncementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("i'll try spinning, that's a good trick");
                Intent intent = new Intent(getActivity(), AnnouncementsActivity.class);
                startActivity(intent);
            }
        });

        mainCountdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(MainActivity.this, AnnouncementsActivity.class);
                // startActivity(intent);
            }
        });

        mainCanvasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("app", 0);
                intent.putExtra("url", getString(R.string.canvas_grades_link));
                startActivity(intent);
            }
        });

        mainCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("hello there general kenobi");
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivity(intent);
            }
        });

        mainStudentIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudentIDActivity.class);
                startActivity(intent);
            }
        });

        mainClubsListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClubsListActivity.class);
                startActivity(intent);
            }
        });

        mainCollegesListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CollegesListActivity.class);
                startActivity(intent);
            }
        });

        mainHotlinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HotlinesActivity.class);
                startActivity(intent);
            }
        });

        mainLGHSTwitterFeedButton.setOnClickListener(new View.OnClickListener() {
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
