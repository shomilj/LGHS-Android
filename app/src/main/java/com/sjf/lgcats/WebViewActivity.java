//
// WebViewActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// WebViewActivity - opens links to the web
//

package com.sjf.lgcats;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent receivedIntent = getIntent();
        Intent sendIntent;
        int app = receivedIntent.getIntExtra("app", -1);
        String url = receivedIntent.getStringExtra("url");
        sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (app == R.string.twitter) {
            try {
                // get the Twitter app if possible
                this.getPackageManager().getPackageInfo("com.twitter.android", 0);
                sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.twitter_app_link)));
                sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } catch (Exception e) {
                // no Twitter app, revert to browser
            }
        }
        startActivity(sendIntent);
    }
}
