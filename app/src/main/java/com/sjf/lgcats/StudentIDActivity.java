package com.sjf.lgcats;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private TextView mNameTextView;
    private TextView mIDTextView;
    private TextView mGradeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        displayStudentID();
    }

    private void displayStudentID() {
        StudentList list = new StudentList(getApplicationContext());
        SharedPreferences prefs = getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE);
        Student student = list.getCurrentStudent(prefs);
        if (student == null) {
            // return to previous screen
            finish();
            onBackPressed();
        } else {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/fre3of9x.ttf");
            studentIDbarcode.setTypeface(custom_font);
            studentIDbarcode.setTextColor(getResources().getColor(R.color.black));
            studentIDbarcode.setText("P " + student.getId());
            mNameTextView.setText(student.getFull());
            mIDTextView.setText(student.getId());
            mGradeTextView.setText(student.getGrade());
        }
    }

    private void setupView() {
        setContentView(R.layout.activity_student_id);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void assignVariables() {
        studentIDbarcode = (TextView) findViewById(R.id.StudentIDbarcode);
        mNameTextView = (TextView) findViewById(R.id.student_id_name_textview);
        mGradeTextView = (TextView) findViewById(R.id.student_id_grade_textview);
        mIDTextView = (TextView) findViewById(R.id.student_id_idtextview);
    }


}
