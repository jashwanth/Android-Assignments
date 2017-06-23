package com.example.jashwanthreddy.assignment4;

/**
 * Created by jashwanthreddy on 2/14/17.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;

public class FrontPageFragment extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";
    Button btn1;
    Button btn2;
    public interface CustomOnClickListener {
        public void onClicked ( View v );
    }
    private CustomOnClickListener customOnClickListener ;
    public static FrontPageFragment newInstance(int sectionNumber) {
        FrontPageFragment fragment = new FrontPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public FrontPageFragment() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.front_page_fragment, container, false);
        btn1 = (Button) rootView.findViewById(R.id.button);
        btn2 = (Button) rootView.findViewById(R.id.button2);
        customOnClickListener = (CustomOnClickListener)rootView.getContext();
        btn1.setOnClickListener(new View.OnClickListener () {
            public void onClick (View v) {
                buttonClicked(v);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener () {
            public void onClick (View v) {
                buttonClicked(v);
            }
        });
        return rootView;
    }
    public void buttonClicked (View v) {
        customOnClickListener.onClicked (v);
    }
}
