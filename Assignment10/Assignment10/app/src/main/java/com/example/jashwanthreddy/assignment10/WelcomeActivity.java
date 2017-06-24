package com.example.jashwanthreddy.assignment10;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.v4.view.PagerAdapter;

import java.util.HashMap;
import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MyFragmentStatePagerAdapter mypagerAdapter;
    private LinearLayout dotsLayout;
    private LinearLayout buttonsLayout;
    private Button btnSkip, btnNext, btnPrev, btnDone;
    private MovieData movieData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);

        movieData = new MovieData();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        buttonsLayout = (LinearLayout)findViewById(R.id.layoutButtons);
        final int childcount = buttonsLayout.getChildCount();
        for (int i = 0; i < childcount; i++)
        {
            View v = buttonsLayout.getChildAt(i);
            if (v instanceof Button)
            {
              if (findViewById(R.id.btn_skip) == v)
              {
                  btnSkip = (Button)v;
              }
              if (findViewById(R.id.btn_next) == v)
              {
                  btnNext = (Button)v;
              }
              if (findViewById(R.id.btn_prev) == v)
              {
                  btnPrev = (Button)v;
              }
              if (findViewById(R.id.btn_done) == v)
              {
                  btnDone = (Button)v;
              }
            }
        }
        btnSkip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //        Toast.makeText(getApplicationContext(), "Done Pressed", Toast.LENGTH_SHORT).show();
                launchHomeScreen();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //        Toast.makeText(getApplicationContext(), "Next Pressed", Toast.LENGTH_SHORT).show();
                int current = getItem(+1);
                if (current < movieData.getSize()) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //        Toast.makeText(getApplicationContext(), "Skip Pressed", Toast.LENGTH_SHORT).show();
                int current = getItem(-1);
                if (current >= 0) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                }
          //      Toast.makeText(getApplicationContext(), "Previous Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        mypagerAdapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager(), movieData.getSize());
        viewPager.setAdapter(mypagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                R.color.bg_screen1));
    }
    private void launchHomeScreen() {
     //   prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageSelected(int position) {
           if (position % 3 == 0)
           {
               viewPager.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                       R.color.bg_screen1));
           }
           else if(position % 3 == 1)
           {
               viewPager.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                       R.color.bg_screen2));
           }
           else
           {
               viewPager.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                       R.color.bg_screen3));
           }
        }
        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    };

    /**
     * View pager adapter
     */
    /*public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(MovieFragment.newInstance(movieData.getItem(position)),
                         container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return movieData.getSize();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }*/
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



}
