package com.example.lifei.ace.chart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import org.achartengine.chart.BarChart.Type;
import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

/**
 * Created by lifei on 2016/10/25.
 */
public class BarChart implements AChartAbstract {
    @Override
    public Intent getIntent(Context context){
        //数据源和样式在下面设置
        return ChartFactory.getBarChartIntent(context,getDataSet(),getRender(), Type.STACKED,"什么鬼");
    }
    //不想跳转，返回一个视图
    public View getView(Context context){
        return  ChartFactory.getBarChartView(context,getDataSet(),getRender(), Type.STACKED);
    }
    //自己重写一个getView，用来获取外部的dataset
    public View getView(Context context,CategorySeries dataset){
        //加工，以后传数据按这个格式来
        XYMultipleSeriesRenderer renderer = getRender();

        //给X轴每个条形图下设置label
        for(int i=0;i<dataset.getItemCount();i++){
            renderer.addXTextLabel(i+1,dataset.getCategory(i));
        }
        XYMultipleSeriesDataset dataset1 = new XYMultipleSeriesDataset();
        dataset1.addSeries(dataset.toXYSeries()); //只留下mValue，去掉mCategories
        double maxy = dataset1.getSeriesAt(0).getMaxY();
        // 设置屏幕能显示的Y轴的最大数字
        renderer.setYAxisMax((maxy/100)*200);//530,Y最大值取500*2
        return  ChartFactory.getBarChartView(context,getDataSet(dataset),renderer, Type.STACKED);
    }

//    //构造数据源
    public XYMultipleSeriesDataset getDataSet(CategorySeries series){
        //设置数据源，本质是一个List<XYSeriers>，XYSeriers
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series.toXYSeries()); //只留下mValue，去掉mCategories
        return dataset;
    }
    //构造数据源
    public XYMultipleSeriesDataset getDataSet(){
        //设置数据源，本质是一个List<XYSeriers>，XYSeriers
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        return dataset;
    }
    //设置样式
    public XYMultipleSeriesRenderer getRender(){
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setChartTitle("图表名");
        renderer.setXTitle("设置X轴名");
        renderer.setYTitle("设置Y轴名");
        renderer.setAxesColor(Color.BLACK);
        renderer.setLabelsColor(Color.BLUE);
        //设置网格
       // renderer.setShowGrid(true);
//        renderer.setShowGridX(true);
        renderer.setShowCustomTextGrid(true);
        renderer.setGridColor(Color.rgb(240,240,240));
//        renderer.setMarginsColor(Color.TRANSPARENT);//图表的外边界填充颜色
        renderer.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));//图表的外边界填充颜色

        // 设置屏幕能显示的X轴的最小数字和最大数字
        renderer.setXAxisMin(0);
        renderer.setXAxisMax(11);
        // 设置屏幕能显示的Y轴的最小数字和最大数字
        renderer.setYAxisMin(0);
        renderer.setYAxisMax(1000);
        //给X轴每个条形图下设置label

//        renderer.setXLabelsAngle(90);

        //renderer.setZoomButtonsVisible(true);
        // 设置渲染器允许放大缩小,上面设置了按钮操控不可见，不知道触屏能不能
        renderer.setZoomEnabled(true);
        // 消除锯齿
        renderer.setAntialiasing(true);
        // 设置背景颜色
        renderer.setApplyBackgroundColor(true);
        //图表的背景色
        renderer.setBackgroundColor(Color.TRANSPARENT);
        // 设置每条柱子的颜色
        SimpleSeriesRenderer sr = new SimpleSeriesRenderer();
        sr.setColor(Color.rgb(164,201,95));
        renderer.addSeriesRenderer(sr);
        // 设置每个柱子上是否显示数值
        renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
        // X轴的坐标个数  (这样不显示横坐标)
        renderer.setXLabels(10);
        // Y轴的坐标个数
        renderer.setYLabels(10);
        renderer.setXLabelsColor(Color.RED);
        //X标签角度
//        renderer.setXLabelsAngle(-90);
        renderer.setYLabelsColor(0,Color.BLACK);
        // 刻度线与X轴坐标文字左侧对齐
        renderer.setXLabelsAlign(Paint.Align.LEFT);
        // Y轴与Y轴坐标文字左对齐
        renderer.setYLabelsAlign(Paint.Align.LEFT);
        // 允许左右拖动,但不允许上下拖动.
        renderer.setPanEnabled(true, false);
        // 柱子间间距
        renderer.setBarSpacing(5.7f);
        //设置柱子的宽度
        renderer.setBarWidth(42f);
        // 设置X,Y轴单位的字体大小
        renderer.setAxisTitleTextSize(20);
        return renderer;
    }
}
