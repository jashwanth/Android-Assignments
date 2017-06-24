package com.example.mina.twelfth;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Recyclerview Fragment which displays list of Movies
 */
public class RecyclerViewFragment extends Fragment {

    private OnListItemSelectedListener mListener;
    RecyclerView myRecyclerView;
    LinearLayoutManager mLayoutManager;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    MovieDataJSONLocal data;
    List<Map<String, ?>> moviesList;

    public interface OnListItemSelectedListener {
        void onListItemSelected(int position, HashMap<String, ?> movie);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnListItemSelectedListener)activity;

        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString()+"must implement onFragmentInteraction Listener");
        }

    }

    public static RecyclerViewFragment newInstance(int option) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        myRecyclerView  = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(mLayoutManager);
        moviesList = data.getMoviesList();

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity(),moviesList);
        myRecyclerView.setAdapter(myRecyclerViewAdapter);

        myRecyclerViewAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                HashMap<String, ?> movie = (HashMap<String, ?>) moviesList.get(position);
                mListener.onListItemSelected(position, movie);
            }
        });
        return rootView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new MovieDataJSONLocal();
        data.loadLocalMovieDataJson(getActivity());
    }

}
