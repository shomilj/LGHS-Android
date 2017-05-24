package com.sjf.lgcats;

import android.support.v7.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


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

        //xml needs to be created still
        //setContentView(R.layout.activity_countdown);

        //listView must be added to xml
        //mListView = (ListView) findViewById(R.id.announcement_list_view);

        //initialize eventList
        eventList = new ArrayList<>();

        //Open background thread for fetching countdown data
        new Thread(new Runnable() {
            @Override
            public void run() {
                // read/parse announcement list
                fetchEvents();
            }
        }).start();
    }

    private void fetchEvents()
    {
        String link = LinkUtils.getLink(LinkUtils.LINK_COUNTDOWN, getApplicationContext());
        String file = StringUtil.getUrlContents(link);

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

    public void fillListView()
    {
        //Data to display
        int[] dayCounts = new int[this.eventList.size()];
        String[] descriptions = new String[this.eventList.size()];
        String[] dateStrings = new String[this.eventList.size()];

        //fetch data to display
        for(int i = 0; i < this.eventList.size(); i++)
        {
            CountdownEvent event = this.eventList.get(i);
            Date now = new Date();
            Date eventDate = event.getEventDate();
            long diff = eventDate.getTime() - now.getTime();
            dayCounts[i] = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            descriptions[i] = this.eventList.get(i).getDescription();

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateStrings[i] = dateFormat.format(this.eventList.get(i).getEventDate());
        }


        //implement UI. Custom ArrayAdapter?
       //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ?);
        if (mListView == null) {
            System.out.println("There was a problem accessing the ListView");
            return;
        }
        //Assign UIElements to display data.
        //mListView.setAdapter(adapter);
    }
}
