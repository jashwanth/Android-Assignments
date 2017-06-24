package com.example.mina.twelfth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.HashMap;

public class MovieDetailActivity extends AppCompatActivity{

    HashMap<String,?> movieEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        movieEntry=(HashMap)bundle.getSerializable("MOVIE");


        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle((String)movieEntry.get("name"));

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MovieDetailsFragment.newInstance(movieEntry)).commit();
        }
    }

}
