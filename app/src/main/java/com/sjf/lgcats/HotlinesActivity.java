//
// HotlinesActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// HotlinesActivity - holds a ListView of hotlines
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

public class HotlinesActivity extends AppCompatActivity {

    private ArrayList<Hotline> hotlines;
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
                // read/parse college list
                parseHotlines();
            }
        }).start();
    }

    // pre: none
    // post: initializes the private variables
    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.hotlines_list_view);
    }

    // pre: none
    // post: sets up the view
    private void setupView() {
        setContentView(R.layout.activity_hotlines);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Hotlines");
    }

    // pre: none
    // post: fetches the hotlines from the file and parses them
    public void parseHotlines() {
        hotlines = new ArrayList<>();
        String file = FileUtil.readFromFile(FileUtil.FILE_HOTLINES, getApplicationContext());

        if (file == null) {
            System.out.println("Empty file");
            return;
        }

        try {
            String[] rows = file.split("\r");
            for (String row : rows) {
                String[] cells = row.split("\t");
                if (cells.length > 1) {
                    hotlines.add(new Hotline(cells[0], cells[1]));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR HOTLINES");
            System.out.println(e);
        }
        updateUI();
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
    // post: assigns a listener to the ListView
    private void setupListListener() {
        final Context context = this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // passes data to detail class
                Hotline h = hotlines.get(position);
                Intent detailIntent = new Intent(context, HotlinesDetailActivity.class);
                detailIntent.putExtra("title", h.getTitle());
                detailIntent.putExtra("content", h.getDescription());
                startActivity(detailIntent);
            }
        });
    }

    // pre: none
    // post: fills the hotline list view
    private void fillListView() {
        String[] listItems = new String[hotlines.size()];
        for (int i = 0; i < hotlines.size(); i++) {
            Hotline h = hotlines.get(i);
            listItems[i] = h.getTitle();
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        if (mListView == null) {
            System.out.println("null");
        } else {
            mListView.setAdapter(adapter);
        }
    }
}