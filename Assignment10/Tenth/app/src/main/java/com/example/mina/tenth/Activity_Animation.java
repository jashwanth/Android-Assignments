package com.example.mina.tenth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.support.v4.app.Fragment;


public class Activity_Animation extends AppCompatActivity {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportFragmentManager().beginTransaction()
                //.setCustomAnimations(R.anim.slide_in_from_left, 0)
                .replace(R.id.container, Fragment_Animation.newInstance(0))
                .commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment_Animation fragment = Fragment_Animation.newInstance(0);
        switch (id){
            case R.id.action_from_left:
                fragment.setEnterTransition(new Slide(Gravity.LEFT));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
                break;
            case R.id.action_in_and_out:
                fragment.setEnterTransition(new Slide(Gravity.RIGHT));
                fragment.setExitTransition(new Slide(Gravity.BOTTOM));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
                break;
            case R.id.action_combined:
                fragment.setEnterTransition(new Slide(Gravity.BOTTOM));
                fragment.setExitTransition(new Slide(Gravity.LEFT));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
                break;
            case R.id.action_others:
            default:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
                break;

        }
        return true;

    }

}
