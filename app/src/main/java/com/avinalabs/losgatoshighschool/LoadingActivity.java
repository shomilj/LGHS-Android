//
// LoadingActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// LoadingActivity - loading screen
//

package com.avinalabs.losgatoshighschool;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingActivity extends AppCompatActivity {

    private TextView mTitleLabel;
    private TextView mDayTypeLabel;
    private ProgressBar mProgressBar;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();

    // pre: none
    // post: sets up the screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        setupDayType();

        // starts the download if network is connected
        if (GlobalUtils.isConnected(this)) {
            startDownload();
        } else {
            showNetworkError();
        }
    }

    // pre: none
    // post: starts download in background thread
    private void startDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                downloadFiles();
            }
        }).start();
    }

    // pre: none
    // post: shows an error if network isn't connected
    private void showNetworkError() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("LG CATS will have limited access to school resources without network. " +
                "Please connect to WiFi or cellular data for full functionality.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        nextScreen();
                    }
                });
        alertDialog.show();
    }

    // pre: none
    // post: sets up the day type indicator
    private void setupDayType() {
        DayCalendar cal = new DayCalendar(this);
        String description = cal.getDescription();
        if (description == null) description = "";
        mDayTypeLabel.setText(description);
        mDayTypeLabel.setTextColor(cal.getTextColorID());
        mTitleLabel.setTextColor(cal.getTextColorID());
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.loading_activity_constraint_layout);
        constraintLayout.setBackgroundColor(cal.getBackgroundColorID());
    }

    // pre: none
    // post: initializes private variables
    private void assignVariables() {
        mTitleLabel = (TextView) findViewById(R.id.loading_screen_title);
        mDayTypeLabel = (TextView) findViewById(R.id.loading_screen_day_type_label);
        mProgressBar = (ProgressBar) findViewById(R.id.initial_loading_spinner);
    }

    // pre: none
    // post: sets up view
    private void setupView() {
        setContentView(R.layout.activity_loading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    // pre: none
    // post; moves onto the next appropriate screen
    // if users are not logged in then goes to login screen
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

    // pre: none
    // post: downloads files & uploads progress bar
    public void downloadFiles() {
        FileUtil.writeToFile(FileUtil.FILE_B_LINKS, StringUtil.getUrlContents(LinkUtils.LINK_B_LINKS), getApplicationContext());
        updateProgress();
        FileUtil.writeToFile(FileUtil.FILE_F_LINKS, StringUtil.getUrlContents(LinkUtils.LINK_F_LINKS), getApplicationContext());
        updateProgress();
        FileUtil.writeToFile(FileUtil.FILE_RESOURCES, StringUtil.getUrlContents(LinkUtils.LINK_RESOURCES), getApplicationContext());
        updateProgress();

        //FileUtil.writeToFile(FileUtil.FILE_LOGINS, StringUtil.getUrlContents(LinkUtils.LINK_LOGINS), getApplicationContext());
        //updateProgress();
        //FileUtil.writeToFile(FileUtil.FILE_CALENDAR, StringUtil.getUrlContents(LinkUtils.LINK_CALENDAR), getApplicationContext());
        //updateProgress();
        //FileUtil.writeToFile(FileUtil.FILE_COUNTDOWN, StringUtil.getUrlContents(LinkUtils.LINK_COUNTDOWN), getApplicationContext());
        //updateProgress();
        nextScreen();
    }

    // pre: none
    // post: adds 20% to progress bar
    private void updateProgress() {
        mHandler.post(new Runnable() {
            public void run() {
                mProgressBar.incrementProgressBy(20);
            }
        });
    }

}
