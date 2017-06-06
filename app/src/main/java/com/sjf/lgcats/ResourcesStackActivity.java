//
// ResourcesStackActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// ResourcesStackActivity - holds resources stack 1 deep
//

package com.sjf.lgcats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ResourcesStackActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<ResourceCellObject> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        fetchExtras();
        fillListView();
        setListListener();
    }

    private void setListListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent detailIntent = new Intent(getApplicationContext(), ResourcesListActivity.class);
                detailIntent.putExtra("Map", (list.get(position).getLinkContent()));
                startActivity(detailIntent);
            }
        });
    }

    private void fillListView() {
        String[] listArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listArr[i] = list.get(i).getLabel();
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listArr);
        if (mListView == null) {
            System.out.println("null");
        } else {
            mListView.setAdapter(adapter);
        }
    }

    private void fetchExtras() {
        list = (ArrayList<ResourceCellObject>) (getIntent().getSerializableExtra("Stack"));
    }

    private void assignVariables() {
        mListView = (ListView) findViewById(R.id.resources_stack_activity_listview);
    }

    private void setupView() {
        setContentView(R.layout.activity_resources_stack);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Resources");
    }

}
