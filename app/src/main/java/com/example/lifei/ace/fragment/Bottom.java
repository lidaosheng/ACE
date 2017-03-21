package com.example.lifei.ace.fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lifei.ace.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Bottom extends Fragment  implements View.OnClickListener{
    private View view;
    public LinearLayout b1;
    public LinearLayout b2;
    public LinearLayout b3;
    public LinearLayout b4;


    public Bottom() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bottom,container,false);
        b1 = (LinearLayout) view.findViewById(R.id.bt1);
        b2 = (LinearLayout) view.findViewById(R.id.bt2);
        b3 = (LinearLayout) view.findViewById(R.id.bt3);
        b4 = (LinearLayout) view.findViewById(R.id.bt4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                Toast.makeText(getActivity().getApplicationContext(),""+view.getId(),Toast.LENGTH_LONG).show();

                switch (view.getId())
                {
                    case R.id.bt1:
                        Top2 top2 = new Top2();
                        DownloadFragment df = new DownloadFragment();
                        // 使用当前Fragment的布局替代id_content的控件
                        transaction.replace(R.id.content, df);
                        transaction.replace(R.id.page_title,top2);
                        break;
                }
                // transaction.addToBackStack();
                // 事务提交
                transaction.commit();

            }
        });
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v)
    {
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        Toast.makeText(getActivity().getApplicationContext(),""+v.getId(),Toast.LENGTH_LONG).show();

        switch (v.getId())
        {
            case R.id.bt1:
                Top2 top2 = new Top2();
                DownloadFragment df = new DownloadFragment();
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.content, df);
                transaction.replace(R.id.page_title,top2);
                break;
        }
        // transaction.addToBackStack();
        // 事务提交
        transaction.commit();
    }

}
