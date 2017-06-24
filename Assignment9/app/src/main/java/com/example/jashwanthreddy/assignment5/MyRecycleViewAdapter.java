package com.example.jashwanthreddy.assignment5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jashwanthreddy on 2/14/17.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    private List<Map<String,?>> mDataset;
    private int lastPosition = -1;
    private Context mcontext;
   // AdapterView.OnItemClickListener
    static  OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
        public void onOverFlowMenuClick(View v, final int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    // constructor
    public MyRecycleViewAdapter(Context myContext, List<Map<String,?>> myDataset)
    {
        Log.d("RecycleViewAdapter", "Inside the constructor function" + myDataset.size());
        mDataset = myDataset;
        mcontext = myContext;
    }

    // Return the size of dataset invoked by layout manager
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        //return position%3 ;
        if (position <= 4)
        {
            return 1;
        }
        else if (position >= getItemCount()-5)
        {
            return 3;
        }
        else
            return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageview;
        public TextView title;
        public TextView description;
        public CheckBox checkBox;
        public ImageView overflow_image;
  //      public RatingBar ratingBar;
        public ViewHolder(View v) {
            super(v);
            imageview = (ImageView)v.findViewById(R.id.cardview_imageview);
            title = (TextView)v.findViewById(R.id.cardview_title);
            description = (TextView)v.findViewById(R.id.cardview_description);
            checkBox = (CheckBox)v.findViewById(R.id.cardview_checkBox);
            overflow_image = (ImageView)v.findViewById(R.id.cardview_overflow);
           /* ratingBar = (RatingBar)v.findViewById(R.id.cardview_ratingbar);
            ratingBar.setMax(5);
            ratingBar.setStepSize(0.1f);*/

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                            mItemClickListener.onItemClick(v, getAdapterPosition());
                        }
                    }
                }
            });

            v.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                  if (mItemClickListener != null) {
                      if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                         mItemClickListener.onItemLongClick(v, getAdapterPosition());
                      }
                  }
                    return true;
                }
            });
            overflow_image.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onOverFlowMenuClick(v, getAdapterPosition());
                }
            });
        }

        public void bindMovieData(final Map<String, ?> movie) {
            title.setText((String)movie.get("name"));
            imageview.setImageResource((Integer)movie.get("image"));
            description.setText((String)movie.get("description"));
            checkBox.setChecked((Boolean)movie.get("selection"));
           /* ratingBar.setMax(5);
            ratingBar.setStepSize(0.1f);*/
            Double dr = (Double)movie.get("rating");
            float fr = (float)dr.floatValue()/2.0f;
 /*           ratingBar.setRating((float)fr);*/
            checkBox.setOnClickListener(new View.OnClickListener(){
                final HashMap<String, Boolean> temp = (HashMap<String,Boolean>)movie;
                @Override
                public void onClick(View v) {
                      temp.put("selection", true);
                }
            });
        }

    }

    // Replace the contents of the view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        // get the element from your dataset at this position
        // replace the contents of the view with parent
        Map<String, ?> movie = mDataset.get(position);
        holder.bindMovieData(movie);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v;
        // create a new view
        /*switch (viewType) {
            case 1:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, parent, false);
                break;
            case 2:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, parent, false);
                break;
            case 3:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, parent, false);
                break;
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, parent, false);
                break;
        }*/
       v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, parent, false);
        // set view layout, size, margins . paddings etc..
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
}
