package com.example.lifei.ace.utils;

import android.os.Environment;

import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class DataAdapter {
    //数据来源，从Download里面传入jsonStr
    private CategorySeries categoriesSeries = null;
    //将json字符串，转换成BarChart用的CategorySeries
    private final static String DIR_PATH = "/mnt/sdcard/Android/data/ACE/";

    public CategorySeries toBarChartData(String jsonStr) {
//        String data = "{'mTitle':'2016年10月25号','mCategories':['Tom','Bob','Lee','Sam','Jill'],'mValue':[330.0,112.0,99.0,300.0,440.0]}";
        String data = read("barChart.json");
        if (data == null) {
            return null;
        }
        JSONObject category;
        String mTitle;
        JSONArray mCategories;
        JSONArray mValues;
        try {
            category = new JSONObject(data);
            mTitle = category.getString("mTitle");
            mCategories = category.getJSONArray("mCategories");
            mValues = category.getJSONArray("mValue");
            categoriesSeries = new CategorySeries(mTitle); //设置图例
            for (int i = 0; i < mCategories.length(); i++) {
                categoriesSeries.add(mCategories.getString(i), mValues.getDouble(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categoriesSeries;

    }

    public XYMultipleSeriesDataset toScatterChart(String jsonStr) {
//        String data = "[{'title':'series 1','XValues':[1.0,2.3,4.2,5.5],'YValues':[2.3,1.2,4.4,8.6]}," +
//                "{'title':'series 2','XValues':[2.1,1.4,3.3,6.2],'YValues':[9.1,2.2,4.1,6.2]}," +
//                "{'title':'series 3','XValues':[11.3,4.8,6.9,9.9],'YValues':[11.2,12.4,5.6,2.8]}," +
//                "{'title':'series 4','XValues':[5.4,3.9,13.1,11.9],'YValues':[2.5,11.8,2.3,1.2]}]";
        String data = read("scatterChart.json");
        if (data == null) {
            return null;
        }
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        JSONArray ja;
        JSONObject jo;
        int length;
        String title;
        try {
            ja = new JSONArray(data);
            length = ja.length();
            for (int i = 0; i < length; i++) {
                jo = ja.getJSONObject(i); //获取第i个json对象（线条）
                title = jo.getString("title");
                XYSeries line = new XYSeries(title, 0);
                JSONArray xValue = jo.getJSONArray("XValues");
                JSONArray yValue = jo.getJSONArray("YValues");
                int count = xValue.length();
                for (int k = 0; k < count; k++) {
                    line.add(xValue.getDouble(k), yValue.getDouble(k));
                    ;
                }
                dataset.addSeries(line);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    private String read(String FILE_NAME) {
        try {
            File[] currentFiles;
            //如果手机插入了SD卡，并且程序有访问SD卡的权限
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                File sdCardDir = Environment.getExternalStorageDirectory();
                File dir = new File(DIR_PATH);
                if (!dir.exists()) {
                    //若不存在，创建目录，可以在应用启动的时候创建
                    dir.mkdirs();
                    return null;
                } else if ((currentFiles = dir.listFiles()) == null || (currentFiles = dir.listFiles()).length == 0) {
                    return null;
                } else {
                    currentFiles = dir.listFiles();
                    for (int i = 0; i < currentFiles.length; i++) {
                        //找到该文件
                        if (currentFiles[i].getName().equals(FILE_NAME)) {
                            FileInputStream fis = new FileInputStream(DIR_PATH + FILE_NAME);
                            //将指定输入流包装成BufferedReader
                            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                            StringBuilder sb = new StringBuilder("");
                            String line = null;
                            //循环读取文件内容
                            while ((line = br.readLine()) != null) {
                                sb.append(line);
                            }
                            //关闭资源
                            br.close();
                            return sb.toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void write(String content, String FILE_NAME) {
//        FileOutputStream fos =
        try {
            //如果手机插入了SD卡，并且引用程序有访问SD卡的权限
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //获取SD卡的目录
//                File sdCardDir = Environment.getExternalStorageDirectory();
                File dir_name = new File(DIR_PATH);
                if (!dir_name.exists()) {
                    //若不存在，创建目录，可以在应用启动的时候创建
                    dir_name.mkdirs();
                }
                File targetFile = new File(DIR_PATH + FILE_NAME);
                //以指定文件创建RandomAccessFile对象
                RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
                //将文件记录指针移动到最后
                raf.seek(targetFile.length());
                //输出文件内容
                raf.write(content.getBytes());
                //关闭RandomAccessFile
                raf.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
