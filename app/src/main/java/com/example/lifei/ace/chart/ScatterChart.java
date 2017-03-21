/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lifei.ace.chart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.example.lifei.ace.utils.DataAdapter;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Scatter demo chart.
 */
public class ScatterChart extends AbstractDemoChart {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Scatter chart";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "Randomly generated values for the scatter chart";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Intent execute(Context context) {
    return ChartFactory.getScatterChartIntent(context, getDataSet(), getRender());
  }
  public View getView(Context context){
    return  ChartFactory.getScatterChartView(context,getDataSet(),getRender());
  }

  public XYMultipleSeriesDataset getDataSet(){
    DataAdapter dataAdapter = new DataAdapter();
    return dataAdapter.toScatterChart("s");
//    String[] titles = new String[] { "Series 1", "Series 2", "Series 3", "Series 4", "Series 5" };
//    List<double[]> x = new ArrayList<double[]>();
//    List<double[]> values = new ArrayList<double[]>();
//    int count = 20;
//    int length = titles.length;
//    Random r = new Random();
//    for (int i = 0; i < length; i++) {
//      double[] xValues = new double[count];
//      double[] yValues = new double[count];
//      for (int k = 0; k < count; k++) {
////        xValues[k] = 5;
//        xValues[k] = k + r.nextInt() % 10;
//        yValues[k] = k * 2 + r.nextInt() % 10;
//      }
//      x.add(xValues);
//      values.add(yValues);
//    }
//    return buildDataset(titles,x,values);
  }
  public XYMultipleSeriesRenderer getRender(){
    int[] colors = new int[] { Color.BLUE, Color.CYAN, Color.MAGENTA, Color.LTGRAY};
    PointStyle[] styles = new PointStyle[] { PointStyle.X, PointStyle.DIAMOND, PointStyle.TRIANGLE,
            PointStyle.SQUARE};
    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
    setChartSettings(renderer, "Scatter chart", "X", "Y", -10, 30, -10, 51, Color.GRAY,
            Color.LTGRAY);
    renderer.setXLabels(10);
    renderer.setYLabels(10);
    renderer.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));//图表的外边界填充颜色
    renderer.setAxesColor(Color.BLACK);
    renderer.setLabelsColor(Color.BLUE);
    renderer.setXLabelsColor(Color.RED);
    renderer.setYLabelsColor(0,Color.BLACK);
    int length = renderer.getSeriesRendererCount();
    for (int i = 0; i < length; i++) {
      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
    }
    return renderer;
  }

}
