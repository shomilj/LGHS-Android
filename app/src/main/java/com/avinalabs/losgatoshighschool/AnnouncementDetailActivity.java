//
// AnnouncementDetailActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// AnnouncementDetailActivity - holds a screen that contains announcement content
//

package com.avinalabs.losgatoshighschool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class AnnouncementDetailActivity extends AppCompatActivity {

    // this text view holds the content of the announcement
    private TextView mTextView;

    // called when the view is created
    // pre: none
    // post: configures the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        setTitle();
        setTextView();
    }

    // pre: none
    // post: initializes the private variables
    private void assignVariables() {
        mTextView = (TextView) findViewById(R.id.announcements_detail_text_view);
    }

    // pre: none
    // post: sets the navigation bar title to the announcement date
    private void setTitle() {
        String date = this.getIntent().getExtras().getString("date");
        setTitle(date);
    }

    // pre: none
    // post: sets the content of the text view
    private void setTextView() {
        String content = this.getIntent().getExtras().getString("content");
        mTextView.setText(Html.fromHtml(content));
        mTextView.setTextSize(18);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
    }

    // pre: none
    // post: sets up the view
    private void setupView() {

        // links this java class to the layout XML file
        setContentView(R.layout.activity_announcement_detail);

        // sets up the back button
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
