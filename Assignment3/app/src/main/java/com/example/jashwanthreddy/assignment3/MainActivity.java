package com.example.jashwanthreddy.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements  Fragment1.CustomOnClickListener {
    Intent intent2;
    Intent intent3;
    public void onClicked (View v)
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment1 f1 = (Fragment1)fm.findFragmentById (R.id.layout1);
        switch (v.getId()) {
            case R.id.button:
                FragmentTransaction fT = fm.beginTransaction();
                fT.replace(R.id.frontpagefragment, AboutMeFragment.newInstance(R.id.aboutmelayout));
                fT.addToBackStack(null);
                fT.commit();
                break;
           /*  this code was written to test the moviefragment loading happens properly
               case R.id.button2:
                FragmentTransaction fT2 = fm.beginTransaction();
                fT2.replace(R.id.frontpagefragment, MovieFragment.newInstance(R.id.moviefragment));
                fT2.addToBackStack(null);
                fT2.commit();
                break;*/
            case R.id.button2:
                intent2 = new Intent (MainActivity.this, ViewPagerActivity.class);
                startActivity(intent2);
                break;
            case R.id.button3:
                intent3 = new Intent(MainActivity.this, MasterDetailActivity.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction ();
        fragmentTransaction.replace(R.id.frontpagefragment, Fragment1.newInstance(R.id.layout1));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
        if (id == R.id.AboutMe) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction ();
            fragmentTransaction.replace(R.id.frontpagefragment, AboutMeFragment.newInstance(R.id.aboutmelayout));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if (id == R.id.task2)
        {
            intent2 = new Intent (MainActivity.this, ViewPagerActivity.class);
            startActivity(intent2);
        }
        if (id == R.id.task3)
        {
            intent3 = new Intent(MainActivity.this, MasterDetailActivity.class);
            startActivity(intent3);
        }

        return super.onOptionsItemSelected(item);
    }
}
