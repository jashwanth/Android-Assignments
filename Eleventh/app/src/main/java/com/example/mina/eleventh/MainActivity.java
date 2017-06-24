package com.example.mina.eleventh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

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
        else if(id == R.id.action_map1) {
            Intent intent = new Intent(this, SimpleMapActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_map2) {
            Intent intent = new Intent(this, MapTwoActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_map3) {
            Intent intent = new Intent(this, MapThreeActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_you1) {
            Intent intent = new Intent(this, Youtube1Activity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_you2) {
            Intent intent = new Intent(this, YouTubeActivity.class);
            intent.putExtra(YouTubeActivity.VIDEO_ID, "a0CDJZu4M5I");
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_you3) {
            //Opens in the StandAlonePlayer, defaults to fullscreen
            if (YouTubeIntents.canResolvePlayVideoIntent(this)) {
                //Opens in the StandAlonePlayer, defaults to fullscreen
                startActivity(YouTubeStandalonePlayer.createVideoIntent(this,
                        getString(R.string.google_maps_key), "a0CDJZu4M5I", 50000, true, true));
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
