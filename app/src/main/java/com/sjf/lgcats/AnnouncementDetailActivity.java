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
        mTextView = (TextView) findViewById(R.id.ANNOUNCEMENT_TEXT_VIEW);
        mTextView.setText(content);
        mTextView.setTextSize(14);
    }

    private void setupView() {
        setContentView(R.layout.activity_announcement_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
