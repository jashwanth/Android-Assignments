package com.example.jashwanthreddy.assignment8;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jashwanthreddy on 3/17/17.
 */

public class MovieDataJson {
   //  public static final String PHP_SERVER = "jashwanth-reddy.com/";
   public static final String PHP_SERVER = "http://saisumanth.com/";
  //  public static final String PHP_SERVER = "http://jashwanth-reddy.com/";
    public static final String TAG = "Debug";
    List<Map<String,?>> moviesList;

    public int getSize(){
        return moviesList.size();
    }

    public MovieDataJson()
    {
        moviesList = new ArrayList<Map<String,?>>();
    }

    public void downloadMovieDataJson(String url)
    {
        String jsondata = MyUtility.downloadJSONusingHTTPGetRequest(url);
        try {
         //   Log.d("Download Json Raw", jsondata);
         //   JSONObject jsonObject = new JSONObject(jsondata);
              JSONArray arr = new JSONArray(jsondata);
           // JSONObject jsonobj = new JSONObject(jsondata);
         //   Log.d("Jashwanth", jsonObject.toString());
         //   JSONArray arr = jsonObject.getJSONArray("");
            moviesList.clear();
            for (int i = 0; i < arr.length(); i++)
            {
                JSONObject jsonobj = arr.getJSONObject(i);
                /*if (i < 5)
                {
                    Log.d("Jashwanth :", jsonobj.getString("id"));
                    Log.d("Jashwanth :", jsonobj.getString("url"));
                }*/
                Log.d("Download Json", jsonobj.getString("id"));
                Log.d("Download Json", jsonobj.getString("url"));
                Log.d("Download Json RATING IS", jsonobj.getString("rating"));
                double rat = jsonobj.getDouble("rating");
             //   Log.d("Jessy", "downloadMovieDataJson:rating is "+String.valueOf(rat));



           //     Log.d("Download Json Rat", String.valueOf(jsonobj.getDouble("rating")));
                moviesList.add(createMovie(jsonobj.getString("id"), jsonobj.getString("name"),
                        jsonobj.getString("description"),
                        jsonobj.getString("year"), jsonobj.getString("length"), rat,
                        jsonobj.getString("director"), jsonobj.getString("stars"),
                        jsonobj.getString("url")));
            }
            Log.d("Json Movie size", Integer.toString(moviesList.size()));
        }
        catch (Exception e)
        {
            Log.d("Download Json error", e.toString());
        }
    }

    private HashMap createMovie(String id, String name,  String description, String year,
                                String length, double rating,  String director, String stars, String url)
    {
        HashMap movie = new HashMap();
    //    movie.put("image",image);
        movie.put("id", id);
        movie.put("name", name);
        movie.put("description", description);
        movie.put("year", year);
        movie.put("length",length);
        movie.put("rating",rating);
        movie.put("director",director);
        movie.put("stars",stars);
        movie.put("url",url);
        movie.put("selection",false);
        return movie;
    }

    public HashMap getItem(int i)
    {
        if (i >=0 && i < moviesList.size())
        {
            return (HashMap) moviesList.get(i);
        }
        else return null;
    }

    public void addItem(int position, Map<String, ?> item)
    {
        Log.d(TAG, "addItem: "+item.get("description"));
        final JSONObject json;
        if (item != null)
        {
          json = new JSONObject(item);
        }
        else
            json = null;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String url = PHP_SERVER + "add";
                MyUtility.sendHttPostRequest(url, json);
            }
        };
        new Thread(runnable).start();
        moviesList.add(position, item);
    }
    public void deleteItem(int position, final Map<String, ?> item)
    {
        Log.d(TAG, "addItem: "+item.get("description"));
        final JSONObject json;
        if (item != null)
        {
            json = new JSONObject(item);
        }
        else
            json = null;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String url = PHP_SERVER + "delete" ;
                //+ item.get("id");
                MyUtility.sendHttPostRequest(url, json);
            }
        };
        new Thread(runnable).start();
      //  moviesList.add(position, item);
        moviesList.remove(position);
    }
}
