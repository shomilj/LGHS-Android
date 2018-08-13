//
// CountdownActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// CountdownActivity - holds a list of important events at lghs & days to those events
//

package com.avinalabs.losgatoshighschool;

import android.support.v7.app.AppCompatActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CountdownActivity extends AppCompatActivity {

    private ArrayList<CountdownEvent> eventList;
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
                fetchEvents();
            }
        }).start();
    }

    // pre: none
    // post: initializes the private variables
    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.countdown_list_view);
        eventList = new ArrayList<>();
    }

    // pre: none
    // post: sets up the view
    private void setupView() {
        setContentView(R.layout.activity_countdown);
        setTitle("Countdown Calendar");
    }

    // pre: none
    // post: fetches the events from the server
    private void fetchEvents() {
        String file = null; //FileUtil.readFromFile(FileUtil.FILE_COUNTDOWN, getApplicationContext());
        if (file == null) {
            System.out.print("There was a problem loading the file");
            return;
        }

        try {
            String[] rows = file.split("\r");
            for (int i = 1; i < rows.length; i++) {
                String[] cells = rows[i].split("\t");
                Date eventDate = processDate(cells[1]);
                if (eventDate == null) {
                    System.out.println("There was an issue fetching the date");
                }
                CountdownEvent event = new CountdownEvent(cells[0], cells[2], eventDate);
                eventList.add(event);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            System.out.println("There was an issue parsing the countdown data");
        }
        updateUI();
    }

    // pre: none
    // post: updates UI
    private void updateUI() {
        runOnUiThread(new Runnable() {
            public void run() {
                fillListView();
            }
        });
    }

    // pre: none
    // post: process date from string
    public Date processDate(String dateString)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        Date eventDate = new Date();
        try
        {
            eventDate = formatter.parse(dateString);
        }
        catch (ParseException e)
        {
            System.out.println("There was a problem pasing the date");
            System.out.println(e);
            return null;
        }

        return eventDate;
    }

    // pre: none
    // post: fills listview
    private void fillListView()
    {
        List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

        //fetch data to display
        for(int i = 0; i < this.eventList.size(); i++)
        {
            CountdownEvent event = this.eventList.get(i);
            int daysToEvent = event.getDaysFromToday();
            if (daysToEvent > 0) {
                String description = event.getDescription();
                String date = event.getDateString();
                String day;
                if (daysToEvent == 1) {
                    day = "1 day until " + description;
                } else {
                    day = "" + daysToEvent + " days until " + description;
                }
                HashMap<String, String> datum = new HashMap<String, String>();
                datum.put("day", day);
                datum.put("date", date);
                data.add(datum);
            }
        }

        SimpleAdapter adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2, new String[]{"day", "date"}, new int[]{android.R.id.text1, android.R.id.text2});

        if (mListView == null) {
            System.out.println("null");
        } else {
            mListView.setAdapter(adapter);

        }
    }
}
