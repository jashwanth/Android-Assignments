package com.example.jashwanthreddy.assignment3;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

public class ViewPagerActivity extends AppCompatActivity {
    MyFragmentStatePagerAdapter mypagerAdapter;
    ViewPager mviewPager;
    MovieData movieData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        movieData = new MovieData();
        //create the adapter that will return a fragment for each of the three primary
        // sections of the activity.
        mypagerAdapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager(), movieData.getSize());
        mviewPager = (ViewPager)findViewById(R.id.activity_view_pager);
        mviewPager.setAdapter(mypagerAdapter);
        mviewPager.setCurrentItem(3);
        customizeViewPager();
        // set TableLayout: This ensures that tab selection events update the viewPager
        // and page changes update the selected tab.
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);
    }
    private void customizeViewPager()
    {
     //   mviewPager.setOffscreenPageLimit(4);
        mviewPager.setPageTransformer(false, new ViewPager.PageTransformer()
        {
          @Override
          public void transformPage(View page, float position)
          {
           // Fading out
            // final float normalised_position = Math.abs(position)-1
              final float normalised_position = Math.abs(Math.abs(position)-1);
              page.setScaleX(normalised_position/ 2+0.5f);
              page.setScaleY(normalised_position/ 2+0.5f);
              // Rotation Effect
              page.setRotationY(position * -30);

          }
        });

        mviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int i, float v, int i2)
            {
            }
            @Override
            public void onPageSelected(int i)
            {
               // Toast.makeText(ViewPagerActivity.this, Integer.toString(i),
               //         Toast.LENGTH_LONG).show();
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
    public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
        int count;
        public MyFragmentStatePagerAdapter(FragmentManager fm, int size) {
            super(fm);
            count = size;
        }
        @Override
        public Fragment getItem(int position) {
            //getItem is called to instantiate the fragment for given page
            return MovieFragment.newInstance(movieData.getItem(position));
        }
        @Override
        public int getCount() { return count;}
        @Override
        public CharSequence getPageTitle(int position)
        {
            Locale l = Locale.getDefault();
            HashMap<String, ?> movie = (HashMap<String, ?>)movieData.getItem(position);
            String name = (String)movie.get("name");
            return name.toUpperCase(l);
        }
    }
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        int count;
        public MyFragmentPagerAdapter(FragmentManager fm, int size)
        {
            super(fm);
            count = size;
        }

        @Override
        public Fragment getItem(int position)
        {
            //getItem is called to instantiate the fragment for given page
            return MovieFragment.newInstance(movieData.getItem(position));
        }

        @Override
        public int getCount() { return count;}

        @Override
        public CharSequence getPageTitle(int position)
        {
            Locale l = Locale.getDefault();
            HashMap<String, ?> movie = (HashMap<String, ?>)movieData.getItem(position);
            String name = (String)movie.get("name");
            return name.toUpperCase(l);
        }

    }
}

