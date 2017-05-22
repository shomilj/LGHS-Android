package com.sjf.lgcats;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    private Button calendarCalendarButton;
    private TextView calendarBellScheduleHeader;
    private TextView calendarBellScheduleBlock1;
    private TextView calendarBellScheduleTutorial;
    private TextView calendarBellScheduleBlock2;
    private TextView calendarBellScheduleLunch;
    private TextView calendarBellScheduleBlock3;
    private TextView calendarBellScheduleBlock4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendarCalendarButton = (Button) findViewById(R.id.calendar_calendar_button);

    }

}
