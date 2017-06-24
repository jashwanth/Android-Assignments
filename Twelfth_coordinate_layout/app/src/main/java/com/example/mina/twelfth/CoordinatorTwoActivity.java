package com.example.mina.twelfth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class CoordinatorTwoActivity extends AppCompatActivity implements RecyclerViewFragment.OnListItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_two);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RecyclerViewFragment.newInstance(0)).commit();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("CoordinatorLayout Two");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.container), "Welcome to Android Programming!", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onListItemSelected(int position, HashMap<String, ?> movie) {
        Intent myIntent = new Intent(CoordinatorTwoActivity.this, MovieDetailActivity.class);
        myIntent.putExtra("MOVIE",movie);
        startActivity(myIntent);
    }
}
