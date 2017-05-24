package com.sjf.lgcats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HotlinesDetailActivity extends AppCompatActivity {

    private Hotline hotline;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        setTitle();
        setTextView();
    }

    private void setTitle() {
        String title = this.getIntent().getExtras().getString("title");
        setTitle(title);

    }

    private void setTextView() {
        String content = this.getIntent().getExtras().getString("content");
        mTextView = (TextView) findViewById(R.id.HOTLINES_TEXT_VIEW);
        mTextView.setText(content);
        mTextView.setTextSize(14);
    }

    private void setupView() {
        setContentView(R.layout.activity_hotlines_detail);
    }
}
