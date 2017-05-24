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

import java.util.ArrayList;

import static com.sjf.lgcats.R.string.colleges;

/**
 * Gives an archive of daily announcements.
 *
 * @author  Shomil Jain
 * @author  Quintin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
 */

public class AnnouncementsActivity extends AppCompatActivity {

    private ArrayList<Announcement> list;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        startDownload();
    }

    private void startDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                fetchAnnouncements();
            }
        }).start();
    }

    private void assignVariables() {
        list = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.announcement_list_view);
    }

    private void setupView() {
        setContentView(R.layout.activity_announcements);
    }


    private void setupListListener() {
        final Context context = this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Announcement a = list.get(position);
                Intent detailIntent = new Intent(context, AnnouncementDetailActivity.class);
                detailIntent.putExtra("date", a.getDate());
                detailIntent.putExtra("content", a.getContent());
                startActivity(detailIntent);
            }
        });

    }

    private void fetchAnnouncements() {


        // get the link associated with the college spreadsheet
        String link = LinkUtils.getLink(LinkUtils.HOST_ANNOUNCEMENTS, getApplicationContext());

        // download the contents of the text file at the link
        String file = StringUtil.getUrlContents(link);

        // split the spreadsheet into rows
        String[] rows = file.split("\r");
        for (String row : rows) {
            System.out.println(row);
        }
        // ignore first row bc of header
        // start at index i = 1 and go to number of rows
        for (int i = 1; i < rows.length; i++) {

            // split the row into cells
            String[] cells = rows[i].split("\t");

            // if the row has 4 cells, then it's valid
            if (cells.length > 1) {

                // assign variables based on indices
                String date = cells[0];
                String content = cells[1];

                // create a new announcement & add it if it's upcoming
                Announcement a = new Announcement(date, content);
                list.add(a);
            }
        }

        // check that colleges have parsed correctly
        for (Announcement a : list) {
            System.out.println(a);
        }
        runOnUiThread(new Runnable() {
            public void run() {
                fillListView();
                setupListListener();
            }
        });
    }

    private void fillListView() {
        String[] listItems = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Announcement a = list.get(i);
            listItems[i] = a.getDate();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
    }


}
