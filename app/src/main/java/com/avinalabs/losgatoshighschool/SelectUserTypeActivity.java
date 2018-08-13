//
// SelectUserTypeActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// SelectUserTypeActivity - screen that allows user to select type
//

package com.avinalabs.losgatoshighschool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SelectUserTypeActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1234;
    private Button mStudentButton;
    private Button mParentButton;
    private Button mStaffButton;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();


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

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("443347583933-jl8au18miiva6i6c9ubflrpr6kqdujpr.apps.googleusercontent.com")
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
/*.enableAutoManage(this FragmentActivity , this  OnConnectionFailedListener )*/
    }

    private void signIn() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                System.out.println("failure 2");
                System.out.println(result.getStatus());
                Toast.makeText(getApplicationContext(), "An error occurred while attempting a login. Please contact app.lghs@gmail.com for help.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println(user.getEmail());
                            proceedWithLogin(user.getEmail());
                        } else {
                            if (task.getException() != null) {
                                System.out.println(task.getException().getLocalizedMessage());
                            }
                            System.out.println("failure 1");
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "An error occurred while attempting a login. Please contact app.lghs@gmail.com for help.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void proceedWithLogin(final String email) {
        //StudentList list = new StudentList(getApplicationContext());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("firebase_v1/appData/studentRoster");

         Query qObj = myRef.orderByChild("email");
System.out.println("orderbychild"+qObj.getRef().getKey());
         qObj.equalTo(email).addListenerForSingleValueEvent(
                 new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         if (dataSnapshot.exists())
                         {
                             System.out.println("Data exists");
                             System.out.println(dataSnapshot.getValue().toString());
                             for (DataSnapshot ds : dataSnapshot.getChildren())
                             {
                                 System.out.println("DS:"+ds.getValue().toString());
                                 Student std = new Student(ds.child("firstName").getValue().toString(),
                                         ds.child("lastName").getValue().toString(),
                                         ds.child("gradYear").getValue().toString(),
                                         ds.getKey(),
                                         ds.child("email").getValue().toString());
                                 SharedPreferences prefs = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
                                 UserUtils.setStudent(prefs, std);
                                 studentLoginSuccess(std.getId());

                             }

                         }
                         else
                         {
                             System.out.println("Failed to fetch");
                             Toast.makeText(getApplicationContext(), "We couldn't find your student information. Please contact app.lghs@gmail.com for help.",
                                     Toast.LENGTH_LONG).show();
                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                     }
                 }
         );

    }

    private void studentLoginSuccess(String id) {
        Intent intent = new Intent(SelectUserTypeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // pre: none
    // post: goes to student login screen
    private void goToStudent() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
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
