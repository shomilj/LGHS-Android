package com.sjf.lgcats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains a list of College objects
 *
 * @author  Shomil Jain
 * @author  Quintin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
 */

public class CollegesListActivity extends AppCompatActivity {

    private ArrayList<College> colleges;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        startDownload();
    }

    private void startDownload() {
        // download colleges in background
        // must be in background thread to work
        new Thread(new Runnable() {
            @Override
            public void run() {
                // read/parse college list
                fetchColleges();
            }
        }).start();
    }

    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.college_list_view);
        colleges = new ArrayList<>();
    }

    private void setupView() {
        setContentView(R.layout.activity_colleges_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Colleges @ LGHS");
    }

    public void fetchColleges() {
        // get the link associated with the college spreadsheet
        String link = LinkUtils.getLink(LinkUtils.HOST_COLLEGES, getApplicationContext());

        // download the contents of the text file at the link
        String file = StringUtil.getUrlContents(link);

        parseColleges(file);
        sortCollegeList();
        updateUI();
    }

    private void parseColleges(String file) {
        // split the spreadsheet into rows
        String[] rows = file.split("\r");

        // ignore first row bc of header
        // start at index i = 1 and go to number of rows
        for (int i = 1; i < rows.length; i++) {

            // split the row into cells
            String[] cells = rows[i].split("\t");

            // if the row has 4 cells, then it's valid
            if (cells.length > 3) {

                // assign variables based on indices
                String name = cells[0];
                String date = cells[1];
                String time = cells[2];
                String location = cells[3];

                // create a new college & add it if it's upcoming
                College col = new College(name, time, location, date);
                // if (col.isUpcoming()) colleges.add(col);
                colleges.add(col);
            }
        }
    }

    private void updateUI() {
        runOnUiThread(new Runnable() {
            public void run() {
                fillListView();
                setupListListener();
            }
        });
    }

    private void sortCollegeList() {
        Collections.sort(colleges, new Comparator<College>() {
            @Override
            public int compare(College c2, College c1) {
                return c2.getDateString().compareTo(c1.getDateString());
            }
        });
    }

    private void setupListListener() {
        final Context context = this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailIntent = new Intent(context, CollegesDetailActivity.class);
                College college = colleges.get(position);
                detailIntent.putExtra("name", college.getName());
                detailIntent.putExtra("dateString", college.getDateString());
                detailIntent.putExtra("location", college.getLocation());
                startActivity(detailIntent);
            }
        });
    }

    /**
     * creates a hashmap with keys and values
     */
    private void fillListView() {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (College college : colleges) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("First Line", college.getName());
            datum.put("Second Line", college.getDateString());
            data.add(datum);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[]{"First Line", "Second Line"},
                new int[]{android.R.id.text1, android.R.id.text2});

        if (mListView == null) {
            System.out.println("null");
        } else {
            mListView.setAdapter(adapter);
        }
    }

}
