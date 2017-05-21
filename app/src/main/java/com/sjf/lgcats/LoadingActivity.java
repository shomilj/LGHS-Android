package com.sjf.lgcats;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

        // get the date
        DateFormat dateFormat = new SimpleDateFormat("HH:mm\nyyyy/MM/dd");
        Date date = new Date();

        //Calendar cal = Calendar.getInstance();

        // display today's date
        todaysDate = (TextView) findViewById(R.id.todays_date);
        todaysDate.setText(dateFormat.format(date));

        // display whether today is a black day or an orange day
        orangeBlackDayDisplay = (TextView) findViewById(R.id.orange_black_day_display);
        String orangeBlackDayDisplayText = "Today " + getString(R.string.orange_day);
        orangeBlackDayDisplay.setText(orangeBlackDayDisplayText);

        DayCalendar cal = new DayCalendar(getApplicationContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                downloadFiles();
            }
        }).start();

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
        FileUtil.writeToFile(FileUtil.FILE_LINKS, getUrlContents(LinkUtils.LINK_LINKS), getApplicationContext());
        FileUtil.writeToFile(FileUtil.FILE_HOTLINES, getUrlContents(LinkUtils.LINK_HOTLINES), getApplicationContext());
        FileUtil.writeToFile(FileUtil.FILE_LOGINS, getUrlContents(LinkUtils.LINK_LOGINS), getApplicationContext());
        FileUtil.writeToFile(FileUtil.FILE_CALENDAR, getUrlContents(LinkUtils.LINK_CALENDAR), getApplicationContext());
        FileUtil.writeToFile(FileUtil.FILE_COUNTDOWN, getUrlContents(LinkUtils.LINK_COUNTDOWN), getApplicationContext());
        System.out.println("FINISHED DOWNLOADING FILES");
        nextScreen();
    }

    private static String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder();

        // many of these calls can throw exceptions, so i've just
        // wrapped them all in one try/catch statement.
        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
