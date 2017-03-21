package com.example.lifei.ace.fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lifei.ace.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Top2 extends Fragment {
    private View view;
    private Button bendi;
    private Button yunduan;
    public Top2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.top2,container,false);
        bendi = (Button)view.findViewById(R.id.bendi);
        yunduan = (Button)view.findViewById(R.id.yunduan);
        //添加事件,操控activity中的内容模块
        yunduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "选中了云端",
                        Toast.LENGTH_SHORT).show();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                DownloadFragment df = new DownloadFragment();
                transaction.replace(R.id.content, df);
                transaction.commit();
            }
        });
        bendi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "选中了本地",
                        Toast.LENGTH_SHORT).show();
                FragmentManager fm = getFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                LocalFragment localFragment = new LocalFragment();
                transaction.replace(R.id.content,localFragment);
                transaction.commit();
            }
        });
        return view;
    }

}
