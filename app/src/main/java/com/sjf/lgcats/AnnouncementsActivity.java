//
// AnnouncementsActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// AnnouncementsActivity - holds a list of daily announcements
//

package com.sjf.lgcats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class AnnouncementsActivity extends AppCompatActivity {

    // Holds a mList of Announcement objects
    private ArrayList<Announcement> mList;

    // The announcements ListView
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
    // post: starts a download process in a background thread to download the announcements
    private void startDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                fetchAnnouncements();
            }
        }).start();
    }

    // pre: none
    // post: initializes private variables
    private void assignVariables() {
        mList = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.announcement_list_view);
    }

    // pre: none
    // post: sets up the view
    private void setupView() {
        // links this class to the layout XML file
        setContentView(R.layout.activity_announcements);

        // configures toolbar back button & title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Daily Announcements");
    }

    // pre: mListView is initialized
    // post: assigns a listener to the ListView
    private void setupListListener() {
        final Context context = this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tappedAnnouncement(position, context);
            }
        });
    }

    // called when the user taps on a item in the list
    // pre: position is a valid index of mList
    // post: passes the announcement information to the next screen and moves there
    private void tappedAnnouncement(int position, Context context) {
        Announcement a = mList.get(position);
        Intent detailIntent = new Intent(context, AnnouncementDetailActivity.class);
        detailIntent.putExtra("date", a.getDate());
        detailIntent.putExtra("content", a.getContent());
        startActivity(detailIntent);
    }

    // pre: none
    // post: fetches the announcements from the URL & parses them into list
    private void fetchAnnouncements() {

        // get the link associated with the announcement spreadsheet
        String link = LinkUtils.getLink(LinkUtils.HOST_ANNOUNCEMENTS, getApplicationContext());

        // download the contents of the text file at the link
        String file = StringUtil.getUrlContents(link);

        // split the spreadsheet into rows
        String[] rows = file.split("\r");
        for (String row : rows) {
            System.out.println(row);
        }

        // ignore first row bc of header
        // start at index i = 1 and go to number of rows
        for (int i = 1; i < rows.length; i++) {

            // split the row into cells
            String[] cells = rows[i].split("\t");

            // if the row has 4 cells, then it's valid
            if (cells.length > 1) {

                // assign variables based on indices
                String date = cells[0];
                String content = cells[1];

                // create a new announcement & add it if it's upcoming
                Announcement a = new Announcement(date, content);
                mList.add(a);
            }
        }

        // check that colleges have parsed correctly
        for (Announcement a : mList) {
            System.out.println(a);
        }

        // update the list view in the main thread
        runOnUiThread(new Runnable() {
            public void run() {
                fillListView();
                setupListListener();
            }
        });
    }

    // pre: none
    // post: fills the list view with list items
    private void fillListView() {

        // create an array of list items (just the date)
        String[] listItems = new String[mList.size()];
        for (int i = 0; i < mList.size(); i++) {
            Announcement a = mList.get(i);
            listItems[i] = a.getDate();
        }

        // make the list view show that array
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
    }


}
