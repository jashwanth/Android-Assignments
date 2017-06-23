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
import android.view.MenuItem;
import android.view.View;

import java.util.HashMap;

public class Task3 extends AppCompatActivity implements myRecycleView.CustomOnClickRecycleViewListener{
    Fragment mcontent;
    Toolbar toolbar;
    Toolbar toolbar4;
 /*   private CustomOnClickTask3SortByYearListener customOnClickTask3Listener1;

    public interface CustomOnClickTask3SortByYearListener {
        public void onCustomSortByYearClicked();
    }*/

    public void onRecycleViewItemClicked (View v , HashMap<String,?> movie)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fT = fm.beginTransaction();
        fT.replace(R.id.task3framelayout, MovieFragment.newInstance(movie));
        fT.addToBackStack(null);
        fT.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);
        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
      //  ab.setDisplayHomeAsUpEnabled(true);
        //ab.setLogo();

        if (savedInstanceState == null)
        {
            mcontent = myRecycleView.newInstance(R.id.recyclerelativelayout);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.task3framelayout,
                mcontent).addToBackStack(null).commit();

        toolbar4 = (Toolbar)findViewById(R.id.toolbar4);
        toolbar4.inflateMenu(R.menu.task3_bottom_toolbar_menu);
        setUpToolbarItemSelected();
        Log.d("onCreate", "Inside task3 set content view");
        setTitle("");
    //    customOnClickTask3Listener1 = (CustomOnClickTask3SortByYearListener)getApplicationContext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.task3_top_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
/*
        switch(id) {
            case R.id.recycleview_toolbar_title:
                return false;
        }
*/
        if (id == R.id.task3_top_toolbar_menu_unhide)
        {
            toolbar4.setVisibility(View.VISIBLE);
        }
        else if (id == R.id.task3_top_toolbar_sort_by_name)
        {
            myRecycleView mrv = (myRecycleView)mcontent;
            mrv.sortMovieDataByName();
        }
        return true;
    }


    void setUpToolbarItemSelected() {
       toolbar4.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(MenuItem item) {
               int id = item.getItemId();
               if (id == R.id.task3_bottom_toolbar_sort_by_year) {
                 /*customOnClickTask3Listener1.onCustomSortByYearClicked();*/
                 //  myRecycleView fragment = (myRecycleView)getSupportFragmentManager().findFragmentById(R.id.recyclerelativelayout);
                 //  fragment.sortMovieDataByYear();
                   myRecycleView mrv= (myRecycleView)mcontent;
                   mrv.sortMovieDataByYear();
               }
               return false;
           }

       });
        toolbar4.setNavigationIcon(R.drawable.task3_navigation_icon);
        toolbar4.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                toolbar4.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mcontent", mcontent);
    }
}
