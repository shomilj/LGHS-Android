//
// ClubsListActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// ClubsListActivity - holds a list of clubs
//

package com.sjf.lgcats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClubsListActivity extends AppCompatActivity {

    private ArrayList<Club> mClubs;
    private ListView mListView;

    // called when the view is created
    // pre: none
    // post: configures the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        startDownload();
    }

    // pre: none
    // post: sets up the view
    private void setupView() {
        setContentView(R.layout.activity_clubs_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Clubs");
    }

    // pre: none
    // post: initializes the private variables
    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.club_list_view);
        mClubs = new ArrayList<>();
    }

    // pre: none
    // post: starts downloading the clubs in the background
    private void startDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // read/parse club list
                fetchClubs();
            }
        }).start();
    }

    // pre: none
    // post: fetches the club data from the server
    public void fetchClubs() {
        // get the link associated with the club spreadsheet
        String link = LinkUtils.getLink(LinkUtils.HOST_CLUBS, getApplicationContext());

        // download the contents of the text file at the link
        String file = StringUtil.getUrlContents(link);

        parseClubs(file);
        sortClubList();
        updateUI();
    }

    // pre: none
    // post: parses club information from the tsv file
    public void parseClubs(String file) {
        // split the spreadsheet into rows
        String[] rows = file.split("\r");

        // ignore first row bc of header
        // start at index i = 1 and go to number of rows
        for (int i = 1; i < rows.length; i++) {

            // split the row into cells
            String[] cells = rows[i].split("\t");

            // if the row has 4 cells, then it's valid
            if (cells.length > 7) {

                // assign variables based on indices
                String name = cells[0];
                String day = cells[1];
                String time = cells[2];
                String location = cells[3];
                String president = cells[4];
                String vicePresident = cells[5];
                String advisor = cells[6];
                String email = cells[7];

                // create a new club & add it
                Club c = new Club(name, day, time, location, president, vicePresident, advisor, email);
                mClubs.add(c);
            }
        }
    }

    // pre: none
    // post: sorts the club list
    private void sortClubList() {
        Collections.sort(mClubs, new Comparator<Club>() {
            @Override
            public int compare(Club c2, Club c1) {
                return (c2.getName().compareTo(c1.getName()));
            }
        });
    }

    // pre: none
    // post: updates the UI in the main thread
    private void updateUI() {
        runOnUiThread(new Runnable() {
            public void run() {
                fillListView();
                setupListListener();
            }
        });
    }

    // pre: none
    // post: sets up the listener for listview taps
    private void setupListListener() {
        final Context context = this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tappedClub(position, context);
            }
        });
    }

    // pre: none
    // post: passes club data to next screen & transitions to club detail screen
    private void tappedClub(int position, Context context) {
        Intent detailIntent = new Intent(context, ClubsDetailActivity.class);
        Club club = mClubs.get(position);
        detailIntent.putExtra(Club.intent_key, club);
        startActivity(detailIntent);
    }

    // pre: none
    // post: fills the list view with clubs
    private void fillListView() {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (Club club : mClubs) {
            // hashmap holds club name & day/time string
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put(Club.lv_item_1_key, club.getName());
            String second = "" + club.getDay() + " - " + club.getTime();
            datum.put(Club.lv_item_2_key, second);
            data.add(datum);
        }

        // connects hashmap to list view
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[]{Club.lv_item_1_key, Club.lv_item_2_key},
                new int[]{android.R.id.text1, android.R.id.text2});

        if (mListView != null) {
            mListView.setAdapter(adapter);
        }
    }

}
