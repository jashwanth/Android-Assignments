package com.example.mina.tenth;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by kevin on 6/23/2014.
 */
public  class Fragment_Animation extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment_Animation newInstance(int sectionNumber) {
        Fragment_Animation fragment = new Fragment_Animation();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment_Animation() {
    }

    ImageView imageView;
    ViewGroup viewGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_animation_experiment, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        viewGroup = (ViewGroup) rootView.findViewById(R.id.viewgroup);

        Button button1 = (Button) rootView.findViewById(R.id.button1);
        Button button2 = (Button) rootView.findViewById(R.id.button2);
        Button button3 = (Button) rootView.findViewById(R.id.button3);
        Button button4 = (Button) rootView.findViewById(R.id.button4);
        Button button5 = (Button) rootView.findViewById(R.id.button5);
        Button button6 = (Button) rootView.findViewById(R.id.button6);

        button1.setOnClickListener(new MyOnClickListener());
        button2.setOnClickListener(new MyOnClickListener());
        button3.setOnClickListener(new MyOnClickListener());
        button4.setOnClickListener(new MyOnClickListener());
        button5.setOnClickListener(new MyOnClickListener());
        button6.setOnClickListener(new MyOnClickListener());

        return rootView;
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button1:  // move and rotate
                    imageView.animate().setDuration(1000);
                    imageView.animate().x(500).y(800)
                            .rotationYBy(720)
                            .scaleX(0.4F).scaleY(0.4F);
                    break;
                case R.id.button2:  // move back
                    imageView.animate().setDuration(1000)
                            .x(imageView.getLeft())
                            .y(imageView.getTop())
                            .rotationYBy(720)
                            .scaleX(1.0F).scaleY(1.0F);
                    viewGroup.animate().setDuration(1000);
                    viewGroup.animate().x(viewGroup.getLeft()).y(viewGroup.getTop())
                            .rotationYBy(900)
                            .scaleX(1.0F).scaleY(1.0F);
                    break;
                case R.id.button3:  // fade out -- change transparency
                    imageView.animate().setDuration(1000)
                            .alpha(0f);
                    break;
                case R.id.button4:  // fade in
                    imageView.animate().setDuration(1000)
                            .alpha(1f);
                    break;
                case R.id.button5:  // use animator from XML
                    AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(),
                            R.animator.fancy_animation);
                    set.setTarget(imageView);
                    set.start();
                    break;
                case R.id.button6: // view group animation
                    viewGroup.animate().setDuration(1000);
                    viewGroup.animate().x(480).y(920)
                            .rotationYBy(900)
                            .scaleX(1.8F).scaleY(1.8F);
                    break;
                default:
                    break;
            }
        }
    }

}