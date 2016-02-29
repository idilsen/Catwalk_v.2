package com.parse.starter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseUser;

import java.text.ParseException;

public class LoginActivity extends AppCompatActivity {
    protected Button mloginButton;
    protected Button mCreateButton;
    protected EditText loginUsername;
    protected EditText loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mloginButton = (Button)findViewById(R.id.loginButton);
        mCreateButton = (Button)findViewById(R.id.createButton);
        loginUsername = (EditText)findViewById(R.id.usernameLoginEditText);
        loginPassword = (EditText)findViewById(R.id.passwordLoginEditText);

        //Listener
        mloginButton.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {

            //get the user input from the edit text and convert it to string
            String username = loginUsername.getText().toString().trim();
            String password = loginPassword.getText().toString().trim();
            ParseUser.logInInBackground(username, password , new LogInCallback() {
                @Override
                public void done(ParseUser user, com.parse.ParseException e) {
                    if(e == null){
                    //successfully logged in
                        Toast.makeText(LoginActivity.this, "Welcome back!", Toast.LENGTH_LONG).show();
                        //take user to homepage
                        Intent takehome = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(takehome);

                    }else{
                    //error
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage(e.getMessage());
                        builder.setTitle("Sorryyyyyyy:(");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    }
            });
        }
        });

            //Listener
            mCreateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(register);
                }
            });
        }

    }
