package com.sjf.lgcats;

import android.content.Intent;
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
    private TextView orangeBlackDayDisplay;
    private Button temporaryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // display whether today is a black day or an orange day
        DayCalendar cal = new DayCalendar(getApplicationContext());
        orangeBlackDayDisplay = (TextView) findViewById(R.id.orange_black_day_display);
        orangeBlackDayDisplay.setText(cal.getDescription());

        // download files in background
        new Thread(new Runnable() {
            @Override
            public void run() {
                downloadFiles();
            }
        }).start();

        StudentList list = new StudentList(getApplicationContext());
        System.out.println(list.checkLogin("Jain", "109431"));
        System.out.println(list.checkLogin("jain", "109431"));
        System.out.println(list.checkLogin("jain", "104431"));


        // get rid of this
        temporaryButton = (Button) findViewById(R.id.temporary_button);
        temporaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextScreen();
            }
        });

    }

    public void nextScreen() {
        Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
        startActivity(intent);
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
