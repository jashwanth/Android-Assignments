package com.example.jashwanthreddy.assignment2;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import java.io.*;
import java.net.URL;
import java.net.*;
import java.util.*;
import android.view.MotionEvent;

import org.w3c.dom.Text;

public class Activity4 extends AppCompatActivity {
    private SeekBar _seekBar;
 //   private TextView _textView;
    private TextView movieInfo;
    MovieData myMovieDb;
    int movieIndex;
    float downX;
    float downY;
    ImageView i1;
    String imageUrl;
    RelativeLayout.LayoutParams params;
    int progress_diff = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                double deltaX = downX - event.getX();
                double deltaY = downY - event.getY();
                if (deltaX > 0)
                {
                    if (movieIndex < myMovieDb.getSize()-1)
                    {
                        movieIndex++;
                        displayMovieInfo();
                    }
                   /* right to left swipe */
                }
                else if(deltaX < 0)
                {
                    if (movieIndex > 0)
                    {
                        movieIndex--;
                        displayMovieInfo();
                    }
                    /* left to right swipe */
                }
/*              downX = event.getX();
                downY = event.getY();*/
         }
        return false;
    }
   /* public class LoadImageFromURL extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            try
            {
                URL url = new URL(imageUrl);
                InputStream is = url.openConnection().getInputStream();
                Bitmap bitMap = BitmapFactory.decodeStream(is);
                return bitMap;
            }
            catch(MalformedURLException e)
            {
                Log.d("MalformURL", "MalformURL error" + e.toString());
                e.printStackTrace();
            }
            catch(IOException e)
            {
                Log.d("IOException", "IOException error" + e.toString());
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            i1.setImageBitmap(result);
        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setTitle("");
        setContentView(R.layout.activity_4);
        imageUrl = "http://ia.media-imdb.com/images/M/MV5BMjE1ODcyODYxMl5BMl5BanBnXkFtZTcwNjA1NDE3MQ@@._V1_SY317_CR2,0,214,317_AL_.jpg";
        myseekbarfunc();
    }
    public void scaleImage(ImageView i1, int diff)
    {
       /* int imgWidth = i1.getWidth();
        int imgHeight = i1.getHeight();
        imgWidth  +=  (progress_diff * 1) ;
        imgHeight += (progress_diff * 1) ;
*/      android.view.ViewGroup.LayoutParams params = i1.getLayoutParams();
        params.width += progress_diff;
        params.height += progress_diff;
        i1.setLayoutParams(params);
    }

    public void displayMovieInfo()
    {
        HashMap myhash = myMovieDb.getItem(movieIndex);
        String Display = "Movie Name : " + myhash.get("name").toString() + "\n";
        Display += "Description : " + myhash.get("description").toString() + "\n";
        Display += "Year : " + myhash.get("year").toString() + "\n";
        Display += "Length : " + myhash.get("length").toString() + "min \n";
        Display += "Rating : " + myhash.get("rating").toString() + "\n";
        Display += "Director : " + myhash.get("director").toString() + "\n";
        Display += "stars : " + myhash.get("stars").toString() + "\n";

        movieInfo.setText(Display);
        i1.setImageDrawable(getResources().getDrawable((int)myhash.get("image"),
                            getTheme()));
    }
    public void myseekbarfunc()
    {
        myMovieDb = new MovieData();
     //   moviesList = myMovieDb.getMoviesList();
     //   ListSize = myMovieDb.getSize();
        movieIndex = 0;
        _seekBar = (SeekBar)findViewById(R.id.seekBar3);
        _seekBar.setProgress(_seekBar.getMax()/2);
        movieInfo = (TextView)findViewById(R.id.textView4);
     //   _textView = (TextView)findViewById(R.id.textView2);
        i1 = (ImageView)findViewById(R.id.imageView2);
        displayMovieInfo();
    //    LoadImageFromURL loadImage = new LoadImageFromURL();
    //    loadImage.execute();
        i1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                _seekBar.setProgress(_seekBar.getMax()/2);
                //scaleImage(i1, progress_diff);
                return true;
            }
        });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Toast Message on Imageview ShortClick",
                        Toast.LENGTH_SHORT).show();
                Snackbar snack = Snackbar.make(v, "SnackBar Message on Imageview ShortClick",
                        Snackbar.LENGTH_SHORT);
                View snackview = (View)snack.getView();
                snackview.setBackgroundColor(getResources().getColor(R.color.darkgrey, getTheme()));
               // snack.setActionTextColor(Color.BLACK);
                TextView textView = (TextView) snackview.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);

                snack.show();
                /*Snackbar.make(v, "SnackBar Message on Imageview ShortClick",
                        Snackbar.LENGTH_SHORT).show();
*/            }
        });

    /*    i1.setOnTouchListener(new View.OnTouchListener()
        {
            float downX;
            float downY;
            @Override
            public boolean onTouch ( View v, MotionEvent event )
            {
                switch ( event . getAction ()) {
                    case MotionEvent.ACTION_DOWN :
                        downX = event.getX();
                        downY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE :
                        double deltaX = downX - event.getX();
                        double deltaY = downY - event.getY();
                        downX = event.getX();
                        downY = event.getY();
                        if (Math.abs(deltaX) > 100)
                        {
                            if (deltaX > 0)
                            {
                                if (movieIndex < myMovieDb.getSize()-1)
                                {
                                    movieIndex++;
                                    displayMovieInfo();
                                }
                                 *//* swipe from right to left *//*
                            }

                            else if (deltaX < 0)
                            {
                                if (movieIndex > 0)
                                {
                                    movieIndex--;
                                    displayMovieInfo();
                                }
                            }
                        }
                }
                return  true;
            }
        });*/


     //   _textView.setText("Covered :  " + _seekBar.getProgress() + "/" + _seekBar.getMax());
        _seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value ;
            int previous_value ;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_diff = progress - progress_value;
                previous_value = progress_value;
                progress_value = progress;
                scaleImage(i1, progress_diff);
       //         _textView.setText("Covered :  " + progress_value + "/" + _seekBar.getMax());
                 //Toast.makeText(Activity4.this, "Seekbar progress", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
             //   Toast.makeText(Activity4.this, "Seekbar on start tracking touch", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               //Toast.makeText(Activity4.this, "Seekbar on stop tracking touch", Toast.LENGTH_LONG).show();
         //       _textView.setText("Covered :  " + progress_value + "/" + _seekBar.getMax());
            }
        });
    }
}
