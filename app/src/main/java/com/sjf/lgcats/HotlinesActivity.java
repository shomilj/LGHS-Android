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
        setContentView(R.layout.activity_hotlines);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * parses the string parameter by making hotline elements
     * @param tsv parsed from google docs tab seperated file
     */
    public void parseTSV (String tsv)
    {
        String[] lines = tsv.split("\n");
        for (String line : lines)
        {
            String[] parts = tsv.split("\t");
            Hotline next = new Hotline(parts[0], parts[1]);
            hotlines.add(next);
        }
    }

}
