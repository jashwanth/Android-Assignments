package com.example.jashwanthreddy.assignment8;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
//import android.support.v4.widget.SearchViewCompatIcs;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.lang.ref.WeakReference;

import java.util.HashMap;

/*import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;*/

/**
 * Created by jashwanthreddy on 2/15/17.
 */

public class myRecycleView extends Fragment {
    RecyclerView mRecycleView;
    MyRecycleViewAdapter mRecycleViewAdapter;
    MovieData moviedata;
    MovieDataJson threadMovieData;
 //   LruCache<String, Bitmap> mImageMemoryCache;

    private class MyDownLoadJsonAsyncTask extends AsyncTask<String, Void, MovieDataJson> {
        private final WeakReference<MyRecycleViewAdapter> adapterWeakReference;
        public MyDownLoadJsonAsyncTask(MyRecycleViewAdapter myRecycleViewAdapter)
        {
            adapterWeakReference = new WeakReference<MyRecycleViewAdapter>(myRecycleViewAdapter);
        }

        @Override
        protected MovieDataJson doInBackground(String...urls) {
          //  MovieDataJson threadMovieData = new MovieDataJson();
            // MovieJsonData threadMovieData = new MovieJsonData();
            // Log.d("Jashwanth json url is: ", urls[0]);
            threadMovieData.moviesList.clear();
             for(String url : urls)
             {
                 threadMovieData.downloadMovieDataJson(url);
                 Log.d("Jashwanth URL from jash", url);
             }
            return threadMovieData;
        }

        @Override
        protected void onPostExecute(MovieDataJson threadMovieData) {
            /*if (moviedata.moviesList != null)*/
            moviedata.moviesList.clear();
            Log.d("Clear MovieSize: ", Integer.toString(moviedata.getSize()));
            for(int i = 0 ;i < threadMovieData.getSize() ; i++)
            {
              moviedata.addItem(i, threadMovieData.getItem(i));
            //  moviedata.moviesList.add(threadMovieData.moviesList.get(i));
            }
            Log.d("AddItem MovieSize: ", Integer.toString(moviedata.getSize()));
            if (adapterWeakReference != null)
            {
                final MyRecycleViewAdapter myRecycleViewAdapter = adapterWeakReference.get();
                if (myRecycleViewAdapter != null)
                    myRecycleViewAdapter.notifyDataSetChanged();
            }
            Log.d("Original Movie Data:", Integer.toString(moviedata.getSize()));
        }
    }
    private class MyDownLoadMovieDetailAsyncTask extends AsyncTask<String, Void, HashMap>
    {
        private final WeakReference<CustomOnClickRecycleViewListener> weakCustomOnClickReference;
        public MyDownLoadMovieDetailAsyncTask(CustomOnClickRecycleViewListener customOnClickRecycleViewListener)
        {
            weakCustomOnClickReference = new
                    WeakReference<CustomOnClickRecycleViewListener>(customOnClickRecycleViewListener);
        }
        @Override
        protected HashMap doInBackground(String...urls) {
            MovieDataJson threadMovieData2 = new MovieDataJson();
            // MovieJsonData threadMovieData = new MovieJsonData();
            for(String url : urls)
            {
                Log.d("Download DetailURl", url);
                threadMovieData2.downloadMovieDataJson(url);
            }
                                 // moviedata.getItem(0);
            Log.d("Single Movie Size: ", Integer.toString(threadMovieData2.getSize()));
            return threadMovieData2.getItem(0);
        }

        @Override
        protected void onPostExecute(HashMap movie) {
            /*moviedata.moviesList.clear();
            for(int i = 0 ;i < threadMovieData.getSize() ; i++)
            {
                moviedata.moviesList.add(threadMovieData.moviesList.get(i));
            }*/
            if (weakCustomOnClickReference != null)
            {
                final CustomOnClickRecycleViewListener customOnClickRecycleViewListener =
                        weakCustomOnClickReference.get();
               // HashMap<String,?> movie = moviedata.getItem(0);

                if (customOnClickRecycleViewListener != null) {
                    if (movie != null) {
                        Log.d("Jash Single Movie", movie.get("id").toString());
                        Log.d("Jash Single Movie", movie.get("name").toString());
                        Log.d("Jash Single Movie", movie.get("description").toString());
                        Log.d("Jash Single Movie", movie.get("director").toString());
                        Log.d("Jash Single Movie", movie.get("length").toString());
                    }
                    customOnClickRecycleViewListener.onRecycleViewItemClicked(movie);
                }
            }
        }
    }
    public myRecycleView() {
    }
    public class ActionBarCallBack implements ActionMode.Callback {
        int position;
        public ActionBarCallBack(int position)
        {
            this.position = position;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
           /* switch (id) {
                 case R.id.contextual_or_pop_menu_delete:
                     moviedata.getMoviesList().remove(position);
                 //moviedata.removeItem(position);
                     mRecycleViewAdapter.notifyItemRemoved(position);
                     mode.finish();
                     break;
                 case R.id.contextual_or_pop_menu_copy:
                 moviedata.addItem(position+1, (HashMap) ((HashMap)moviedata.getItem(position)).clone());
                 mRecycleViewAdapter.notifyItemInserted(position);
                 mode.finish();
                 break;
                 default:
                 break;
            }*/
            return false;
        }
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        //    mode.getMenuInflater().inflate(R.menu.contextual_or_popmenu, menu);
            return true;
        }
        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            HashMap hm = (HashMap)moviedata.getItem(position);
            mode.setTitle((String)hm.get("name"));
            return false;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        /* Retain this fragment so that it wont be destroyed when the configuration changes */
        setRetainInstance(true);
        moviedata = new MovieData();
        threadMovieData = new MovieDataJson();
/*        if (mImageMemoryCache == null)
        {
            *//* in KiloBytes *//*
            final int maxMemory = (int)Runtime.getRuntime().maxMemory()/1024;
            // Use 1/8 of available memory for this cache
            final int cacheSize = maxMemory/8;
            mImageMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String myUrl, Bitmap bitMap) {
                    // The cache size will be measured in KiloBytes rather than number of items
                    return bitMap.getByteCount()/1024;
                }
            };
        }*/
    }
    public interface CustomOnClickRecycleViewListener {
        public void onRecycleViewItemClicked(HashMap<String, ?> movie);
    }
    private CustomOnClickRecycleViewListener customOnClickRvListener;
    private static final String ARG_SECTION_NUMBER = "sectionNumber";
    public static myRecycleView newInstance(int section_number) {
        myRecycleView mr = new myRecycleView();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, section_number);
        mr.setArguments(args);
        return mr;
    }

    public void sortMovieDataByYear()
    {
        moviedata.sortItemsByYear();
        mRecycleViewAdapter.notifyDataSetChanged();
    }

    public void sortMovieDataByName() {
        moviedata.sortItemsByName();
        mRecycleViewAdapter.notifyDataSetChanged();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootview = inflater.inflate(R.layout.recycleviewfragment, container, false);
        customOnClickRvListener = (CustomOnClickRecycleViewListener) rootview.getContext();

        Log.d("Task1", "Oncreateview function moviedata function");
        mRecycleView = (RecyclerView)rootview.findViewById(R.id.recycleview2);
        // specify an adapter
        mRecycleViewAdapter = new MyRecycleViewAdapter(getContext(), moviedata.getMoviesList());
        Log.d("Task1", "Oncreateview function new RecycleAdapter");
        mRecycleView.setAdapter(mRecycleViewAdapter);
        Log.d("Task1", "Oncreateview function set the adapter to recycleview");
        // use this setting to improve th performance if you know that changes
        // in content donot change the size of recycle view
        mRecycleView.setHasFixedSize(true);
        // use a linear layout manager to specify how the items appear in the list
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        Log.d("Task1", "Oncreateview function new linearlayout manager");
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleViewAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick ( View view , int position) {
                HashMap<String,?> movie = (HashMap<String,?>) moviedata.getItem(position);
                Log.d("Adapter Click", "Movie position: "+Integer.toString(position));
                if (movie == null)
                {
                    Log.d("Adapter Click", "Null Movie received"+Integer.toString(position));
                }
            //    customOnClickRvListener.onRecycleViewItemClicked(movie);
                Log.d("Original Movie Size", Integer.toString(moviedata.getSize()));
                String id = (String)movie.get("id");
                String url = MovieDataJson.PHP_SERVER + "movies/id/" + id;
                Log.d("Single Movie", url);
                if (url != null)
                {
                   MyDownLoadMovieDetailAsyncTask downloadDetail = new
                           MyDownLoadMovieDetailAsyncTask(customOnClickRvListener);
                    downloadDetail.execute(url);
                }
            }
            public void onItemLongClick ( View view , int position) {
               // getActivity().startSupportActionMode(new ActionBarCallBack(position));
               // getActivity().startActionMode(new ActionBarCallBack(position));
                ActionBarCallBack abc = new ActionBarCallBack(position);
              //  getActivity().startActionMode((ActionMode.Callback)abc);
                AppCompatActivity abcd = (AppCompatActivity) getActivity();
                abcd.startSupportActionMode(abc);
            }
            public void onOverFlowMenuClick(View v, final int position) {
                PopupMenu popup = new PopupMenu(getActivity(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.contextual_or_pop_menu_copy:
/*
                                moviedata.addItem(position+1, (HashMap) ((HashMap)moviedata.getItem(position)).clone());
                                mRecycleViewAdapter.notifyItemInserted(position);
*/                              HashMap<String, ?> oldmovie = (HashMap)moviedata.getItem(position);
                                HashMap newmovie = new HashMap();
                                newmovie.put("id", oldmovie.get("id")+"_new");
                                newmovie.put("name", (String)oldmovie.get("name"));
                                newmovie.put("description", oldmovie.get("description"));
                               // newmovie.put("year", oldmovie.get("year"));
                               // newmovie.put("length",oldmovie.get("length"));
                                newmovie.put("year", null);
                                newmovie.put("length", null);
                                double newrating = Double.parseDouble(oldmovie.get("rating").toString());
                                Log.d("Jashwanth Rating: ", String.valueOf(newrating));
                              //  newmovie.put("rating", String.valueOf(newrating));
                                newmovie.put("rating", newrating);
                                newmovie.put("image", "Hellooo Image");
                             //   newmovie.put("director",oldmovie.get("director"));
                             //   newmovie.put("stars",oldmovie.get("stars"));
                                newmovie.put("director", null);
                                newmovie.put("stars", null);
                                newmovie.put("url",oldmovie.get("url"));
                                newmovie.put("selection",false);
                                threadMovieData.addItem(position, newmovie);
                                moviedata.addItem(position, newmovie);
                                mRecycleViewAdapter.notifyItemInserted(position);
                                Log.d("Jashwanth", "Clicked copy button");
                                return true;
                            case R.id.contextual_or_pop_menu_delete:
/*
                                moviedata.getMoviesList().remove(position);
                                mRecycleViewAdapter.notifyItemRemoved(position);
*/                              HashMap deletemovie = moviedata.getItem(position);
                                threadMovieData.deleteItem(position, deletemovie);
                                moviedata.getMoviesList().remove(position);
                                mRecycleViewAdapter.notifyItemRemoved(position);
                                Log.d("Jashwanth", "Clicked the delete button");
                                return true;
                         default:
                             return false;
                        }
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.contextual_or_popmenu,popup.getMenu());
                popup.show();
            }
        });
        // fancy animation
        // fancyAnimation();
        // animation for adapter
        // adapterAnimation();
        //defaultAnimation();
       /* itemAnimation();
        adapterAnimation();*/

        MyDownLoadJsonAsyncTask downLoadJsonAsyncTask = new
                                      MyDownLoadJsonAsyncTask(mRecycleViewAdapter);
      //  String url = "jashwanth-reddy.com/" + "movies";
        String url = MovieDataJson.PHP_SERVER + "movies";
        Log.d("Jashwanth", url);
        downLoadJsonAsyncTask.execute(url);
        return rootview;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
         /* if (menu.findItem(R.id.recycle_fragment_menu) == null) {
            inflater.inflate(R.menu.recycle_fragment_toolbar, menu);
          }*/
        SearchView search = null;

         if (menu.findItem(R.id.recycle_fragment_actionview) == null) {
             inflater.inflate(R.menu.recycle_fragment_actionview, menu);
          }
          MenuItem menuItem = menu.findItem(R.id.recycle_fragment_searchitem);
          if (menuItem != null) {
           search = (SearchView)menuItem.getActionView();
          }
          if (search != null) {
              search.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
             // int position = moviedata.findFirst(query);
             // double inpRating = moviedata.findFirst(query);
             //  String url = MovieDataJson.PHP_SERVER + "movies/rating/" + inpRating;
                String url = MovieDataJson.PHP_SERVER + "movies/rating/" + query;
                if (url != null)
                {
                    MyDownLoadJsonAsyncTask downLoadJsonAsyncTask = new
                            MyDownLoadJsonAsyncTask(mRecycleViewAdapter);
                    downLoadJsonAsyncTask.execute(url);
                }
             /* if (position >= 0) {
                  mRecycleView.scrollToPosition(position);
              }*/
              //  Toast.makeText(getContext(), "Search query entered", Toast.LENGTH_SHORT).show();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String query) {
              return true;
            }});
          }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
      /*  if (id == R.id.recycleview_toolbar_title) {
            Toast.makeText(getContext(), "Inside recycle fragment title click", Toast.LENGTH_LONG).show();
        }*/
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    /*private void defaultAnimation(){
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(5000);
        animator.setRemoveDuration(3000);
        mRecycleView.setItemAnimator(animator);
    }*/
 /*   private void itemAnimation(){
        ScaleInAnimator anim = new ScaleInAnimator();
        anim.setAddDuration(5000);
        anim.setRemoveDuration(2000);
        mRecycleView.setItemAnimator(anim);
    }
    private void adapterAnimation() {
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mRecycleViewAdapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        mRecycleView.setAdapter(scaleAdapter);
    }*/
}
