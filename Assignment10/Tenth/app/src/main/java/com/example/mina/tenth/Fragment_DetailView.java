package com.example.mina.tenth;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;

public class Fragment_DetailView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MOVIE = "movie";
    private HashMap<String, ?> movie;

    public static Fragment_DetailView newInstance(HashMap<String, ?> movie) {
        Fragment_DetailView fragment = new Fragment_DetailView();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment_DetailView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie = (HashMap<String, ?>) getArguments().getSerializable(ARG_MOVIE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detailview, container, false);


        final TextView name = (TextView) rootView.findViewById(R.id.name);
        final TextView year = (TextView) rootView.findViewById(R.id.year);
        final TextView rating = (TextView) rootView.findViewById(R.id.rating);
        final TextView length = (TextView) rootView.findViewById(R.id.length);
        final TextView director = (TextView) rootView.findViewById(R.id.director);
        final TextView cast = (TextView) rootView.findViewById(R.id.stars);
        final ImageView image = (ImageView) rootView.findViewById(R.id.image);
        final TextView description = (TextView) rootView.findViewById(R.id.description);


        name.setText((String) movie.get("name"));
        description.setText((String) movie.get("description"));
        image.setImageResource((Integer) movie.get("image"));
        image.setTransitionName((String) movie.get("name")); // for animation
        year.setText("("+(String) movie.get("year")+")");
        rating.setText(movie.get("rating").toString());
        length .setText((String) movie.get("length"));
        director.setText((String) movie.get("director"));
        cast.setText((String) movie.get("stars"));

        final RatingBar ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);
        ratingBar.setMax(5);
        float rating1 = Float.parseFloat(movie.get("rating").toString())/2;
        ratingBar.setStepSize(0.05f);
        ratingBar.setRating(rating1);


        return rootView;

    }

}
