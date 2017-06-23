package com.example.jashwanthreddy.assignment8;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

public class LoadMovies extends AppCompatActivity implements myRecycleView.CustomOnClickRecycleViewListener{
  //  Fragment mcontent;

    public void onRecycleViewItemClicked (HashMap<String,?> movie)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fT = fm.beginTransaction();
        fT.replace(R.id.activity_load_movies, MovieFragment.newInstance(movie));
        fT.addToBackStack(null);
        fT.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_movies);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);*/
        Log.d("onCreate", "Inside task2 set content view");
        setTitle("");
  /*      if (savedInstanceState == null)
        {
            mcontent = myRecycleView.newInstance(R.id.recyclerelativelayout);
        }*/
        ActionBar ab2 = getSupportActionBar();

        FragmentManager fmanager = getSupportFragmentManager();
        FragmentTransaction ft = fmanager.beginTransaction();
        ft.add(R.id.activity_load_movies,myRecycleView.newInstance(R.id.recyclerelativelayout));
        ft.addToBackStack(null);
        ft.commit();
/*        getSupportFragmentManager().beginTransaction().replace(R.id.activity_load_movies,
                mcontent).addToBackStack(null).commit();*/
    }

   /* @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mcontent != null) {
            getSupportFragmentManager().putFragment(outState, "mcontent", mcontent);
        }
    }*/

}
