//
// MainActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// MainActivity - home page of the app. Holds the "today" view
//

package com.avinalabs.losgatoshighschool;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;

    // pre: none
    // post: sets up the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // link XML file to java file
        setContentView(R.layout.activity_main);

        assignVariables();

        // setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set navigation view listener
        navigationView.setNavigationItemSelectedListener(this);

        openFragment();

        // set up action bar drawer toggle button
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // other various setup methods
        setupNavHeader();
        setDayType();
    }

    // pre: none
    // post: opens MainFragment - a subset of this class
    private void openFragment() {
        MainFragment mainFragment = MainFragment.newInstance();
        openFragmentInContentMain(mainFragment);
    }

    // pre: none
    // post: initializes private variables
    private void assignVariables() {
        drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    // pre: none
    // post: sets the day type in the header
    private void setDayType() {
        DayCalendar cal = new DayCalendar(getApplicationContext());
        getSupportActionBar().setTitle(cal.getDescription());
    }

    // pre: none
    // post: sets up the navigation bar header to show name/id for student
    private void setupNavHeader() {
        View headerView = navigationView.getHeaderView(0);
        TextView nameTextView = (TextView) headerView.findViewById(R.id.main_nav_student_name_text_view);
        TextView idTextView = (TextView) headerView.findViewById(R.id.main_nav_student_id_text_view);
        ImageView imageView = (ImageView) headerView.findViewById(R.id.main_nav_image_view);

        SharedPreferences prefs = getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE);
        if (UserUtils.isStudent(prefs) && UserUtils.getStudent(prefs) != null) {

            Student student = UserUtils.getStudent(prefs);
            String name = student.getFull();
            nameTextView.setText(name);
            idTextView.setText(student.getId());
        }
    }

    // pre: none
    // post: closes drawer if back button pressed
    @Override
    public void onBackPressed () {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // pre: none
    // post: configures the menu for logout button
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // pre: none
    // post: called when menu item is selected
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // logs out if logout button pressed
        if (id == R.id.action_logout) {
            SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE).edit();
            UserUtils.logout(prefs);
            Intent intent = new Intent(this, SelectUserTypeActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    // pre: none
    // post: opens the fragments for Main/Resource files
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected (MenuItem item) {
        // Handle calendar_navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            MainFragment mainFragment = MainFragment.newInstance();
            openFragmentInContentMain(mainFragment);
        } else if (id == R.id.nav_resources) {
            ResourcesFragment resourcesFragment = ResourcesFragment.newInstance();
            openFragmentInContentMain(resourcesFragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // pre: none
    // post: opens the fragment in the main content screen
    public void openFragmentInContentMain (Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MainActivityFragmentPlaceHolder, fragment);
        fragmentTransaction.commit();
    }

}
