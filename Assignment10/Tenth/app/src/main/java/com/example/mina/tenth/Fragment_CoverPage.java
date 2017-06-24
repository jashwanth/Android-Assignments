package com.example.mina.tenth;


import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by kevin on 3/27/16.
 */
public class Fragment_CoverPage extends Fragment {
    public static Fragment_CoverPage newInstance(int position) {
        Fragment_CoverPage fragment = new Fragment_CoverPage();
        return fragment;
    }

    public Fragment_CoverPage() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_detail_menu, menu);
        getActivity().getActionBar().setTitle("Demo Page");
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_coverpage, container, false);

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int id = view.getId();
                Intent intent;

                switch (id){
                    case R.id.button1:
                        intent = new Intent(getActivity(), Activity_RemoteControl.class);
                        startActivity(intent);
                        break;
                    case R.id.button2:
                        intent = new Intent(getActivity(), Activity_Drawable.class);
                        startActivity(intent);
                        break;
                    case R.id.button3:
                        intent = new Intent(getActivity(), Activity_DragandDrop.class);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ActivityOptionsCompat options = ActivityOptionsCompat.
                                    makeSceneTransitionAnimation(getActivity(), view, "testAnimation");
                            getActivity().startActivity(intent, options.toBundle());
                        }
                        else {
                            startActivity(intent);
                        }
                        break;
                    case R.id.button4:
                        intent = new Intent(getActivity(), Activity_Animation.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        };

        ((Button) rootView.findViewById(R.id.button1)).setOnClickListener(onClickListener);
        ((Button) rootView.findViewById(R.id.button2)).setOnClickListener(onClickListener);
        ((Button) rootView.findViewById(R.id.button3)).setOnClickListener(onClickListener);
        ((Button) rootView.findViewById(R.id.button4)).setOnClickListener(onClickListener);

        return rootView;
    }
}

