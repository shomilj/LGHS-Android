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

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // read/parse college list
                parseHotline();
            }
        }).start();

        setContentView(R.layout.activity_hotlines);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * parses the hotlines file parameter by making hotline element
     */

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
