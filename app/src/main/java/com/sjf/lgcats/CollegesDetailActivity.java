//
// CollegesDetailActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// CollegesDetailActivity - displays detailed info about each college
//

package com.sjf.lgcats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollegesDetailActivity extends AppCompatActivity {

    private String name;
    private String dateString;
    private String location;
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
    // post: fetches information from previous screen
    private void fetchExtras() {
        name = this.getIntent().getExtras().getString("name");
        dateString = this.getIntent().getExtras().getString("dateString");
        location = this.getIntent().getExtras().getString("location");
    }

    // pre: none
    // post: initializes the private variables
    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.college_detail_list_view);
    }

    // pre: none
    // post: sets up the view
    private void setupView() {
        setContentView(R.layout.activity_colleges_detail);
        setTitle("Colleges @ LGHS");
    }

    // pre: none
    // post: fills the listview with the info
    private void fillListView() {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        Map<String, String> m1 = new HashMap<>();
        m1.put("First Item", "College Name");
        m1.put("Second Item", name);
        data.add(m1);

        Map<String, String> m2 = new HashMap<>();
        m2.put("First Item", "Date/Time");
        m2.put("Second Item", dateString);
        data.add(m2);

        Map<String, String> m3 = new HashMap<>();
        m3.put("First Item", "Location");
        m3.put("Second Item", location);
        data.add(m3);

        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[]{"First Item", "Second Item"},
                new int[]{android.R.id.text1, android.R.id.text2});

        if (mListView != null) {
            mListView.setAdapter(adapter);
        }
    }
}
