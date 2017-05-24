package com.sjf.lgcats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Gives the app time to load essential information.
 * Informs the user of the date and day color.
 * Downloads initial data.
 * 
 *
 * @author  Shomil Jain
 * @author  Quintin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
 */

public class LoadingActivity extends AppCompatActivity {

    private TextView todaysDate;
    private TextView mDayTypeLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // display whether today is a black day or an orange day
        DayCalendar cal = new DayCalendar(getApplicationContext());
        mDayTypeLabel = (TextView) findViewById(R.id.loading_screen_day_type_label);
        mDayTypeLabel.setText(cal.getDescription());

        // download files in background
        new Thread(new Runnable() {
            @Override
            public void run() {
                downloadFiles();
            }
        }).start();
    }

    public void nextScreen() {
        SharedPreferences prefs = getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE);
        String userType = prefs.getString("UserType", null);
        if (userType == null) {
            Intent intent = new Intent(LoadingActivity.this, SelectUserTypeActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
            startActivity(intent);
        }
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

    public void downloadFiles() {
        FileUtil.writeToFile(FileUtil.FILE_LINKS, StringUtil.getUrlContents(LinkUtils.LINK_LINKS), getApplicationContext());
        FileUtil.writeToFile(FileUtil.FILE_HOTLINES, StringUtil.getUrlContents(LinkUtils.LINK_HOTLINES), getApplicationContext());
        FileUtil.writeToFile(FileUtil.FILE_LOGINS, StringUtil.getUrlContents(LinkUtils.LINK_LOGINS), getApplicationContext());
        FileUtil.writeToFile(FileUtil.FILE_CALENDAR, StringUtil.getUrlContents(LinkUtils.LINK_CALENDAR), getApplicationContext());
        FileUtil.writeToFile(FileUtil.FILE_COUNTDOWN, StringUtil.getUrlContents(LinkUtils.LINK_COUNTDOWN), getApplicationContext());
        System.out.println("FINISHED DOWNLOADING FILES");
        nextScreen();
    }

}
