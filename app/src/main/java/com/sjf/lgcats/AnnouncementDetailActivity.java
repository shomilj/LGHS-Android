//
// AnnouncementDetailActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// AnnouncementDetailActivity - this screen holds a text view that displays the content of the announcement only.
//

package com.sjf.lgcats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class AnnouncementDetailActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        setTitle();
        setTextView();
    }

    private void setTitle() {
        String date = this.getIntent().getExtras().getString("date");
        setTitle(date);
    }

    private void setTextView() {
        String content = this.getIntent().getExtras().getString("content");
        mTextView = (TextView) findViewById(R.id.announcements_detail_text_view);
        mTextView.setText(content);
        mTextView.setTextSize(18);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
    }

    private void setupView() {
        setContentView(R.layout.activity_announcement_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
