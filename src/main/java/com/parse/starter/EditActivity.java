package com.parse.starter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class EditActivity extends AppCompatActivity {

    protected EditText mConfirmed;
    protected Button mUpdate;
    protected  EditText mNewPassword;
    protected  EditText mNewEmail;
    protected Button mUpdateEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize
        mNewPassword = (EditText)findViewById(R.id.newPassword);
        mConfirmed = (EditText)findViewById(R.id.confirmPassword);
        mNewEmail = (EditText)findViewById(R.id.newEmail);

        mUpdateEmail = (Button)findViewById(R.id.updateEmail);
        mUpdate = (Button)findViewById(R.id.updateButton);

        //Update Email Process
        mUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", currentUser);
                query.findInBackground(new FindCallback<ParseUser>() {
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e == null) {
                            ParseUser currentUser = ParseUser.getCurrentUser();
                            String newEmail = mNewEmail.getText().toString().trim();
                            currentUser.setEmail(newEmail);
                            currentUser.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        //successfully updated the password
                                        Toast.makeText(EditActivity.this, "You have successfully changed your email address!", Toast.LENGTH_LONG).show();
                                        //take user to homepage
                                        Intent discover = new Intent(EditActivity.this, DiscoverActivity.class);
                                        startActivity(discover);

                                    } else {
                                        //there was a problem. advice user.
                                        AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                                        builder.setMessage(e.getMessage());
                                        builder.setTitle("Oops!");
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
                        } else {
                            // Something went wrong.
                            //there was a problem. advice user.
                            AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                            builder.setMessage(e.getMessage());
                            builder.setTitle("Oops!");
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

        //Update Password Process
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpass = mNewPassword.getText().toString().trim();
                String confirmed = mConfirmed.getText().toString().trim();

                if(newpass.equals(confirmed)) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", currentUser);
                query.findInBackground(new FindCallback<ParseUser>() {
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e == null) {
                            ParseUser currentUser = ParseUser.getCurrentUser();
                            String newPass = mNewPassword.getText().toString();
                            currentUser.setPassword(newPass);
                            currentUser.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    //successfully updated the password
                                    Toast.makeText(EditActivity.this, "You have successfully changed your password!", Toast.LENGTH_LONG).show();
                                    //take user to homepage
                                    Intent discover = new Intent(EditActivity.this, DiscoverActivity.class);
                                    startActivity(discover);

                                } else {
                                    //there was a problem. advice user.
                                    AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                                    builder.setMessage(e.getMessage());
                                    builder.setTitle("Oops!");
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
                    } else {
                        // Something went wrong.
                        //there was a problem. advice user.
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                        builder.setMessage(e.getMessage());
                        builder.setTitle("Oops!");
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
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
            builder.setMessage("You did not enter the same password!!");
            builder.setTitle("Oops!");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){

            case  R.id.logout:
                //logout the user
                ParseUser.logOut();
                Intent logout = new Intent(EditActivity.this, LoginActivity.class);
                EditActivity.this.startActivity(logout);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
