package com.sjf.lgcats;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        //Calendar cal = Calendar.getInstance();

        // display today's date
        todaysDate = (TextView) findViewById(R.id.todays_date);
        todaysDate.setText(dateFormat.format(date));

        // display whether today is a black day or an orange day
        orangeBlackDayDisplay = (TextView) findViewById(R.id.orange_black_day_display);
        String orangeBlackDayDisplayText = "Today " + getString(R.string.orange_day);
        orangeBlackDayDisplay.setText(orangeBlackDayDisplayText);

        // parse necessary info upon loading
        parse();

        temporaryButton = (Button) findViewById(R.id.temporary_button);
        temporaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //finish();
        // don't do this until the activity fully works
        // and autoloads the main activity
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_loading, menu);
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

    public void parse() {

        /*File dir = new File(Environment.getExternalStorageDirectory() + "/Download/your folder/");
        dir.mkdirs();

        String url = "bit.ly/LGCATSlinks";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Some description");
        request.setTitle("Some title");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "name-of-the-file.ext");

        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

        ArrayList<ArrayList<String>> s = StringUtil.parseTSV();
        parseSchoolCalendar();
        parseClubs();*/
    }

    public void parseSchoolCalendar() {


    }

    public void parseClubs() {

    }
}
