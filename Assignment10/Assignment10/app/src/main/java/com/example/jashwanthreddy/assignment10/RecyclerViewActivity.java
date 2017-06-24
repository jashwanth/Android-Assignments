package com.example.jashwanthreddy.assignment10;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerFragment.RecyclerOnClickListener {
    public static  final String TAG = "Debug";
    public MovieData movieData = new MovieData();
    Fragment myContent;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
           // toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
      // ActionBar actionBar = getSupportActionBar();
        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate: Old fragment");
            myContent = getSupportFragmentManager().getFragment(
                    savedInstanceState, "mContent"
            );

        }

        else {
            Log.d(TAG, "onCreate: new fragment");
            myContent = RecyclerFragment.newInstance();
        }


       // setTitle("");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recyclerviewactivity, myContent)
                .commit();
    }



    @Override
    public void onClicked ( View v ,int position) {
        MoviesFragment details = MoviesFragment.newInstance(movieData.getItem(position));
        details.setSharedElementEnterTransition(new DetailsTransition());
     //   details.setSharedElementEnterTransition(new android.transition.ChangeImageTransform());
     //   details.setEnterTransition(new android.transition.Explode());
        details.setEnterTransition(new android.transition.Fade());
        //details.set
        details.setExitTransition(new android.transition.Fade());
     //   details.setExitTransition(new android.transition.Explode());
        details.setSharedElementReturnTransition(new DetailsTransition());
     //   details.setSharedElementReturnTransition(new android.transition.ChangeImageTransform());
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.recyclerviewactivity, MoviesFragment.newInstance(movieData.getItem(position)))
//                .addToBackStack(null)
//                .commit();
        v.setTransitionName((String)movieData.getItem(position).get("name"));
      //  String transitionName = getResources().getString(R.string.transition_string);
        Log.d(TAG, "onClicked: The shared element name is"+v.getTransitionName());
        getSupportFragmentManager().beginTransaction()
                .addSharedElement(v, v.getTransitionName())
                .replace(R.id.recyclerviewactivity,details)
                .addToBackStack(null)
                .commit();
    }
}
