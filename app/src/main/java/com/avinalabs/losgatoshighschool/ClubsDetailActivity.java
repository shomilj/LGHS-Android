//
// ClubsDetailActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// ClubsDetailActivity - displays list of club information
//

package com.avinalabs.losgatoshighschool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClubsDetailActivity extends AppCompatActivity {

    private Club club;
    private ListView mListView;

    // called when the view is created
    // pre: none
    // post: configures the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        fetchExtras();
        fillListView();
    }

    // pre: none
    // post: fetches the content passed from the previous screen
    private void fetchExtras() {
        club = (Club) (getIntent().getSerializableExtra("Club"));
    }

    // pre: none
    // post: initializes variables
    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.club_detail_list_view);
    }

    // pre: none
    // post: sets up the view
    private void setupView() {
        setContentView(R.layout.activity_clubs_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Clubs @ LGHS");
    }

    // pre: private variables have been initialized
    // post: fills the list view
    private void fillListView() {
        // this hash map holds the club data in a format parsable by the listview
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        Map<String, String> m1 = new HashMap<>();
        m1.put(Club.lv_item_1_key, "Club Name");
        m1.put(Club.lv_item_2_key, club.getName());
        data.add(m1);

        Map<String, String> m2 = new HashMap<>();
        m2.put(Club.lv_item_1_key, "Typical Meeting Day(s)");
        m2.put(Club.lv_item_2_key, club.getDay());
        data.add(m2);

        Map<String, String> m3 = new HashMap<>();
        m3.put(Club.lv_item_1_key, "Meeting Time(s)");
        m3.put(Club.lv_item_2_key, club.getTime());
        data.add(m3);

        Map<String, String> m4 = new HashMap<>();
        m4.put(Club.lv_item_1_key, "Location");
        m4.put(Club.lv_item_2_key, club.getLocation());
        data.add(m4);

        Map<String, String> m5 = new HashMap<>();
        m5.put(Club.lv_item_1_key, "President");
        m5.put(Club.lv_item_2_key, club.getPresident());
        data.add(m5);

        Map<String, String> m6 = new HashMap<>();
        m6.put(Club.lv_item_1_key, "Vice President");
        m6.put(Club.lv_item_2_key, club.getVicePresident());
        data.add(m6);

        Map<String, String> m7 = new HashMap<>();
        m7.put(Club.lv_item_1_key, "Staff Advisor");
        m7.put(Club.lv_item_2_key, club.getAdvisor());
        data.add(m7);

        Map<String, String> m8 = new HashMap<>();
        m8.put(Club.lv_item_1_key, "Email to Contact Club");
        m8.put(Club.lv_item_2_key, club.getEmail());
        data.add(m8);


        // assign the hashmap to the list view
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[]{Club.lv_item_1_key, Club.lv_item_2_key},
                new int[]{android.R.id.text1, android.R.id.text2});

        // if the listview has been initialized, then display that info
        if (mListView != null) {
            mListView.setAdapter(adapter);
        }
    }
}