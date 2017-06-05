//
//  HotlineActivity.java
//  LG CATS
//
//  Created by <name of developer> on <date>
//  Copyright © SJF Technologies, Inc. All rights reserved.
//
/*
A HotlineActivity contains a screen that displays a list of hotlines. These are fetched/stored in an arraylist of Hotline objects.

Attributes:
private ArrayList<Hotline> hotlines; // holds a list of hotline objects

Methods:
onCreate(Bundle savedInstanceState) // runs when the view is created
parse(String tsv) // parses the tsv file (downloaded as a String) into the array of hotlines.
*/

package com.sjf.lgcats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author  Shomil Jain
 * @author  Quintin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
 */

public class HotlinesActivity extends AppCompatActivity {

    private ArrayList<Hotline> hotlines;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        startDownload();
    }

    private void startDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // read/parse college list
                parseHotlines();
            }
        }).start();
    }

    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.hotlines_list_view);
    }

    private void setupView() {
        setContentView(R.layout.activity_hotlines);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Hotlines");
    }

    public void parseHotlines()
    {
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
        for (Hotline a : hotlines) {
            System.out.println(a);
        }
        updateUI();
    }

    private void updateUI() {
        runOnUiThread(new Runnable() {
            public void run() {
                fillListView();
                setupListListener();
            }
        });
    }


    private void setupListListener() {
        final Context context = this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Hotline h = hotlines.get(position);
                Intent detailIntent = new Intent(context, HotlinesDetailActivity.class);
                detailIntent.putExtra("title", h.getTitle());
                detailIntent.putExtra("content", h.getDescription());
                startActivity(detailIntent);
            }
        });
    }

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

    public void parseHotline()
    {
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
        for (Hotline a : hotlines) {
            System.out.println(a);
        }
    }
}