package com.example.jashwanthreddy.assignment4;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

/**
 * Created by jashwanthreddy on 2/15/17.
 */

public class myRecycleView extends Fragment {
    RecyclerView mRecycleView;
    MyRecycleViewAdapter mRecycleViewAdapter;
    MovieData moviedata;
    public myRecycleView() {}
    Button selectall;
    Button clearall;
    Button delete;
    Button sort;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public interface CustomOnClickRecycleViewListener {
        public void onRecycleViewItemClicked (View v , HashMap<String,?> movie);
    }
    private CustomOnClickRecycleViewListener customOnClickRvListener;
    public static myRecycleView newInstance(int section_number) {
        myRecycleView mr = new myRecycleView();
        Bundle args = new Bundle();
        return mr;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootview = inflater.inflate(R.layout.recycleviewfragment, container, false);
        customOnClickRvListener = (CustomOnClickRecycleViewListener) rootview.getContext();
        selectall = (Button)rootview.findViewById(R.id.selectall);
        clearall = (Button)rootview.findViewById(R.id.clearall);
        delete = (Button)rootview.findViewById(R.id.delete);
        sort   = (Button)rootview.findViewById(R.id.sort);
        moviedata = new MovieData();


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


        selectall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                moviedata.selectall();
                mRecycleViewAdapter.notifyDataSetChanged();
            }
        });

        clearall.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v) {
               moviedata.clearall();
               mRecycleViewAdapter.notifyDataSetChanged();
           }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               // moviedata.deleteitems();
                 class myObject {
                    int index;
                     HashMap<String,?> mapobj;
                     public myObject(int x, HashMap<String,?> y) {
                         index = x;
                         mapobj = y;
                     }
                }
                ArrayList<myObject> clean = new ArrayList<myObject>();
                for (int i = 0, j = 0; i < moviedata.getSize(); i++) {
                    HashMap<String,Boolean> temp =
                            (HashMap<String,Boolean>)moviedata.getMoviesList().get(i);
                    if (temp.get("selection") == true) {
                        myObject obj1 = new myObject(i,(HashMap<String,?>)moviedata.getMoviesList().get(i));
                        clean.add(j,obj1);
                        j++;
                    }
                }
                Integer ci = clean.size();
                Log.d("Clean size: ", ci.toString());
                for (int i = 0; i < clean.size(); i++)
                {
                    int index = clean.get(i).index;
                    HashMap<String,Boolean> temp =
                            (HashMap<String,Boolean>)clean.get(i).mapobj;
                    moviedata.getMoviesList().remove(index-i);
                    mRecycleViewAdapter.notifyItemRemoved(index-i);
                }
                /*for(Map<String, ?> map : moviedata.getMoviesList())
                {
                    HashMap<String,Boolean> temp= (HashMap<String,Boolean>)map;
                    //temp.put("selection", false);
                    if (temp.get("selection") == true)
                    {
                     //   clean.add(map);
                        //  moviesList.remove(map);
                    }
                }*/
                //  moviesList.removeAll(clean);
             //   mRecycleViewAdapter.notifyItemRemoved();
              //  mRecycleViewAdapter.notifyDataSetChanged();
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviedata.sortItemsByYear();
                mRecycleViewAdapter.notifyDataSetChanged();
            }
        });
        mRecycleViewAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick ( View view , int position) {
                HashMap<String,?> movie = (HashMap<String,?>) moviedata.getItem(position);
                customOnClickRvListener.onRecycleViewItemClicked(view,movie);
            }
            public void onItemLongClick ( View view , int position) {
                //    moviedata.removeItem(position);
                moviedata.addItem(position+1,(HashMap)((HashMap) moviedata.getItem(position)).clone());
                mRecycleViewAdapter.notifyItemInserted(position+1);
               //mRecycleViewAdapter.notifyItemRemoved(position);
               // mRecycleViewAdapter.notifyDataSetChanged();
            }
        });
        //  MyBaseAdapter myBaseAdapter = new MyBaseAdapter(getApplicationContext(), moviedata.getMoviesList());
        //   listview.setAdapter(myBaseAdapter);

        // fancy animation
        // fancyAnimation();
        // animation for adapter
        // adapterAnimation();
        //defaultAnimation();
        itemAnimation();
        adapterAnimation();
        return rootview;
    }
    private void defaultAnimation(){
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(5000);
        animator.setRemoveDuration(3000);
        mRecycleView.setItemAnimator(animator);
    }
    private void itemAnimation(){
        ScaleInAnimator anim = new ScaleInAnimator();
        anim.setAddDuration(5000);
        anim.setRemoveDuration(2000);
        mRecycleView.setItemAnimator(anim);
    }
    private void adapterAnimation() {
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mRecycleViewAdapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        mRecycleView.setAdapter(scaleAdapter);
    }
}
