package com.example.jashwanthreddy.assignment10;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

public class RecyclerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "Debug";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MovieData movieData = new MovieData();
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    LinearLayoutManager layoutManager;

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
                    movieData.getMoviesList().remove(position);
                    //moviedata.removeItem(position);
                    myAdapter.notifyItemRemoved(position);

                    mode.finish();
                    break;
                case R.id.contextual_or_pop_menu_copy:
                    movieData.addItem(position+1, (HashMap) ((HashMap)movieData.getItem(position)).clone());
                    myAdapter.notifyItemInserted(position+1);
                    mode.finish();
                    break;
                default:
                    break;
            }
            return false;
        }
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.contextuallayout, menu);
            return true;
        }
        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            HashMap hm = (HashMap)movieData.getItem(position);
            mode.setTitle((String)hm.get("name"));
            return false;
        }
    }
    public RecyclerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RecyclerFragment newInstance() {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        // args.putString(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
        // fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: OnCreate invoked");
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    public void sortMovieDataByName() {
                     movieData.sortmovies(movieData.getMoviesList());
                myAdapter.notifyDataSetChanged();
    }
    public void sortMovieDataByYear() {
        movieData.sortmoviesonyear(movieData.getMoviesList());
        myAdapter.notifyDataSetChanged();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)  {
        Log.d(TAG, "onCreateOptionsMenu: Enter of onCreateOptionsMenu method");
        //inflater.inflate(R.menu.frag_menu, menu);

        if (menu.findItem(R.id.recycle_fragment_menu) == null) {
            Log.d(TAG, "onCreateOptionsMenu: Menu has been inflated");
            inflater.inflate(R.menu.frag_menu, menu);
        }

        SearchView searchView = null;
        if (menu.findItem(R.id.recycle_fragment_actionview) == null) {
            inflater.inflate(R.menu.recycle_fragment_actionview, menu);
        }

        MenuItem menuItem = menu.findItem(R.id.recycle_fragment_searchitem);
        if(menuItem!=null){
            searchView = (SearchView) menuItem.getActionView();

        }


        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.d(TAG, "onQueryTextSubmit: The Query submitted is"+query);
                    int position = movieData.findFirst(query);
                    Log.d(TAG, "onQueryTextSubmit: Position returned is"+position);
                    if (position >= 0) {
                        recyclerView.scrollToPosition(position);


                    }
                    return true;

                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return true;
                }
            });


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


    private RecyclerOnClickListener recyclerOnClickListenerO;

    public interface RecyclerOnClickListener {
        public void onClicked(View v, int position);

    }

    public void viewClicked(View v, int position) {
        recyclerOnClickListenerO.onClicked(v, position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);


        recyclerView = (RecyclerView)
                view.findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true);
        layoutManager = new
                LinearLayoutManager(getActivity());
        recyclerOnClickListenerO = (RecyclerOnClickListener) view.getContext();
        // Set the LayoutManager to RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        // myAdapter = new MyAdapter(getActivity(), movieData);
        myAdapter = new MyAdapter(getActivity(), movieData);
        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if(id == R.id.statebutton){

                    Log.d(TAG, "onClick: StateButton has been clicked");
                    sortMovieDataByName();


                }
            }
        };


        ((Button) view.findViewById(R.id.statebutton)).setOnClickListener(onClickListener);

        myAdapter.SetOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

               // ImageView shared = (ImageView)view.findViewById(R.id.movieImage);
                viewClicked(view, position);
            }



            @Override
            public void onItemLongClick(View view, int position) {

                ActionBarCallBack abc = new ActionBarCallBack(position);
                //  getActivity().startActionMode((ActionMode.Callback)abc);
                AppCompatActivity abcd = (AppCompatActivity) getActivity();
                abcd.startSupportActionMode(abc);

            }

            @Override
            public void onOverFlowMenuClick(View v, final int position) {
                PopupMenu popup = new PopupMenu(getActivity(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.contextual_or_pop_menu_copy:
                                Log.d(TAG, "onMenuItemClick: Before adding"+movieData.getMoviesList().size());
                                movieData.addItem(position, (HashMap) ((HashMap)movieData.getItem(position)).clone());
                                Log.d(TAG, "onMenuItemClick: After adding"+movieData.getMoviesList().size());

                                myAdapter.notifyItemInserted(position+1);
                                myAdapter.notifyDataSetChanged();
                                return true;
                            case R.id.contextual_or_pop_menu_delete:
                                movieData.getMoviesList().remove(position);
                                myAdapter.notifyItemRemoved(position);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.contextuallayout,popup.getMenu());
                popup.show();
            }
        });


        recyclerView.setAdapter(myAdapter);
       // itemAnimation();
       // adapterAnimation();
        // recyclerView.setItemAnimator ( new S );
        return view;

    }

    public void adapterAnimation(){
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(myAdapter);
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(alphaInAnimationAdapter);
        scaleInAnimationAdapter.setDuration(300);
        recyclerView.setAdapter(scaleInAnimationAdapter);

    }

    public void itemAnimation(){
        ScaleInAnimator scaleInAnimation = new ScaleInAnimator();
        scaleInAnimation.setInterpolator(new OvershootInterpolator());
        scaleInAnimation.setAddDuration(100);
        scaleInAnimation.setRemoveDuration(100);
        recyclerView.setItemAnimator(scaleInAnimation);

    }


}
