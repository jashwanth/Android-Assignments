package com.example.mina.twelfth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Recycler View Adapter to populate recycler view items.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Map<String,?>> mDataSet;
    private Context mContext ;
    OnItemClickListener myItemClickListener;

    //Constructor
    public MyRecyclerViewAdapter(Context myContext, List<Map<String,?>> myDataSet)  {
        mContext = myContext;
        mDataSet = myDataSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView vIcon;
        TextView name;
        TextView description;
        RatingBar ratingStars;
        ImageView menuIcon;

        public ViewHolder(View v) {
            super(v);
            vIcon = (ImageView) v.findViewById(R.id.icon);
            name = (TextView) v.findViewById(R.id.movieName);
            ratingStars = (RatingBar) v.findViewById(R.id.ratingBar);
            description = (TextView) v.findViewById(R.id.description);
            menuIcon = (ImageView) v.findViewById(R.id.moreoptions);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myItemClickListener != null) {
                        myItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

        public void bindMovieData(Map<String,?> movie) {
            if(name !=null) name.setText((String) movie.get("name"));
            vIcon.setImageResource((Integer) movie.get("image"));

            if(description !=null) description.setText((String) movie.get("description"));
            if(ratingStars !=null) {
                Double dRating = (Double)movie.get("rating");
                float fRating = dRating.floatValue()/2;
                ratingStars.setRating(fRating);
            }
        }
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowview_item_recycler,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String,?> movie =  mDataSet.get(position);
        holder.bindMovieData(movie);
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    /* Listener for onClick on Movie Item */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.myItemClickListener = mItemClickListener;
    }

}
