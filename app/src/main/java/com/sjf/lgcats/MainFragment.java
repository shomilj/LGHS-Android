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

    private Button mainFragmentAnnouncementButton;
    private Button mainFragmentCountdownButton;
    private Button mainFragmentCanvasButton;
    private Button mainFragmentCalendarButton;
    private Button mainFragmentStudentIDButton;
    private Button mainFragmentClubsListButton;
    private Button mainFragmentCollegesListButton;
    private Button mainFragmentHotlinesButton;
    private Button mainFragmentLGHSTwitterFeedButton;

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

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mainFragmentAnnouncementButton = (Button) view.findViewById(R.id.main_fragment_announcement_button);
        mainFragmentCountdownButton = (Button) view.findViewById(R.id.main_fragment_countdown_button);
        mainFragmentCanvasButton = (Button) view.findViewById(R.id.main_fragment_canvas_button);
        mainFragmentCalendarButton = (Button) view.findViewById(R.id.main_fragment_calendar_button);
        mainFragmentStudentIDButton = (Button) view.findViewById(R.id.main_fragment_studentID_button);
        mainFragmentClubsListButton = (Button) view.findViewById(R.id.main_fragment_clubsList_button);
        mainFragmentCollegesListButton = (Button) view.findViewById(R.id.main_fragment_collegesList_button);
        mainFragmentHotlinesButton = (Button) view.findViewById(R.id.main_fragment_hotlines_button);
        mainFragmentLGHSTwitterFeedButton = (Button) view.findViewById(R.id.main_fragment_LGHSTwitterFeed_button);

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
