package com.example.jashwanthreddy.assignment8;

/**
 * Created by jashwanthreddy on 2/9/17.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
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
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class MovieFragment extends Fragment {
    private class MyDownLoadImageAsyncTask extends AsyncTask<String, Void, Bitmap>
    {
        private final WeakReference<ImageView> imageViewWeakReference;
        public MyDownLoadImageAsyncTask(ImageView imageView)
        {
            imageViewWeakReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String...urls)
        {
            Bitmap bitmap = null;
            for(String url : urls)
            {
                Log.d("Jashwanth URL is : ", url);
                bitmap = MyUtility.downloadImageusingHTTPGetRequest(url);
               /* if (bitmap != null)
                {
                    mImageMemoryCache.put(url, bitmap);
                }*/
            }
            return bitmap;
        }

        /* Set the bitmap returned from doInbackground */
        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            if ((imageViewWeakReference != null) && (bitmap != null))
            {
                final ImageView imageView = imageViewWeakReference.get();
                if (imageView != null)
                {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
    private static final String ARG_MOVIE = "Movie";
    private HashMap<String, ?> movie;
    android.support.v7.widget.ShareActionProvider mshareActionProvider;
    private int total = 0;
    TextView Title;
    TextView Description;
    TextView StarInfo;
    ImageView image;
    TextView YearInfo;
    RatingBar rating;
    public static MovieFragment newInstance(HashMap<String, ?> movie)
    {
        Log.d("TAG", "newInstance: Start");
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        Log.d("TAG", "newInstance: End");
        return fragment;
    }
    public MovieFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Log.d("TAG", "onCreate: Start");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null)
        {
            movie = (HashMap<String, ?> )getArguments().getSerializable(ARG_MOVIE);
        }
        if (movie != null) {
            Log.d("TAG", "onCreate: " + movie.size());
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.moviefragment, container, false);
        Title = (TextView)rootView.findViewById(R.id.movietitle);
        Description = (TextView)rootView.findViewById(R.id.moviedescription);
        image = (ImageView)rootView.findViewById(R.id.movieimageView);
        YearInfo = (TextView)rootView.findViewById(R.id.movieyear);
        StarInfo = (TextView)rootView.findViewById(R.id.moviestar);
        rating = (RatingBar)rootView.findViewById(R.id.movieratingBar);
     //   rating.setMax(5);
        rating.setMax(5);
        rating.setStepSize(0.1f);
//        Log.d("Jashwanth", movie.get("description").toString());
        if (movie == null)return rootView;
        if(movie.get("name") != null){
        Log.d("Jashwanth", movie.get("name").toString());}
        else{
            Log.d("Jashwanth", "onCreateView: name is null");
        }

        if (movie.get("name") != null) {
            Title.setText(movie.get("name").toString());
        }
        if (movie.get("description") != null) {
            Description.setText(movie.get("description").toString());
        }
        //Integer imageId = movie.get("image");
      //  getResources().getDrawable((int)movie.get("image"), null);
       // Integer k = (Integer)movie.get("image");
      //  image.setImageDrawable(getResources().getDrawable(movie.get("image"), null));
      //  image.setImageResource(k);
        MyDownLoadImageAsyncTask myDownLoadImageAsyncTask = new
                           MyDownLoadImageAsyncTask(image);
        myDownLoadImageAsyncTask.execute((String)movie.get("url"));
        if (movie.get("year") != null) {
            Log.d("Final Jash Movie", movie.get("year").toString());
            YearInfo.setText("Year: " + movie.get("year").toString());
        }
        if (movie.get("stars") != null) {
         //   Log.d("Final Jash Movie", movie.get("stars").toString());
         //   Log.d("Final Jash Movie", String.valueOf(movie.get("stars").toString().length()));
            StarInfo.setText("Stars: " + (movie.get("stars").equals("null") ? "" : movie.get("stars").toString()));
        }
        if (movie.get("rating") != null) {
            Double frat = (Double) movie.get("rating");
            Log.d("Rating in DOuble: ", frat.toString());
            float ft = (float) (frat.floatValue()) / 2.0f;
            Log.d("Rating in float: ", Float.toString(ft));
            rating.setRating((float) ft);
        }
/*        String Display = "Movie Name : " + myhash.get("name").toString() + "\n";
           Display += "Description : " + myhash.get("description").toString() + "\n";
           Display += "Year : " + myhash.get("year").toString() + "\n";
           Display += "Length : " + myhash.get("length").toString() + "min \n";
           Display += "Rating : " + myhash.get("rating").toString() + "\n";
           Display += "Director : " + myhash.get("director").toString() + "\n";
           Display += "stars : " + myhash.get("stars").toString() + "\n";*/
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
      /*  if (menu.findItem(R.id.movie_fragment_menu) == null) {
            inflater.inflate(R.menu.movie_fragment_toolbar, menu);
        }
        MenuItem menuItem1 = menu.findItem(R.id.movie_fragment_toolbar_imageview);
        MenuItem menuItem2 = menu.findItem(R.id.movie_fragment_toolbar_title);
        MenuItem menuItem3 = menu.findItem(R.id.movie_fragment_action_provider);
        if (menuItem1 != null) {
            Integer k = (Integer)movie.get("image");
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
*/        super.onCreateOptionsMenu(menu, inflater);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
       /* if (id == R.id.movie_fragment_toolbar_title) {
            Toast.makeText(getContext(), "Inside movie fragment title click", Toast.LENGTH_LONG).show();
        }*/
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }
}
