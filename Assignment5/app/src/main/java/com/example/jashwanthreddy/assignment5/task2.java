package com.example.jashwanthreddy.assignment5;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.HashMap;

public class task2 extends AppCompatActivity implements myRecycleView.CustomOnClickRecycleViewListener{
    Fragment mcontent;

    public void onRecycleViewItemClicked (View v , HashMap<String,?> movie)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fT = fm.beginTransaction();
        fT.replace(R.id.task2framelayout, MovieFragment.newInstance(movie));
    //    fT.replace(R.id.recyclerelativelayout, MovieFragment.newInstance(movie));
        fT.addToBackStack(null);
        fT.commit();
        /*getSupportFragmentManager().beginTransaction()
                .replace(R.id.recyclerelativelayout, MovieFragment.newInstance(movie))
                .addToBackStack(null).commit();*/
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);*/
        Log.d("onCreate", "Inside task2 set content view");
        setTitle("");
        if (savedInstanceState == null)
        {
            mcontent = myRecycleView.newInstance(R.id.recyclerelativelayout);
        }
        ActionBar ab2 = getSupportActionBar();
        getSupportFragmentManager().beginTransaction().replace(R.id.task2framelayout,
                mcontent).addToBackStack(null).commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mcontent", mcontent);
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.recycleview_toolbar_title:
                return false;
        }
        return true;
    }


}
