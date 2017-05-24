package com.sjf.lgcats;

import android.support.v7.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


/**
 * Created by QDL on 5/23/17.
 */

public class CountdownActivity extends AppCompatActivity {

    private ArrayList<CountdownEvent> eventList;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedinstanceState)
    {
        super.onCreate(savedinstanceState);
        setupView();
        assignVariables();
        startDownload();
    }

    private void startDownload() {
        // download colleges in background
        // must be in background thread to work
        new Thread(new Runnable() {
            @Override
            public void run() {
                // read/parse college list
                fetchEvents();
            }
        }).start();
    }

    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.countdown_list_view);
        eventList = new ArrayList<>();
    }

    private void setupView() {
        setContentView(R.layout.activity_countdown);

        setTitle("Countdown Calendar");
    }

    private void fetchEvents()
    {
        String file = FileUtil.readFromFile(FileUtil.FILE_COUNTDOWN, getApplicationContext());
        if(file == null)
        {
            System.out.print("There was a problem loading the file");
            return;
        }

        try
        {
            String[] rows = file.split("\r");

            for(int i = 1; i < rows.length; i++)
            {
                String[] cells = rows[i].split("\t");
                Date eventDate = processDate(cells[1]);
                if(eventDate == null)
                {
                    System.out.println("There was an issue fetching the date");
                }

                CountdownEvent event = new CountdownEvent(cells[0], cells[2], eventDate);
                eventList.add(event);
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println(e);
            System.out.println("There was an issue parsing the countdown data");
        }
        updateUI();

    }

    private void updateUI() {
        runOnUiThread(new Runnable() {
            public void run() {
                fillListView();
            }
        });
    }

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
                String day = "" + daysToEvent + " days until " + description;
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
