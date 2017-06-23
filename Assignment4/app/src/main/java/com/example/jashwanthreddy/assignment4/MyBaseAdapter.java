package com.example.jashwanthreddy.assignment4;

import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.BaseAdapter;
import android.content.Context;
import java.util.Map;
import java.util.List;
import android.view.*;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by jashwanthreddy on 2/14/17.
 */



public class MyBaseAdapter extends BaseAdapter{
    private final Context context;
    private final List<Map<String,?>> movieList;

    public MyBaseAdapter(Context context, List<Map<String,?>> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowview = inflater.inflate(R.layout.listrow, parent, false);
        ImageView imageview = (ImageView) rowview.findViewById(R.id.imageView3);
        TextView name = (TextView)rowview.findViewById(R.id.textView5);
        TextView Description = (TextView)rowview.findViewById(R.id.textView6);
        CheckBox checkBox = (CheckBox)rowview.findViewById(R.id.checkBox);
        RatingBar rating = (RatingBar)rowview.findViewById(R.id.ratingBar);
        rating.setMax(5);
        rating.setStepSize(0.1f);
        Map<String, ?> entry = movieList.get(position);
        Double frat = (Double)entry.get("rating");
        float ft = (float) (frat.floatValue())/2.0f;
        rating.setRating((float)ft);
        imageview.setImageResource((Integer)entry.get("image"));
        name.setText((String)entry.get("name"));
        Description.setText((String)entry.get("description"));
        return rowview;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }
}
