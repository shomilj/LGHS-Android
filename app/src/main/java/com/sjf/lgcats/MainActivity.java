package com.sjf.lgcats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

/**
 * The ultimate nexus of the LG CATS app. Provides access to other activities.
 *
 * @author  Shomil Jain
 * @author  Quentin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // private TextView mainDay
    private Button mainAnnouncementButton;
    private Button mainCanvasButton;
    private Button mainCalendarButton;
    private Button mainStudentIDButton;
    private Button mainBellScheduleButton;
    private Button mainClubsListButton;
    private Button mainCollegesListButton;
    private Button mainHotlinesButton;
    private Button mainLGHSTwitterFeedButton;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //mainDay = (TextView) findViewById(R.id.main_day);
        mainAnnouncementButton = (Button) findViewById(R.id.main_announcement_button);
        mainCanvasButton = (Button) findViewById(R.id.main_canvas_button);
        mainCalendarButton = (Button) findViewById(R.id.main_calendar_button);
        mainStudentIDButton = (Button) findViewById(R.id.main_studentID_button);
        mainBellScheduleButton = (Button) findViewById(R.id.main_bellSchedule_button);
        mainClubsListButton = (Button) findViewById(R.id.main_clubsList_button);
        mainCollegesListButton = (Button) findViewById(R.id.main_collegesList_button);
        mainHotlinesButton = (Button) findViewById(R.id.main_hotlines_button);
        mainLGHSTwitterFeedButton = (Button) findViewById(R.id.main_LGHSTwitterFeed_button);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // display whether it is a black or an orange day
        // currently not working
        String dayColor = getString(R.string.today) + " " + getString(R.string.black_day) + ".";
        getSupportActionBar().setTitle(dayColor);

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

        mainCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
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

        mainHotlinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HotlinesActivity.class);
                startActivity(intent);
            }
        });

        mainLGHSTwitterFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LGHSTwitterFeedActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
