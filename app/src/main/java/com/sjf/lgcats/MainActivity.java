package com.sjf.lgcats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

// Method header
/**
 *
 */

public class MainActivity extends AppCompatActivity {

    private TextView mainDay;
    private Button mainAnnouncementButton;
    private Button mainCanvasButton;
    private Button mainStudentIDButton;
    private Button mainBellScheduleButton;
    private Button mainClubsListButton;
    private Button mainCollegesListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainDay = (TextView) findViewById(R.id.main_day);
        mainAnnouncementButton = (Button) findViewById(R.id.main_announcement_button);
        mainCanvasButton = (Button) findViewById(R.id.main_canvas_button);
        mainStudentIDButton = (Button) findViewById(R.id.main_studentID_button);
        mainBellScheduleButton = (Button) findViewById(R.id.main_bellSchedule_button);
        mainClubsListButton = (Button) findViewById(R.id.main_clubsList_button);
        mainCollegesListButton = (Button) findViewById(R.id.main_collegesList_button);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("LG CATS");

        // display whether it is a black or an orange day
        // currently not working
        String dayColor = getString(R.string.today) + " " + getString(R.string.black_day) + ".";
        mainDay.setText(dayColor);

        // change action bar to black
        //Window window = this.getWindow();
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        mainAnnouncementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnnouncementsActivity.class);
                startActivity(intent);
            }
        });

        mainCanvasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CanvasActivity.class);
                startActivity(intent);
            }
        });

        mainStudentIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentIDActivity.class);
                startActivity(intent);
            }
        });

        mainBellScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BellScheduleActivity.class);
                startActivity(intent);
            }
        });

        mainClubsListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ClubsListActivity.class);
                startActivity(intent);
            }
        });

        mainCollegesListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CollegesListActivity.class);
                startActivity(intent);
            }
        });

    }



}
