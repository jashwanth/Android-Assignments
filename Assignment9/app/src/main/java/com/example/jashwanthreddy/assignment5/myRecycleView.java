package com.example.jashwanthreddy.assignment5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.Menu;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.zip.Inflater;

/*import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;*/

/**
 * Created by jashwanthreddy on 2/15/17.
 */

public class myRecycleView extends Fragment {
    RecyclerView mRecycleView;
  //  MyRecycleViewAdapter mRecycleViewAdapter;
    MyFirebaseRecylerAdapter myFirebaseRecylerAdapter;
    MovieData moviedata;
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
            switch (id) {
                 case R.id.contextual_or_pop_menu_delete:
                     moviedata.getMoviesList().remove(position);
                 //moviedata.removeItem(position);
                  //   mRecycleViewAdapter.notifyItemRemoved(position);
                     mode.finish();
                     break;
                 case R.id.contextual_or_pop_menu_copy:
               //  moviedata.addItem(position+1, (HashMap) ((HashMap)moviedata.getItem(position)).clone());
               //  mRecycleViewAdapter.notifyItemInserted(position);
                 mode.finish();
                 break;
                 default:
                 break;
            }
            return false;
        }
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.contextual_or_popmenu, menu);
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
    }
    public interface CustomOnClickRecycleViewListener {
        public void onRecycleViewItemClicked(View v, HashMap<String, ?> movie);
    }
    private CustomOnClickRecycleViewListener customOnClickRvListener;
    public static myRecycleView newInstance(int section_number) {
        myRecycleView mr = new myRecycleView();
        Bundle args = new Bundle();
        return mr;
    }

    /*public void sortMovieDataByYear()
    {
        moviedata.sortItemsByYear();
        mRecycleViewAdapter.notifyDataSetChanged();
    }

    public void sortMovieDataByName() {
        moviedata.sortItemsByName();
        mRecycleViewAdapter.notifyDataSetChanged();
    }*/

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootview = inflater.inflate(R.layout.recycleviewfragment, container, false);
        customOnClickRvListener = (CustomOnClickRecycleViewListener) rootview.getContext();
        moviedata = new MovieData();

        Log.d("Task1", "Oncreateview function moviedata function");
        mRecycleView = (RecyclerView)rootview.findViewById(R.id.recycleview2);
        // specify an adapter
      //  mRecycleViewAdapter = new MyRecycleViewAdapter(getContext(), moviedata.getMoviesList());
        Log.d("Task1", "Oncreateview function new RecycleAdapter");

        DatabaseReference childref = FirebaseDatabase.getInstance().getReference()
                .child("moviedata").getRef();

        myFirebaseRecylerAdapter = new MyFirebaseRecylerAdapter(Movie.class, R.layout.listrow,
                MyFirebaseRecylerAdapter.MovieViewHolder.class, childref , getContext());

        if (moviedata.getSize() == 0) {
            moviedata.setAdapter(myFirebaseRecylerAdapter);
            moviedata.setContext(getActivity());
            moviedata.initializeDataFromCloud();
        }
        Log.d("Task1", "Oncreateview function set the adapter to recycleview");
        // use this setting to improve th performance if you know that changes
        // in content donot change the size of recycle view
        mRecycleView.setHasFixedSize(true);
        // use a linear layout manager to specify how the items appear in the list
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        Log.d("Task1", "Oncreateview function new linearlayout manager");
        mRecycleView.setLayoutManager(mLayoutManager);
       /* mRecycleViewAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick (View view, int position) {
                HashMap<String,?> movie = (HashMap<String,?>) moviedata.getItem(position);
                customOnClickRvListener.onRecycleViewItemClicked(view,movie);
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
                                moviedata.addItem(position+1, (HashMap) ((HashMap)moviedata.getItem(position)).clone());
                                mRecycleViewAdapter.notifyItemInserted(position);
                                return true;
                            case R.id.contextual_or_pop_menu_delete:
                                moviedata.getMoviesList().remove(position);
                                mRecycleViewAdapter.notifyItemRemoved(position);
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
        });*/
        myFirebaseRecylerAdapter.setOnItemClickListener(new MyFirebaseRecylerAdapter.RecycleItemClickListener()
        {
            @Override
            public void onItemClick(final View view, int position) {

                Log.d("Task", "onItemClick: Enter of onItemClick method ");
                // handle=navigationlistener.naviagteToCardsSelected(position, movie);
                HashMap<String, ?> movie = (HashMap<String, ?>) moviedata.getItem(position);
                String id = (String) movie.get("id");
                Log.d("Clicked Recycle ID: ", id);
                DatabaseReference ref = moviedata.getFireBaseRef();
                Log.d("Database Ref", "Got the moviedata reference from Firebase");
                ref.child(id).addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot)
                    {
                        HashMap<String, String> movie = (HashMap<String, String>) dataSnapshot.getValue();
                        Log.d("Movie Detail", movie.get("stars"));
                        Log.d("Movie Details", movie.get("length"));
                        Log.d("Movie Details", movie.get("description"));
                        Log.d("Movie Details", movie.get("director"));
                        customOnClickRvListener.onRecycleViewItemClicked(view, movie);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {
                        Log.d("My Test", "The read failed: " + databaseError.getMessage());
                    }
                });
            }
            @Override
            public void onItemLongClick(View view, int position) {
            }
            @Override
            public void onOverFlowMenuClick(View v, final int position) {
                PopupMenu popup = new PopupMenu(getActivity(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        HashMap movie;
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.contextual_or_pop_menu_copy:
                                movie = (HashMap) ((HashMap) moviedata.getItem(position).clone());
                                movie.put("id", ((String) movie.get("id") + "_new"));
                                Log.d("Clicked Copy Button", (String)movie.get("name"));
                                moviedata.addItemToServer(movie);
                                return true;
                            case R.id.contextual_or_pop_menu_delete:
                                movie = (HashMap) ((HashMap) moviedata.getItem(position));
                                moviedata.removeItemFromServer(movie);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.contextual_or_popmenu, popup.getMenu());
                popup.show();
            }
        });
        //  mRecycleView.setAdapter(mRecycleViewAdapter);
        mRecycleView.setAdapter(myFirebaseRecylerAdapter);

        // fancy animation
        // fancyAnimation();
        // animation for adapter
        // adapterAnimation();
        //defaultAnimation();
       /* itemAnimation();
        adapterAnimation();*/
        return rootview;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
          if (menu.findItem(R.id.recycle_fragment_menu) == null) {
            inflater.inflate(R.menu.recycle_fragment_toolbar, menu);
          }
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
              int position = moviedata.findFirst(query);
              if (position >= 0) {
                  mRecycleView.scrollToPosition(position);
              }
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
        if (id == R.id.recycleview_toolbar_title) {
            Toast.makeText(getContext(), "Inside recycle fragment title click", Toast.LENGTH_LONG).show();
        }
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
    @Override
    public void onSaveInstanceState(Bundle content) {
        // NEVER CALLED
        super.onSaveInstanceState(content);
        //More stuff
    }
}
