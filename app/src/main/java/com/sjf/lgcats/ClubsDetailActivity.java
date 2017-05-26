package com.sjf.lgcats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClubsDetailActivity extends AppCompatActivity {

    private Club club;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        fetchExtras();
        fillListView();
    }

    private void fetchExtras() {
        club = (Club)this.getIntent().getSerializableExtra("Club");

    }

    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.club_detail_list_view);
    }

    private void setupView() {
        setContentView(R.layout.activity_clubs_detail);
        setTitle("Clubs @ LGHS");
    }

    private void fillListView() {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        Map<String, String> m1 = new HashMap<>();
        m1.put("First Item", "Club Name");
        m1.put("Second Item", club.getName());
        data.add(m1);

        Map<String, String> m2 = new HashMap<>();
        m2.put("First Item", "Typical Meeting Day(s)");
        m2.put("Second Item", club.getDay());
        data.add(m2);

        Map<String, String> m3 = new HashMap<>();
        m3.put("First Item", "Meeting Time(s)");
        m3.put("Second Item", club.getTime());
        data.add(m3);

        Map<String, String> m4 = new HashMap<>();
        m4.put("First Item", "Location");
        m4.put("Second Item", club.getLocation());
        data.add(m4);

        Map<String, String> m5 = new HashMap<>();
        m5.put("First Item", "President");
        m5.put("Second Item", club.getPresident());
        data.add(m5);

        Map<String, String> m6 = new HashMap<>();
        m6.put("First Item", "Vice President");
        m6.put("Second Item", club.getVicePresident());
        data.add(m6);

        Map<String, String> m7 = new HashMap<>();
        m7.put("First Item", "Staff Advisor");
        m7.put("Second Item", club.getAdvisor());
        data.add(m7);

        Map<String, String> m8 = new HashMap<>();
        m8.put("First Item", "Email to Contact Club");
        m8.put("Second Item", club.getEmail());
        data.add(m8);

        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[]{"First Item", "Second Item"},
                new int[]{android.R.id.text1, android.R.id.text2});

        // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        if (mListView == null) {
            System.out.println("null");
        } else {
            mListView.setAdapter(adapter);
        }
    }
}

