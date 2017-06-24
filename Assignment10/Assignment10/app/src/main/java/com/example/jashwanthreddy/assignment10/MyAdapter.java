package com.example.jashwanthreddy.assignment10;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sumanthsai on 2/13/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Map<String, ?>> mItems;
    private int lastPosition = -1;
    private Context mContext;
    OnItemClickListener itemClickListener;

    public MyAdapter(Context context, MovieData movieData) {
        mContext = context;
        mItems = movieData.getMoviesList();

    }

//    public MyAdapter( MovieData movieData) {
//       // mContext = context;
//        mItems = movieData.getMoviesList();
//    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);

        public void onOverFlowMenuClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView movieImage;
        public TextView movieName;
        public TextView movieDesc;
        public ImageView overflow_image;
        // public CheckBox movieCheck;


        public ViewHolder(View view) {
            super(view);
            movieImage = (ImageView)
                    view.findViewById(R.id.movieImage);
            movieName = (TextView)
                    view.findViewById(R.id.movieName);
            movieDesc = (TextView)
                    view.findViewById(R.id.movieDesc);
            overflow_image = (ImageView) view.findViewById(R.id.cardview_overflow);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                            itemClickListener.onItemClick(v, getAdapterPosition());
                        }
                    }
                }


            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemClickListener != null) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                            itemClickListener.onItemLongClick(v, getAdapterPosition());
                        }
                    }
                    return true;
                }


            });

            overflow_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onOverFlowMenuClick(v, getAdapterPosition());
                }
            });


        }


    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = null;

        v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.itemlayout, parent, false);


//        if(viewType <= 4){
//        v = LayoutInflater.from(parent.getContext()).inflate(
//                R.layout.itemlayout, parent, false);}
//        if(viewType>=25){
//            v = LayoutInflater.from(parent.getContext()).inflate(
//                    R.layout.itemlayout3, parent, false);
//        }
//        if(viewType>4 && viewType <25){
//            v = LayoutInflater.from(parent.getContext()).inflate(
//                    R.layout.itemlayout2, parent, false);
//        }
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    //This is recycled viewholder.
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        final Map<String, ?> movie = mItems.get(position);
        final HashMap<String, Boolean> temp = (HashMap<String, Boolean>) movie;
        holder.movieImage.setImageResource((Integer) movie.get("image"));
        holder.movieName.setText((String) movie.get("name"));
        holder.movieDesc.setText((String) movie.get("description"));


    }

    @Override
    public int getItemCount()

    {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
