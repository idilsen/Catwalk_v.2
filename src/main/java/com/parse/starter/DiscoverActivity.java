package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.ParseUser;

public class DiscoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // show user's discover page

        } else {
            // show the signup or login screen
            //Intent login = new Intent(MainActivity.this, LoginActivity.class);
            //startActivity(login);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.editProfile) {
            return true;
        }*/

        switch(id){
            case R.id.editProfile:
                //take user to edit profile
                Intent edit = new Intent(DiscoverActivity.this, EditActivity.class);
                DiscoverActivity.this.startActivity(edit);
                break;

            case  R.id.logout:
                //logout the user
                ParseUser.logOut();
                Intent logout = new Intent(DiscoverActivity.this, LoginActivity.class);
                DiscoverActivity.this.startActivity(logout);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
