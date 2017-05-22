package com.sjf.lgcats;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AnnouncementDetailActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String date = this.getIntent().getExtras().getString("date");
        String content = this.getIntent().getExtras().getString("content");

        setTitle(date);

        mTextView = (TextView) findViewById(R.id.ANNOUNCEMENT_TEXT_VIEW);
        mTextView.setText(content);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
