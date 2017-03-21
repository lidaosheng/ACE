package com.example.lifei.ace.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lifei.ace.R;
import com.example.lifei.ace.chart.AverageTemperatureChart;
import com.example.lifei.ace.chart.BarChart;
import com.example.lifei.ace.chart.ScatterChart;
import com.example.lifei.ace.utils.DataAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class BarChartFragment extends Fragment {

    public BarChartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final BarChart barChart = new BarChart();

        View view = inflater.inflate(R.layout.tab01, container, false);
        final LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.barchart1);
        final View barChartView = barChart.getView(getActivity().getBaseContext(), new DataAdapter().toBarChartData("ss"));
        linearLayout.addView(barChartView);
        //-----------------------------------------------



        //测试动态改变图像
        Button reflash = (Button)view.findViewById(R.id.reflash);
        reflash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScatterChart scatterChart = new ScatterChart();
                View scatterChartView = scatterChart.getView(getActivity().getBaseContext());
                linearLayout.removeViewAt(0);//???
                linearLayout.addView(scatterChartView);

            }
        });


        Button b1 = (Button)view.findViewById(R.id.getData);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AverageTemperatureChart atChart = new AverageTemperatureChart();
                View atChartView = atChart.getView(getActivity().getBaseContext());
                linearLayout.removeView(barChartView);
                linearLayout.addView(atChartView);
            }
        });
        return view;
    }

}
