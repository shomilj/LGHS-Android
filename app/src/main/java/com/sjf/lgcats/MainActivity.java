package com.sjf.lgcats;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * The ultimate nexus of the LG CATS app. Provides access to other activities.
 *
 * @author  Shomil Jain
 * @author  Quintin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView nameTextView = (TextView) headerView.findViewById(R.id.main_nav_student_name_text_view);
        TextView idTextView = (TextView) headerView.findViewById(R.id.main_nav_student_id_text_view);
        ImageView imageView = (ImageView) headerView.findViewById(R.id.main_nav_image_view);

        SharedPreferences prefs = getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE);
        if (UserUtils.isStudent(prefs) && UserUtils.getUsername(prefs) != null) {
            String id = UserUtils.getUsername(prefs);
            StudentList sList = new StudentList(this);
            Student student = sList.getStudent(id);
            String name = student.getFull();
            nameTextView.setText(name);
            idTextView.setText(id);
        }

        MainFragment mainFragment = MainFragment.newInstance();
        openFragmentInContentMain(mainFragment);

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // display whether it is a black or an orange day
        DayCalendar cal = new DayCalendar(getApplicationContext());
        getSupportActionBar().setTitle(cal.getDescription());
    }

    @Override
    public void onBackPressed () {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE).edit();
            UserUtils.logout(prefs);
            Intent intent = new Intent(this, SelectUserTypeActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

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
        } /*else if (id == R.id.nav_news) {
            NewsFragment newsFragment = NewsFragment.newInstance();
            openFragmentInContentMain(newsFragment);
        } else if (id == R.id.nav_calculators) {
            CalculatorsFragment calculatorsFragment = CalculatorsFragment.newInstance();
            openFragmentInContentMain(calculatorsFragment);
        } else if (id == R.id.nav_feedback) {
            FeedbackFragment feedbackFragment = FeedbackFragment.newInstance();
            openFragmentInContentMain(feedbackFragment);
        } else if (id == R.id.nav_bugs) {
            BugsFragment bugsFragment = BugsFragment.newInstance();
            openFragmentInContentMain(bugsFragment);
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openFragmentInContentMain (Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MainActivityFragmentPlaceHolder, fragment);
        fragmentTransaction.commit();
    }

}
