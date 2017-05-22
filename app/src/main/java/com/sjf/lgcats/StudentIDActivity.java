package com.sjf.lgcats;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 *
 * Displays a barcode for the student's student ID.
 * barcode software font came from here:
 * https://www.barcodesinc.com/free-barcode-font/
 *
 * @author  Shomil Jain
 * @author  Quintin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
 */

public class StudentIDActivity extends AppCompatActivity {

    private TextView studentIDbarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_id);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        studentIDbarcode = (TextView) findViewById(R.id.StudentIDbarcode);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/fre3of9x.ttf");

        studentIDbarcode.setTypeface(custom_font);
        studentIDbarcode.setTextColor(getResources().getColor(R.color.black));
        studentIDbarcode.setText(getString(R.string.barcode_start) + "109236" + getString(R.string.barcode_end));

    }

}
