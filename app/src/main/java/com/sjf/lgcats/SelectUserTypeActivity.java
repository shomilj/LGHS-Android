//
// SelectUserTypeActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// SelectUserTypeActivity - screen that allows user to select type
//

package com.sjf.lgcats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SelectUserTypeActivity extends AppCompatActivity {

    private Button mStudentButton;
    private Button mParentButton;
    private Button mStaffButton;

    // pre: none
    // post: sets up the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // generic view setup
        setContentView(R.layout.activity_select_user_type);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Welcome to LG CATS!");

        // assign variables
        mStudentButton = (Button) findViewById(R.id.user_type_student_button);
        mParentButton = (Button) findViewById(R.id.user_type_parent_button);
        mStaffButton = (Button) findViewById(R.id.user_type_staff_button);

        // set listeners
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

    // pre: none
    // post: goes to student login screen
    private void goToStudent() {
        Intent intent = new Intent(SelectUserTypeActivity.this, StudentLoginActivity.class);
        startActivity(intent);
    }

    // pre: none
    // post: goes to homepage
    private void goToStaff() {
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE).edit();
        UserUtils.setUserType(UserUtils.KEY_TEACHER, editor);
        goToHomeScreen();
    }

    // pre: none
    // post: goes to homepage
    private void goToParent() {
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE).edit();
        UserUtils.setUserType(UserUtils.KEY_PARENT, editor);
        goToHomeScreen();
    }

    // pre: none
    // post: goes to home screen
    private void goToHomeScreen() {
        Intent intent = new Intent(SelectUserTypeActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
