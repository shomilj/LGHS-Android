package com.sjf.lgcats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import static com.sjf.lgcats.R.attr.title;

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

    private TextView mTitleLabel;
    private TextView mDayTypeLabel;

    private ProgressBar mProgressBar;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        setupDayType();

        if (GlobalUtils.isConnected(this)) {
            startDownload();
        } else {
            showNetworkError();
        }
    }

    private void startDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                downloadFiles();
            }
        }).start();
    }

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

    private void assignVariables() {
        mTitleLabel = (TextView) findViewById(R.id.loading_screen_title);
        mDayTypeLabel = (TextView) findViewById(R.id.loading_screen_day_type_label);
        mProgressBar = (ProgressBar) findViewById(R.id.initial_loading_spinner);
    }

    private void setupView() {
        setContentView(R.layout.activity_loading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        // Update the progress bar
        updateProgress();
        FileUtil.writeToFile(FileUtil.FILE_HOTLINES, StringUtil.getUrlContents(LinkUtils.LINK_HOTLINES), getApplicationContext());
        updateProgress();
        FileUtil.writeToFile(FileUtil.FILE_LOGINS, StringUtil.getUrlContents(LinkUtils.LINK_LOGINS), getApplicationContext());
        updateProgress();
        FileUtil.writeToFile(FileUtil.FILE_CALENDAR, StringUtil.getUrlContents(LinkUtils.LINK_CALENDAR), getApplicationContext());
        updateProgress();
        FileUtil.writeToFile(FileUtil.FILE_COUNTDOWN, StringUtil.getUrlContents(LinkUtils.LINK_COUNTDOWN), getApplicationContext());
        updateProgress();
        nextScreen();
    }

    private void updateProgress() {
        mHandler.post(new Runnable() {
            public void run() {
                mProgressBar.incrementProgressBy(20);
            }
        });
    }

}
