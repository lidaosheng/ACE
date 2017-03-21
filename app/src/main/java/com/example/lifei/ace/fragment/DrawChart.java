package com.example.lifei.ace.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.lifei.ace.R;
import com.example.lifei.ace.chart.AverageTemperatureChart;
import com.example.lifei.ace.chart.BarChart;
import com.example.lifei.ace.chart.ScatterChart;
import com.example.lifei.ace.utils.DataAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrawChart extends Fragment {
    private View view;
    private View thechart;
    private int chart_type = 0;
    private FrameLayout chart;

    public DrawChart() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        chart_type = getArguments().getInt("chart_type");
        view = inflater.inflate(R.layout.drawchart,container,false);
        chart = (FrameLayout)view.findViewById(R.id.chart);
        switch (chart_type){
            case 1:
                BarChart barChart = new BarChart();
                thechart = barChart.getView(getActivity().getBaseContext(), new DataAdapter().toBarChartData("ss"));
                chart.addView(thechart);
                break;
            case 2:
                AverageTemperatureChart atChart = new AverageTemperatureChart();
                thechart = atChart.getView(getActivity().getBaseContext());
                chart.addView(thechart);
                break;
            case 3:
                ScatterChart scatterChart = new ScatterChart();
                thechart = scatterChart.getView(getActivity().getBaseContext());
                chart.addView(thechart);
                break;
            default:
                Toast.makeText(getActivity().getApplicationContext(),"敬请期待!",Toast.LENGTH_LONG).show();
                break;
        }
        //chart.addView(thechart);
        return view;
    }

}
