package com.example.mina.twelfth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;


public class MovieDetailsFragment extends Fragment {

    private static final String ARG_OPTION = "argument_option";
    ShareActionProvider shareActionProvider;
    String movieName;
    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    public static MovieDetailsFragment newInstance(HashMap<String, ?> movie) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_OPTION, movie);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_movie_detail, container, false);
        final TextView releaseYearTV  = (TextView)rootView.findViewById(R.id.rYearVal);
        final TextView movieDescription  = (TextView)rootView.findViewById(R.id.movieDescription);
        final ImageView moviePicIV  = (ImageView)rootView.findViewById(R.id.moviePic);
        final TextView actorView  = (TextView)rootView.findViewById(R.id.actors);
        final RatingBar ratingStars  = (RatingBar)rootView.findViewById(R.id.ratingBar);
        final TextView directors  = (TextView)rootView.findViewById(R.id.directors);
        final TextView runTIme  = (TextView)rootView.findViewById(R.id.runTime);

        HashMap map =   (HashMap)this.getArguments().getSerializable(ARG_OPTION);

        final HashMap movie = map;
        movieName = (String)movie.get("name");
        String year = (String)movie.get("year");
        String stars = (String)movie.get("stars");
        String description = (String)movie.get("description");
        releaseYearTV.setText(year);
        int id = (Integer)movie.get("image");
        moviePicIV.setImageResource(id);


        StringBuilder actors = new StringBuilder("");
        actors.append(stars);
        actorView.setText(actors.toString());
        movieDescription.setText(description);
        Double dRating = (Double)movie.get("rating");
        float fRating = dRating.floatValue()/2;
        ratingStars.setRating(fRating);
        String length = (String)movie.get("length");
        StringBuilder dir = new StringBuilder("");
        String director = (String)movie.get("director");
        dir.append(director);
        StringBuilder runTimeSb = new StringBuilder("");
        runTimeSb.append(length);
        directors.setText(dir.toString());
        runTIme.setText(runTimeSb.toString());

        ImageView backDrop = (ImageView) getActivity().findViewById(R.id.backdrop);
        backDrop.setImageResource(R.drawable.avatarland);

        FloatingActionButton fab = (FloatingActionButton)getActivity().findViewById(R.id.share);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody="ShareContentBody";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,"Subject:");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent,"Sharevia"));
            }
        });

        return rootView;

    }
}
