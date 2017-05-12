package com.sjf.lgcats;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Immediately loads up the calendar on the LGHS website.
 *
 * @author  Shomil Jain
 * @author  Quentin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
 */

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.LGcalendar)));
        this.startActivity(intent);
    }

}
