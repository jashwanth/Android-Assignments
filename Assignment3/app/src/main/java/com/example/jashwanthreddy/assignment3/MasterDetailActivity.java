package com.example.jashwanthreddy.assignment3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.util.Log;

public class MasterDetailActivity extends AppCompatActivity implements MasterFragment.CustomOnClickMasterFragmentListener {
    MovieData movieData;
    boolean mTwoPane = false;
    Fragment mcontent;
    public void onMasterFragmentButtonClicked (View v, int index)
    {
       // Toast.makeText(MasterDetailActivity.this, "MasterDetailActivity is called from Fragment Master Fragment",
       //         Toast.LENGTH_LONG).show();
        Log.d("Normal piece", "Inside button clicked layout to display movie Info");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fT = fm.beginTransaction();
    //    MasterFragment Mf = (MasterFragment)fm.findFragmentById(R.id.masterfragmentlayout);
/*        switch(v.getId()) {
            case R.id.button4:
                break;
            case R.id.button5:
                break;
        }*/
        if (mTwoPane == true) {
         //   Toast.makeText(MasterDetailActivity.this, "Inside tab layout to dipsplay movie Info",
         //           Toast.LENGTH_LONG).show();

            Log.d("Tab piece", "Inside tab layout replace slave framelayout movie Info");
            fT.replace(R.id.masterslave, MovieFragment.newInstance(movieData.getItem(index)));
           // fT.addToBackStack(null);
            fT.commit();
        }
        else {
            Log.d("Normal piece", "Inside tab layout to replace msterdetailframelayout movie Info");
            fT.replace(R.id.masterdetailframelayout, MovieFragment.newInstance(movieData.getItem(index)));
            fT.addToBackStack(null);
            fT.commit();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieData = new MovieData();
        setContentView(R.layout.activity_master_detail);
/*        if (savedInstanceState == null) {
            FragmentManager fm =
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.masterdetailframelayout,
                    MasterFragment.newInstance(R.id.masterfragmentlayout));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }*/
        if (savedInstanceState == null)
        {
            mcontent = MasterFragment.newInstance(R.id.masterfragmentlayout);
        }
        /*getSupportFragmentManager().beginTransaction().replace(R.id.masterdetailframelayout,
                mcontent).addToBackStack(null).commit();
*/
        if( findViewById (R.id.masterslave) != null ) {
            mTwoPane = true;
            getSupportFragmentManager().beginTransaction().replace(R.id.masterdetailframelayout,
                    mcontent).commit();
            /*fragmentTransaction.replace(R.id.masterslave,
                    MovieFragment.newInstance(movieData.getItem(0)));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();*/
        }
        else
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.masterdetailframelayout,
                    mcontent).addToBackStack(null).commit();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
       super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mcontent", mcontent);
    }
}
