package com.sjf.lgcats;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

/**
 * Contains a list of College objects
 *
 * @author  Shomil Jain
 * @author  Quintin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
 */

public class CollegesListActivity extends AppCompatActivity {

    private ArrayList<College> colleges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // display some sort of a loading indicator
        // remove loading indicator when files have been read/parsed

        // initialize colleges arraylist
        colleges = new ArrayList<>();

        // download colleges in background
        // must be in background thread to work
        new Thread(new Runnable() {
            @Override
            public void run() {
                // read/parse college list
                fetchColleges();
            }
        }).start();

        setContentView(R.layout.activity_colleges_list);
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

    public void fetchColleges() {
        // get the link associated with the college spreadsheet
        String link = LinkUtils.getLink(LinkUtils.HOST_COLLEGES, getApplicationContext());

        // download the contents of the text file at the link
        String file = StringUtil.getUrlContents(link);

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
                if (col.isUpcoming()) colleges.add(col);
            }
        }

        // check that colleges have parsed correctly
        for (College college : colleges) {
            System.out.println(college);
        }
    }

}
