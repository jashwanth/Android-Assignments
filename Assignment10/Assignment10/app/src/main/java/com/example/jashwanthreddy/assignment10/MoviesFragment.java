package com.example.jashwanthreddy.assignment10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;


public class MoviesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_MOVIE = "Movie";
    public static final String TAG = "Debug";
    public static HashMap movieData = new HashMap();
    private HashMap<String, ?> movie;
    ShareActionProvider mshareActionProvider;
    // TODO: Rename and change types of parameters
    private HashMap<String, ?> mParam1;
    public MoviesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MoviesFragment newInstance(HashMap<String, ?> movie) {
        Log.d(TAG, "newInstance: New instnace of detailed fragment is created");
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Start of on Create method");
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            movie = (HashMap<String, ?>) getArguments().getSerializable(ARG_MOVIE);
        }
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: End of on Create method");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreate: Start of onCreateView method");
        View itemView = inflater.inflate(R.layout.fragment_movies_copy, container, false);
        if (getArguments() != null) {
            Log.d(TAG, "onCreateView: We have some prev data");
            movieData = (HashMap) getArguments().getSerializable(ARG_MOVIE);
        }
        // layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        // View itemView = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView4);

        // textView = (TextView)itemView.findViewById(R.id.name);
        TextView textView = (TextView) itemView.findViewById(R.id.name1);
        TextView textView2 = (TextView) itemView.findViewById(R.id.description);
        TextView textView3 = (TextView) itemView.findViewById(R.id.year2);
        TextView textView4 = (TextView) itemView.findViewById(R.id.length2);
        RatingBar RatingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        //TextView textView5 = (TextView)itemView.findViewById(R.id.rating);
        TextView textView6 = (TextView) itemView.findViewById(R.id.idirector);
        TextView textView7 = (TextView) itemView.findViewById(R.id.stars);
        imageView.setImageResource((Integer) movieData.get("image"));

        //imageView.setTransitionName((String) movieData.get("name"));
        Log.d(TAG, "onCreateView: The Transition name has been set"+(String) movieData.get("name"));
        textView.setText((String) movieData.get("name"));
        textView2.setText((String) movieData.get("description"));
        textView3.setText((String) movieData.get("year"));
        textView4.setText("Length: " + (String) movieData.get("length"));
        //Float rate = Float.valueOf((Float)movieData.get("rating"));
        // textView5.setText("Rating: "+movieData.get("rating"));
        RatingBar.setNumStars(5);

        Double rating = (Double) movieData.get("rating");
        // Log.d(TAG, "onCreateView: RATING IS"+ rating);
        RatingBar.setRating(rating.floatValue() / 2);
        // RatingBar.setRating((Float) movieData.get("rating"));
        textView6.setText("Director: " + movieData.get("director"));
        textView7.setText("Cast: " + movieData.get("stars"));


        return itemView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menu.findItem(R.id.movie_fragment_menu) == null) {
            inflater.inflate(R.menu.movie_fragment_toolbar, menu);
        }
        MenuItem menuItem1 = menu.findItem(R.id.movie_fragment_toolbar_imageview);
        MenuItem menuItem2 = menu.findItem(R.id.movie_fragment_toolbar_title);
        MenuItem menuItem3 = menu.findItem(R.id.movie_fragment_action_provider);
        if (menuItem1 != null) {
            Integer k = (Integer) movie.get("image");
            menuItem1.setIcon(k);
        }
        if (menuItem2 != null) {
            menuItem2.setTitle(movie.get("name").toString());
        }
        if (menuItem3 != null) {
            mshareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem3);
            Intent intentShare = new Intent(Intent.ACTION_SEND);
            intentShare.setType("text/plain");
            intentShare.putExtra(Intent.EXTRA_TEXT, (String) movie.get("name"));
            mshareActionProvider.setShareIntent(intentShare);
            // startActivity(intentShare);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

}
