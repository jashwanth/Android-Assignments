package com.example.jashwanthreddy.assignment3;

/**
 * Created by jashwanthreddy on 2/9/17.
 */
import android.os.Bundle;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

public class AboutMeFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static AboutMeFragment newInstance(int sectionNumber)
    {
        AboutMeFragment fragment = new AboutMeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public AboutMeFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.aboutmefragment, container, false);
        int option = getArguments().getInt(ARG_SECTION_NUMBER);
        switch (option) {
            /* */
        }
        return rootView;
    }
}
