package com.example.jashwanthreddy.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class Activity1 extends AppCompatActivity {
    Intent intent1;
    Intent intent2;
    Intent intent3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.task2)
        {
            intent1 = new Intent (Activity1.this, Activity2.class);
            startActivity(intent1);
        }
        if (id == R.id.task3)
        {
            intent2 = new Intent (Activity1.this, Activity3.class);
            startActivity(intent2);
        }

        if (id == R.id.task4)
        {
            intent3 = new Intent(Activity1.this, Activity4.class);
            startActivity(intent3);
        }

        return super.onOptionsItemSelected(item);
    }
}
