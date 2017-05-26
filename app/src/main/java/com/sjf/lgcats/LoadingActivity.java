package com.sjf.lgcats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
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

    private TextView title;
    private TextView mDayTypeLabel;
    private String dayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (TextView) findViewById(R.id.loading_screen_title);
        mDayTypeLabel = (TextView) findViewById(R.id.loading_screen_day_type_label);

        // display whether today is a black day or an orange day
        DayCalendar cal = new DayCalendar(getApplicationContext());
        dayText = cal.getDescription();
        mDayTypeLabel.setText(dayText);

        // download files in background
        new Thread(new Runnable() {
            @Override
            public void run() {
                downloadFiles();
            }
        }).start();

        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.loading_activity_constraint_layout);
        if (dayText.equals(getString(R.string.orange_day))) {
            constraintLayout.setBackgroundColor(ContextCompat.getColor(LoadingActivity.this, R.color.colorPrimary));
            title.setTextColor(ContextCompat.getColor(LoadingActivity.this, R.color.black));
            mDayTypeLabel.setTextColor(ContextCompat.getColor(LoadingActivity.this, R.color.black));
        }
        else if (dayText.equals(getString(R.string.black_day))) {
            constraintLayout.setBackgroundColor(ContextCompat.getColor(LoadingActivity.this, R.color.black));
            title.setTextColor(ContextCompat.getColor(LoadingActivity.this, R.color.colorPrimary));
            mDayTypeLabel.setTextColor(ContextCompat.getColor(LoadingActivity.this, R.color.colorPrimary));
        }
        else {
            constraintLayout.setBackgroundColor(ContextCompat.getColor(LoadingActivity.this, R.color.colorAccent));
            title.setTextColor(ContextCompat.getColor(LoadingActivity.this, R.color.black));
            mDayTypeLabel.setTextColor(ContextCompat.getColor(LoadingActivity.this, R.color.black));
        }
        
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
