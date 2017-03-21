package com.example.lifei.ace.fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lifei.ace.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Top extends Fragment {
    private View view;
    private ImageButton imageButton;
    private TextView textView;

    public Top() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.top,container,false);

        imageButton = (ImageButton)view.findViewById(R.id.backward);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
            }
        });
        textView = (TextView)view.findViewById(R.id.top_title);
        return view;
    }
    public void changeText(String title){
        TextView textView = (TextView)getView().findViewById(R.id.top_title);
        textView.setText("呀呀呀");
    }

}
