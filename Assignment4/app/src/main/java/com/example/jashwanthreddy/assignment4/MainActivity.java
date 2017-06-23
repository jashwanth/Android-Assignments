package com.example.jashwanthreddy.assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements FrontPageFragment.CustomOnClickListener{
    Intent intent;
    public void onClicked(View v)
    {
        FragmentManager fm = getSupportFragmentManager();
        FrontPageFragment fpf = (FrontPageFragment)fm.findFragmentById(R.id.frontpagefragment);
        switch (v.getId()) {
            case R.id.button:
                Log.d("AboutMe", "replace mainframelayout with task1");
                FragmentTransaction fT = fm.beginTransaction();
                fT.replace(R.id.mainframelayout, AboutMeFragment.newInstance(R.id.AboutMe));
                fT.addToBackStack(null);
                fT.commit();
                break;
            case R.id.button2:
                Log.d("Button task1", "Clicked the button task1");
                intent = new Intent(MainActivity.this, Task1.class);
                startActivity(intent);
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
        fragmentTransaction.replace(R.id.mainframelayout,
                FrontPageFragment.newInstance(R.id.frontpagefragment));
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
            fragmentTransaction.replace(R.id.mainframelayout, AboutMeFragment.newInstance(R.id.AboutMe));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if (id == R.id.task1)
        {
            intent = new Intent (MainActivity.this, Task1.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
