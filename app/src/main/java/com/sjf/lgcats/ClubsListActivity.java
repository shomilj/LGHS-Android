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
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains a list of Club objects
 *
 * @author  Shomil Jain
 * @author  Quintin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
 */

public class ClubsListActivity extends AppCompatActivity {

    private ArrayList<Club> clubs;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ClubsListActivity was created");
        setupView();
        assignVariables();
        startDownload();
    }

    /**
     * Sets up the screen with a layout and a name
     */
    private void setupView() {
        setContentView(R.layout.activity_clubs_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Clubs");
    }

    /**
     * makes a list view and turns clubs and makes clubs and arrayList
     */
    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.club_list_view);
        clubs = new ArrayList<>();
    }

    /**
     * download clubs in background
     * must be in background thread to work
     */
    private void startDownload() {
        //
        new Thread(new Runnable() {
            @Override
            public void run() {
                // read/parse club list
                fetchClubs();
            }
        }).start();
    }


    public void fetchClubs() {
        // get the link associated with the club spreadsheet
        String link = LinkUtils.getLink(LinkUtils.HOST_CLUBS, getApplicationContext());
        System.out.println(link);
        // download the contents of the text file at the link
        String file = StringUtil.getUrlContents(link);
        System.out.println(file);
        parseClubs(file);
        sortClubList();
        updateUI();
    }

    public void parseClubs(String file) {
        // split the spreadsheet into rows
        String[] rows = file.split("\r");

        // ignore first row bc of header
        // start at index i = 1 and go to number of rows
        for (int i = 1; i < rows.length; i++) {

            // split the row into cells
            String[] cells = rows[i].split("\t");

            // if the row has 4 cells, then it's valid
            if (cells.length > 7) {

                // assign variables based on indices
                String name = cells[0];
                String day = cells[1];
                String time = cells[2];
                String location = cells[3];
                String president = cells[4];
                String vicePresident = cells[5];
                String advisor = cells[6];
                String email = cells[7];

                // create a new club & add it
                Club c = new Club(name, day, time, location, president, vicePresident, advisor, email);
                clubs.add(c);
            }
        }
        System.out.println(clubs);
    }

    private void sortClubList() {
        Collections.sort(clubs, new Comparator<Club>() {
            @Override
            public int compare(Club c2, Club c1) {
                return (c2.getName().compareTo(c1.getName()));
            }
        });
    }
    private void updateUI() {
        runOnUiThread(new Runnable() {
            public void run() {
                fillListView();
                setupListListener();
            }
        });
    }

    private void setupListListener() {
        final Context context = this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailIntent = new Intent(context, ClubsDetailActivity.class);
                Club club = clubs.get(position);
                detailIntent.putExtra("Club", club);
                startActivity(detailIntent);
            }
        });
    }

    private void fillListView() {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (Club club : clubs) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put(Club.lv_item_1_key, club.getName());
            String second = "" + club.getDay() + " - " + club.getTime();
            datum.put(Club.lv_item_2_key, second);
            data.add(datum);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[]{Club.lv_item_1_key, Club.lv_item_2_key},
                new int[]{android.R.id.text1, android.R.id.text2});

        if (mListView == null) {
            System.out.println("null");
        } else {
            mListView.setAdapter(adapter);
        }
    }

}
