package com.sjf.lgcats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SelectUserTypeActivity extends AppCompatActivity {

    private Button mStudentButton;
    private Button mParentButton;
    private Button mStaffButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Welcome to LG CATS!");
        mStudentButton = (Button) findViewById(R.id.user_type_student_button);
        mParentButton = (Button) findViewById(R.id.user_type_parent_button);
        mStaffButton = (Button) findViewById(R.id.user_type_staff_button);

        mStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToStudent();
            }
        });

        mStaffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToStaff();
            }
        });

        mParentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToParent();
            }
        });
    }

    private void goToStudent() {
        Intent intent = new Intent(SelectUserTypeActivity.this, StudentLoginActivity.class);
        startActivity(intent);
    }

    private void goToStaff() {
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE).edit();
        UserUtils.setUserType(UserUtils.KEY_TEACHER, editor);
        goToHomeScreen();
    }

    private void goToParent() {
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE).edit();
        UserUtils.setUserType(UserUtils.KEY_PARENT, editor);
        goToHomeScreen();
    }

    private void goToHomeScreen() {
        Intent intent = new Intent(SelectUserTypeActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
