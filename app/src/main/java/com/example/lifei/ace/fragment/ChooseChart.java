package com.example.lifei.ace.fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.lifei.ace.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseChart extends Fragment {
    private View view;
    private GridView gridView;
    private int[] images = new int[]{
            R.mipmap.bar,R.mipmap.line2,R.mipmap.pen,R.mipmap.pie,
            R.mipmap.area,R.mipmap.egg,R.mipmap.bar3,R.mipmap.bar2,
            R.mipmap.line1,R.mipmap.box
    };
    private String[] types = {"柱状图","折线图","散点图","饼状图","箱图","待定",
            "待定","待定","待定","待定"
    };

    public ChooseChart() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_choose_chart, container, false);
        gridView = (GridView)view.findViewById(R.id.gridview);
        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        for(int i=0;i<images.length;i++){
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("image",images[i]);
            item.put("type",types[i]);
            mapList.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),mapList,R.layout.choosechart_item,
                new String[]{"image","type"},
                new int[]{R.id.imagechoose,R.id.image_text});
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DrawChart dc = new DrawChart();
                Bundle bd = new Bundle();
                int chart_type = 0;
                if(i==0){
                    chart_type = 1;
                }else if(i==1){
                    chart_type = 2;
                }else if(i==2){
                    chart_type = 3;
                }
                bd.putInt("chart_type",chart_type);
                dc.setArguments(bd);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.add(R.id.content,dc);
                transaction.addToBackStack(null);
                transaction.commit();
                Toast.makeText(getActivity().getApplicationContext(),"点击了第"+i+"个",Toast.LENGTH_LONG).show();
            }
        });
        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DrawChart dc = new DrawChart();
                Bundle bd = new Bundle();
                int chart_type = 0;
                if(i==0){
                    chart_type = 1;
                }else if(i==1){
                    chart_type = 2;
                }else if(i==2){
                    chart_type = 3;
                }
                bd.putInt("chart_type",chart_type);
                dc.setArguments(bd);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.add(R.id.content,dc);
                transaction.addToBackStack(null);
                transaction.commit();
                Toast.makeText(getActivity().getApplicationContext(),"点击了第"+i+"个",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

}
