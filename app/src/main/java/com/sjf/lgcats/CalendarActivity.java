package com.sjf.lgcats;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    private Button calendarCalendarButton;
    private Button calendarBellScheduleHeader;
    private TextView calendarBellScheduleBlock1;
    private TextView calendarBellScheduleTutorial;
    private TextView calendarBellScheduleBlock2;
    private TextView calendarBellScheduleLunch;
    private TextView calendarBellScheduleBlock3;
    private TextView calendarBellScheduleBlock4;
    private TextView calendarBellScheduleBlock1time;
    private TextView calendarBellScheduleTutorialtime;
    private TextView calendarBellScheduleBlock2time;
    private TextView calendarBellScheduleLunchtime;
    private TextView calendarBellScheduleBlock3time;
    private TextView calendarBellScheduleBlock4time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.calendar));

        calendarCalendarButton = (Button) findViewById(R.id.calendar_calendar_button);
        calendarBellScheduleHeader = (Button) findViewById(R.id.calendar_bell_schedule_header);
        calendarBellScheduleBlock1 = (TextView) findViewById(R.id.calendar_bell_schedule_block_1);
        calendarBellScheduleTutorial = (TextView) findViewById(R.id.calendar_bell_schedule_tutorial);
        calendarBellScheduleBlock2 = (TextView) findViewById(R.id.calendar_bell_schedule_block_2);
        calendarBellScheduleLunch = (TextView) findViewById(R.id.calendar_bell_schedule_lunch);
        calendarBellScheduleBlock3 = (TextView) findViewById(R.id.calendar_bell_schedule_block_3);
        calendarBellScheduleBlock4 = (TextView) findViewById(R.id.calendar_bell_schedule_block_4);
        calendarBellScheduleBlock1time = (TextView) findViewById(R.id.calendar_bell_schedule_block_1_time);
        calendarBellScheduleTutorialtime = (TextView) findViewById(R.id.calendar_bell_schedule_tutorial_time);
        calendarBellScheduleBlock2time = (TextView) findViewById(R.id.calendar_bell_schedule_block_2_time);
        calendarBellScheduleLunchtime = (TextView) findViewById(R.id.calendar_bell_schedule_lunch_time);
        calendarBellScheduleBlock3time = (TextView) findViewById(R.id.calendar_bell_schedule_block_3_time);
        calendarBellScheduleBlock4time = (TextView) findViewById(R.id.calendar_bell_schedule_block_4_time);

        calendarBellScheduleBlock1.setPadding(60, 0, 0, 0);
        calendarBellScheduleTutorial.setPadding(60, 0, 0, 0);
        calendarBellScheduleBlock2.setPadding(60, 0, 0, 0);
        calendarBellScheduleLunch.setPadding(60, 0, 0, 0);
        calendarBellScheduleBlock3.setPadding(60, 0, 0, 0);
        calendarBellScheduleBlock4.setPadding(60, 0, 0, 0);
        calendarBellScheduleBlock1time.setPadding(60, 0, 0, 0);
        calendarBellScheduleTutorialtime.setPadding(60, 0, 0, 0);
        calendarBellScheduleBlock2time.setPadding(60, 0, 0, 0);
        calendarBellScheduleLunchtime.setPadding(60, 0, 0, 0);
        calendarBellScheduleBlock3time.setPadding(60, 0, 0, 0);
        calendarBellScheduleBlock4time.setPadding(60, 0, 0, 0);

        calendarCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarActivity.this, WebViewActivity.class);
                intent.putExtra("app", 0);
                intent.putExtra("url", LinkUtils.getLink(LinkUtils.GENERAL_CALENDAR, getApplicationContext()));
                startActivity(intent);
            }
        });

        calendarBellScheduleHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
