package com.example.jashwanthreddy.assignment3;

/**
 * Created by jashwanthreddy on 2/9/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.HashMap;

public class MovieFragment extends Fragment{
    private static final String ARG_MOVIE = "Movie";
    private HashMap<String, ?> movie;
    private int total = 0;
    TextView Title;
    TextView Description;
    TextView StarInfo;
    ImageView image;
    TextView YearInfo;
    RatingBar rating;
    public static MovieFragment newInstance(HashMap<String, ?> movie)
    {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }
    public MovieFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            movie = (HashMap<String, ?> )getArguments().getSerializable(ARG_MOVIE);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.moviefragment, container, false);
        Title = (TextView)rootView.findViewById(R.id.textView3);
        Description = (TextView)rootView.findViewById(R.id.textView4);
        image = (ImageView)rootView.findViewById(R.id.imageView2);
        YearInfo = (TextView)rootView.findViewById(R.id.textView5);
        StarInfo = (TextView)rootView.findViewById(R.id.textView6);
        rating = (RatingBar)rootView.findViewById(R.id.ratingBar);
     //   rating.setMax(5);
        rating.setMax(5);
        rating.setStepSize(0.1f);
        Title.setText(movie.get("name").toString());
        Description.setText(movie.get("description").toString());
        //Integer imageId = movie.get("image");
      //  getResources().getDrawable((int)movie.get("image"), null);
        Integer k = (Integer)movie.get("image");
      //  image.setImageDrawable(getResources().getDrawable(movie.get("image"), null));
        image.setImageResource(k);
        YearInfo.setText("Year: "+movie.get("year").toString());
        StarInfo.setText("Stars: "+movie.get("stars").toString());
        Double frat = (Double)movie.get("rating");
        Log.d("Rating in DOuble: ",frat.toString());
        float ft = (float) (frat.floatValue())/2.0f;
        Log.d("Rating in float: ", Float.toString(ft));
        rating.setRating((float)ft);
/*        String Display = "Movie Name : " + myhash.get("name").toString() + "\n";
           Display += "Description : " + myhash.get("description").toString() + "\n";
           Display += "Year : " + myhash.get("year").toString() + "\n";
           Display += "Length : " + myhash.get("length").toString() + "min \n";
           Display += "Rating : " + myhash.get("rating").toString() + "\n";
           Display += "Director : " + myhash.get("director").toString() + "\n";
           Display += "stars : " + myhash.get("stars").toString() + "\n";*/
        return rootView;
    }
}
