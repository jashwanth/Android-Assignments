package com.example.mina.twelfth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_layout1) {
            Intent intent = new Intent(this, CoordinatorOneActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_layout2) {
            Intent intent = new Intent(this, CoordinatorTwoActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_layout3) {
            Intent intent = new Intent(this, ConstraintActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_layout4) {
            Intent intent = new Intent(this, FlexboxOneActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_layout5) {
            Intent intent = new Intent(this, FlexboxTwoActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_layout6) {
            Intent intent = new Intent(this, FlexboxThreeActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
