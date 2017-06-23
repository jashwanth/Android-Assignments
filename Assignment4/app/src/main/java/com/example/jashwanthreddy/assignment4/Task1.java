package com.example.jashwanthreddy.assignment4;

import android.graphics.Movie;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.app.ListActivity;

import java.util.HashMap;

/* oncreateview(LayoutInflater inflater, ViewGroup container) {
*   View rootview = inflater.inflate(R.layout.fragment_Listview, container, false);
*   Listview listview = (Listview) rootview.findviewbyId(R.id.Listview);
*   MovieData moviedata = new MovieData();
*   MyBaseAdapter myBaseAdapter = new MyBaseAdapter(getActivity(), movieData.getMoviesList());
*   listview.setAdapter(myBaseAdapter);
*
* }*/
public class Task1 extends AppCompatActivity implements myRecycleView.CustomOnClickRecycleViewListener{
    //ListView listView;
    Fragment mcontent;

    public void onRecycleViewItemClicked (View v , HashMap<String,?> movie)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fT = fm.beginTransaction();
        fT.replace(R.id.task1framelayout, MovieFragment.newInstance(movie));
        fT.addToBackStack(null);
        fT.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "Inside task1 onCreate function");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);
        Log.d("onCreate", "Inside task1 set content view");
        if (savedInstanceState == null)
        {
            mcontent = myRecycleView.newInstance(R.id.recyclerelativelayout);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.task1framelayout,
                mcontent).addToBackStack(null).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mcontent", mcontent);
    }
}
