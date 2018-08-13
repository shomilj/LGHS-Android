//
// CollegesListActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// CollegesListActivity - displays a list of colleges visiting lghs
//

package com.avinalabs.losgatoshighschool;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

public class CollegesListActivity extends AppCompatActivity {

    private ArrayList<College> colleges;
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
    // post: starts download in background
    private void startDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                fetchColleges();
            }
        }).start();
    }

    // pre: none
    // post: initializes the private variables
    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.college_list_view);
        colleges = new ArrayList<>();
    }

    // pre: none
    // post: sets up the view
    private void setupView() {
        setContentView(R.layout.activity_colleges_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Colleges @ LGHS");
    }

    // pre: none
    // post: fetches colleges from server
    public void fetchColleges() {
        // get the link associated with the college spreadsheet
        String link = null;//LinkUtils.getLink(LinkUtils.HOST_COLLEGES, getApplicationContext());

        // download the contents of the text file at the link
        String file = StringUtil.getUrlContents(link);

        parseColleges(file);
        sortCollegeList();
        updateUI();
    }

    // pre: none
    // post: parses college from text file
    private void parseColleges(String file) {
        // split the spreadsheet into rows
        String[] rows = file.split("\r");

        // ignore first row bc of header
        // start at index i = 1 and go to number of rows
        for (int i = 1; i < rows.length; i++) {

            // split the row into cells
            String[] cells = rows[i].split("\t");

            // if the row has 4 cells, then it's valid
            if (cells.length > 3) {

                // assign variables based on indices
                String name = cells[0];
                String date = cells[1];
                String time = cells[2];
                String location = cells[3];

                // create a new college & add it if it's upcoming
                College col = new College(name, time, location, date);
                if (col.isUpcoming())
                    colleges.add(col);
            }
        }
    }

    // pre: none
    // post: updates UI
    private void updateUI() {
        runOnUiThread(new Runnable() {
            public void run() {
                fillListView();
                setupListListener();
                showEmptyCollegeAlertIfNecessary();
            }
        });
    }

    // pre: none
    // post: if college list is empty then shows alert and goes back to previous screen
    private void showEmptyCollegeAlertIfNecessary() {
        if (colleges.size() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CollegesListActivity.this);
            builder.setMessage("At this time, no colleges have scheduled visits to LGHS.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    // pre: none
    // post: sorts the college list by date
    private void sortCollegeList() {
        Collections.sort(colleges, new Comparator<College>() {
            @Override
            public int compare(College c2, College c1) {
                return c2.getDateString().compareTo(c1.getDateString());
            }
        });
    }

    // pre: none
    // post: assigns listener to listview
    private void setupListListener() {
        final Context context = this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tappedCollege(position, context);
            }
        });
    }

    // pre: none
    // post: passes data and transitions to college detail screen
    private void tappedCollege(int position, Context context) {
        Intent detailIntent = new Intent(context, CollegesDetailActivity.class);
        College college = colleges.get(position);
        detailIntent.putExtra("name", college.getName());
        detailIntent.putExtra("dateString", college.getDateString());
        detailIntent.putExtra("location", college.getLocation());
        startActivity(detailIntent);
    }

    // pre: none
    // post: fills the list view w college data
    private void fillListView() {
        // arraylist of hashmaps holds the college data to be displayed
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        // add an item for each college
        for (College college : colleges) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("First Line", college.getName());
            datum.put("Second Line", college.getDateString());
            data.add(datum);
        }

        // link hashmap to listview
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[]{"First Line", "Second Line"},
                new int[]{android.R.id.text1, android.R.id.text2});

        if (mListView != null) {
            mListView.setAdapter(adapter);
        }
    }

}
