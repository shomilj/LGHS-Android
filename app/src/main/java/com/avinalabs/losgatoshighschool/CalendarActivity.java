//
// CalendarActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// CalendarActivity - holds the screen with the bell schedule
//

package com.avinalabs.losgatoshighschool;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.util.DateTimeComponents;

public class CalendarActivity extends AppCompatActivity {
    private ArrayList<CalFeed> mCals;
    private ListView mListView;

    // called when the view is created
    // pre: none
    // post: configures the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        if (mCals == null || mCals.size() == 0) {
            startDownload();
        }
    }

    // pre: none
    // post: sets up the view
    private void setupView() {
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Calendar Events");
    }

    // pre: none
    // post: initializes the private variables
    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.content_calendar);
        mCals = new ArrayList<>();
    }

    // pre: none
    // post: starts downloading the clubs in the background
    private void startDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // read/parse club list
                fetchCals();
            }
        }).start();
    }

    // pre: none
    // post: fetches the club data from the server
    public void fetchCals() {
        // get the link associated with the club spreadsheet
        //String link = null;// LinkUtils.getLink(LinkUtils.Frontend, getApplicationContext());

        // download the contents of the text file at the link
        //String file = StringUtil.getUrlContents(link);

        parseCals();
        //sortClubList();
    }

    // pre: none
    // post: parses club information from the tsv file
    public void parseCals() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("firebase_v1/appData/calendars");

        Query qObj = myRef;

        //System.out.println("club  name"+qObj.getRef().getKey());
        qObj.addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        System.out.println("dataSnapshotCount:"+dataSnapshot.getChildrenCount());
                        if (dataSnapshot.exists())
                        {
                            System.out.println("Data exists");
                            System.out.println(dataSnapshot.getValue().toString());
                            for (DataSnapshot ds : dataSnapshot.getChildren())
                            {
                                System.out.println("DS:"+ds.getValue().toString());
                                CalFeed feed = null;
                                try {
                                    feed = new CalFeed(
                                            ds.child("name").getValue().toString(),
                                            ds.child("url").getValue().toString());

                                }
                                catch (Exception e)
                                {
                                    System.out.println("Club record skipped due to :"+e.getMessage());
                                    continue;
                                }
                                mCals.add(feed);
                                feed.getEvents();

                            }

                            updateUI();

                        }
                        else
                        {
                            System.out.println("Failed to fetch");
                            Toast.makeText(getApplicationContext(), "We couldn't find your student information. Please contact app.lghs@gmail.com for help.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }
        );
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
    // post: sets up the listener for listview taps
    private void setupListListener() {
        final Context context = this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tappedClub(position, context);
            }
        });
    }

    // pre: none
    // post: passes club data to next screen & transitions to club detail screen
    private void tappedClub(int position, Context context) {
        Intent detailIntent = new Intent(context, ClubsDetailActivity.class);
        //CalFeed club = mCals.get(position);
        //detailIntent.putExtra(Club.intent_key, club);
        //startActivity(detailIntent);
    }

    // pre: none
    // post: fills the list view with clubs
    private void fillListView() {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (CalFeed feed : mCals) {
            if (feed.getEvents() != null) {
                for (CalEvent ev : feed.getEvents()) {
                    // hashmap holds club name & day/time string
                    Map<String, String> datum = new HashMap<String, String>(2);
                    datum.put(Club.lv_item_1_key, ev.getSummary());
                    String second = null;

                    if (ev.isAllDay())
                    {
                        second = ev.getStart();
                    }
                    else
                    {
                        second = ev.getStart()+"-"+ev.getEnd();
                    }

                    if (ev.getLocation() != null && ev.getLocation().trim().isEmpty() == false) {
                        second += (" at "+ev.getLocation());
                    }
                    datum.put(Club.lv_item_2_key, second);
                    data.add(datum);
                }
            }
        }

        // connects hashmap to list view
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[]{Club.lv_item_1_key, Club.lv_item_2_key},
                new int[]{android.R.id.text1, android.R.id.text2});

        if (mListView != null) {
            mListView.setAdapter(adapter);
        }
    }

    class CalFeed
    {
        String mName;
        String mUrl;
        List<CalEvent> mEvents;
        Color mColor;
        boolean mIsVisible;
        boolean mIsRead;

        CalFeed(String name, String url)
        {
            mName = name;
            mUrl = url;
        }

        String getName()
        {
            return mName;
        }

        String getUrl()
        {
            return mUrl;
        }

        void show()
        {
            mIsVisible = true;
        }

        void hide()
        {
            mIsVisible = false;
        }

        List<CalEvent> getEvents()
        {
            parse();
            return mEvents;
        }

        void setEvents(List<CalEvent> events)
        {
            mEvents = events;
        }

        void parse()
        {
            if (mEvents == null)
            {
                if (mIsRead == false) {
                    mIsRead = true;
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Fetching:"+mUrl);
                            String calString = StringUtil.getUrlContents(mUrl);
                            //StringReader reader = new StringReader(calString);
                            //CalendarBuilder builder = new CalendarBuilder();
                            ICalendar ical = Biweekly.parse(calString).first();
                            if (ical == null)
                            {
                                System.out.println("Null calendar fetched");
                                return;
                            }
                            try {
                                //Calendar calendar = builder.build(reader);
                                //ComponentList events = calendar.getComponen
                                // ts(Component.VEVENT);
                                List<VEvent> events = ical.getEvents();
                                mEvents = new ArrayList<CalEvent>(events.size());
                                System.out.println("Calendar event count:" + events.size());
                                for (Object item : events) {
                                    VEvent event = (VEvent)item;
                                    if (event.getDateEnd().getValue().before(new Date()))
                                    {
                                        continue;
                                    }
                                    mEvents.add(new CalEvent((VEvent) item));
                                }
                                mIsVisible = true;
                            } catch (Exception e) {
                                System.out.println("Fetching failed:"+mUrl);

                                e.printStackTrace();
                                mIsRead = false;
                            }
                        }
                    });
                    Thread mainThread = Thread.currentThread();
                    thread.start();
                    try {
                        int i = 0;
                        while (mEvents == null && i < 10) {
                            mainThread.sleep(100);
                            i++;
                        }
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    class CalEvent
    {
        private VEvent event;
        private SimpleDateFormat startFmt = new SimpleDateFormat("EEE, MMM d, h:mm a");
        private SimpleDateFormat endFmt = new SimpleDateFormat("h:mm a");
        private SimpleDateFormat allDayFmt = new SimpleDateFormat("EEE, MMM d");
        private boolean allDay;

        CalEvent(VEvent event) {

            this.event = event;
            DateTimeComponents sday = event.getDateStart().getValue().getRawComponents();
            DateTimeComponents eday = event.getDateEnd().getValue().getRawComponents();
            allDay = eday.after(sday);
        }

        String getStart()
        {
            Date dt = event.getDateStart().getValue().getRawComponents().toDate();
            return (isAllDay()) ? allDayFmt.format(dt) : startFmt.format(dt);
        }

        String getEnd()
        {
            Date dt = event.getDateEnd().getValue().getRawComponents().toDate();
            return endFmt.format(dt);
        }

        public String getSummary() {
            return event.getSummary().getValue();
        }

        public boolean isAllDay() {
            return allDay;
        }

        public String getLocation() {
            return event.getLocation().getValue();
        }

        public Color getTintColor() {
            return null;
        }
    }

}
