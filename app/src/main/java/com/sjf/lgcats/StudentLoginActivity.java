package com.sjf.lgcats;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class StudentLoginActivity extends AppCompatActivity {

    private EditText mIdEditText;
    private EditText mLastNameEditText;
    private Button mSigninButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        setListeners();
    }

    private void setupView() {
        setContentView(R.layout.activity_student_login);
        setTitle("Student Login");
    }

    private void setListeners() {
        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryLogin();
            }
        });
    }

    private void assignVariables() {
        mIdEditText = (EditText) findViewById(R.id.sso_id_field);
        if (mIdEditText.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        mLastNameEditText = (EditText) findViewById(R.id.sso_lastname_field);
        mSigninButton = (Button) findViewById(R.id.sso_signin_button);
    }

    private void tryLogin() {
        String id = mIdEditText.getText().toString();
        String last = mLastNameEditText.getText().toString();
        StudentList sList = new StudentList(getApplicationContext());
        if (sList.checkLogin(last, id)) {
            loginSuccess(id);
        } else {
            loginFail();
        }
    }

    private void loginSuccess(String id) {
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE).edit();
        UserUtils.setUserType(UserUtils.KEY_STUDENT, editor);
        UserUtils.setUsername(id, editor);
        Intent intent = new Intent(StudentLoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void loginFail() {
        AlertDialog alertDialog = new AlertDialog.Builder(StudentLoginActivity.this).create();
        alertDialog.setTitle("Invalid Login");
        alertDialog.setMessage("Please check your Student ID & last name. If you're having trouble, please contact app.lghs@gmail.com.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
