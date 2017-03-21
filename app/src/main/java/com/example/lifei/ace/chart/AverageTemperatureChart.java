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

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.*;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.View;

/**
 * Average temperature demo chart.
 */
public class AverageTemperatureChart extends AbstractDemoChart {

  public Intent getIntent(Context context){
    //数据源和样式在下面设置
    return ChartFactory.getLineChartIntent(context, getDataSet(), getRender(),
            "Average temperature");
  }
//不想跳转，返回一个视图
  public View getView(Context context){
    return  ChartFactory.getLineChartView(context,getDataSet(),getRender());
  }



  //构建数据集
  public XYMultipleSeriesDataset getDataSet(){
    String[] titles = new String[] { "Crete", "Corfu", "Thassos", "Skiathos" };
    //list的节点是 double数组
    List<double[]> x = new ArrayList<double[]>();
    //根据标题的个数，产生相应个数的double数组，作为各自的x坐标
    for (int i = 0; i < titles.length; i++) {
      x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
    }
    //录入四条线的y坐标
    List<double[]> values = new ArrayList<double[]>();
    values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2,
            13.9 });
    values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
    values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
    values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });
    //构建数据集
    XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
    XYSeries series = dataset.getSeriesAt(0);
    //在点(6,30)处添加注释信息
    series.addAnnotation("Vacation", 6, 30);
    return dataset;
  }
  //构建渲染器
  public XYMultipleSeriesRenderer getRender(){
    //颜色集
    int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };
    //点的样式集
    PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND,
            PointStyle.TRIANGLE, PointStyle.SQUARE };
    //创建渲染器实例
    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
    //这个是什么呢？--------------------------------------------------------------------------------
    int length = renderer.getSeriesRendererCount();
    for (int i = 0; i < length; i++) {
      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
    }
    //--------------------------------------------------------------------------------------------
    setChartSettings(renderer, "Average temperature", "Month", "Temperature", 0.5, 12.5, -10, 40,
            Color.BLACK, Color.BLACK);
    renderer.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));//图表的外边界填充颜色
    renderer.setXLabels(12);
    renderer.setYLabels(10);
    renderer.setXLabelsColor(Color.BLACK);
    renderer.setYLabelsColor(0,Color.BLACK);
    renderer.setShowGrid(true);
    renderer.setXLabelsAlign(Align.RIGHT);
    renderer.setYLabelsAlign(Align.RIGHT);
    renderer.setZoomButtonsVisible(true);
    renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
    renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });
    return renderer;
  }


}
