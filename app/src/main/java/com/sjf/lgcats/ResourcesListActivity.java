package com.sjf.lgcats;

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ResourcesListActivity extends AppCompatActivity {

    private HashMap<String, String> map;
    private ListView mListView;
    private String[] listArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        fetchExtras();
        fillListView();
        setupListListener();
    }

    private void goToWebView(String linkKey) {
        String link = LinkUtils.getLink(linkKey, this);
        if (link != null) {
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("app", 0);
            intent.putExtra("url", link);
            startActivity(intent);
        }
    }

    private void setupListListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                goToWebView(map.get(listArr[position]));
            }
        });
    }

    private void setupView() {
        setContentView(R.layout.activity_resources_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Resources");
    }

    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.resources_list_activity_listview);
    }

    private void fetchExtras() {
        map = (HashMap<String, String>) (getIntent().getSerializableExtra("Map"));
    }

    private void fillListView() {
        System.out.println(map);
        ArrayList<String> listItems = new ArrayList<>();

        for (String s : map.keySet()) {
            listItems.add(s);
        }

        listArr = new String[listItems.size()];
        for (int i = 0; i < listItems.size(); i++) {
            listArr[i] = listItems.get(i);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        if (mListView == null) {
            System.out.println("null");
        } else {
            mListView.setAdapter(adapter);
        }
    }

}
