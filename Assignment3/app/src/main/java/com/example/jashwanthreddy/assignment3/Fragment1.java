package com.example.jashwanthreddy.assignment3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;

/**
 * Created by jashwanthreddy on 2/9/17.
 */

public class Fragment1 extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";
    Button btn1;
    Button btn2;
    Button btn3;
    public interface CustomOnClickListener {
        public void onClicked ( View v );
    }
    private CustomOnClickListener customOnClickListener ;
    public static Fragment1 newInstance(int sectionNumber)
    {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public Fragment1()
    {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.fragment1, container, false);
        btn1 = (Button)rootView.findViewById(R.id.button);
        btn2 = (Button)rootView.findViewById(R.id.button2);
        btn3 = (Button)rootView.findViewById(R.id.button3);
        customOnClickListener = (CustomOnClickListener)rootView.getContext();
        btn1.setOnClickListener(new View.OnClickListener () {
                           public void onClick (View v) {
                                buttonClicked(v);
                           }
                       });
       /*  this code was written to test the moviefragment loading happens properly */
        btn2.setOnClickListener(new View.OnClickListener () {
            public void onClick (View v) {
                buttonClicked(v);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });
        int option = getArguments().getInt(ARG_SECTION_NUMBER);
        switch (option) {
            /* */
        }
        return rootView;
    }
    public void buttonClicked ( View v ) {
        customOnClickListener.onClicked (v);
    }

}
