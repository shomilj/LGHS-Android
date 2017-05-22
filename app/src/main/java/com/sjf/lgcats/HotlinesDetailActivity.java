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
        setContentView(R.layout.activity_hotlines_detail);

        String title = this.getIntent().getExtras().getString("title");
        String content = this.getIntent().getExtras().getString("content");

        setTitle(title);

        mTextView = (TextView) findViewById(R.id.HOTLINES_TEXT_VIEW);
        mTextView.setText(content);
        mTextView.setTextSize(14);
    }
}
