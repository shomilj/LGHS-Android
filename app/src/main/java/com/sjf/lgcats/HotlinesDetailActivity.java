//
// HotlinesDetailActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// HotlinesDetailActivity - displays a single hotline's content
//

package com.sjf.lgcats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HotlinesDetailActivity extends AppCompatActivity {

    private Hotline hotline;
    private TextView mTextView;

    // called when the view is created
    // pre: none
    // post: configures the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        setTitle();
        setTextView();
    }

    // pre: none
    // post: sets the toolbar title
    private void setTitle() {
        String title = this.getIntent().getExtras().getString("title");
        setTitle(title);

    }

    // pre: none
    // post: sets the textivew to the hotline content
    private void setTextView() {
        String content = this.getIntent().getExtras().getString("content");
        mTextView = (TextView) findViewById(R.id.hotlines_text_view);
        mTextView.setText(content);
        mTextView.setTextSize(20);
    }

    // pre: none
    // post: sets up the view
    private void setupView() {
        setContentView(R.layout.activity_hotlines_detail);
    }
}
