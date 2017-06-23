package com.example.jashwanthreddy.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
//    Button mybutton;
    String msg = "Activity life cycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        mybutton = (Button) findViewById(R.id.button);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mybutton.setOnClickListener(this);
    }

    @Override
    protected void onStart () {
        super.onStart () ;
        Log.d( msg, "onStart() event");
    }

    @Override
    protected void onResume () {
        super.onResume() ;
        Log.d( msg, "onResume() event");
    }

    @Override
    protected void onPause () {
        super.onPause() ;
        Log.d( msg, "onPause() event");
    }

    @Override
    protected void onStop () {
        super.onStop();
        Log.d( msg, "onStop() event");
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        Log.d( msg, "onDestroy() event");
    }

  //  @Override
  /*  public void onClick(View v) {
        if (v.getId() == R.id.button)
        {
            Toast.makeText(this, "Donot click", Toast.LENGTH_LONG).show();
        }

    } */
}
