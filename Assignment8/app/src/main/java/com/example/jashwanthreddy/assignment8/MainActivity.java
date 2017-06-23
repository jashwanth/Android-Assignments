package com.example.jashwanthreddy.assignment8;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
  //  Button btn1;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.MenuLoadMoviesFromUrl) {
            intent = new Intent(MainActivity.this, LoadMovies.class);
            startActivity(intent);
            /*FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction ();
            fragmentTransaction.replace(R.id.mainframelayout, AboutMeFragment.newInstance(R.id.AboutMe));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();*/
        }
        /*if (id == R.id.task1)
        {
            intent = new Intent (MainActivity.this, Task1.class);
            startActivity(intent);
        }*/

        return super.onOptionsItemSelected(item);
    }

}
