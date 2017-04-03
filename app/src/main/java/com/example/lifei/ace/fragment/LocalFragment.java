package com.example.lifei.ace.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifei.ace.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalFragment extends Fragment {
    private View view;
    private ListView list;
    private TextView msg;
    private int file_icon = R.mipmap.ic_insert_drive_file_black_24dp;
    private final static String DIR_PATH = "/mnt/sdcard/Android/data/ACE";

    public LocalFragment() {}

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_local, container, false);
        list = (ListView)view.findViewById(R.id.file_list);
        msg = (TextView)view.findViewById(R.id.msg);
        ImageButton test = (ImageButton)view.findViewById(R.id.testImage);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(),"图片呗点了",Toast.LENGTH_LONG).show();
                FragmentManager fm = getFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                ChooseChart chooseChart = new ChooseChart();
                Top top = new Top();
                transaction.add(R.id.content,chooseChart);
                transaction.add(R.id.page_title,top);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });





        //创建一个list集合，元素是Map,用循环，将一个list项封装到一个Map中
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        String[] filelist = getFileList();
        if(filelist==null||filelist.length==0){
            msg.setText("文件列表为空..");
        }else{
            msg.setText("文件列表");
            for(int i=0;i<filelist.length;i++){
                Map<String,Object> listItem = new HashMap<String,Object>();
//                listItem.put("file_icon",file_icon);
                listItem.put("personName",filelist[i]);
                listItem.put("desc","2017-04-02");
                listItems.add(listItem);
            }
            //创建一个SimpleAdapter
            SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),listItems,R.layout.file_item,new String[]{"personName","desc"},new int[]{R.id.file_name,R.id.file_date});
            list.setAdapter(simpleAdapter);
        }
        //点击动作
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DrawChart dc = new DrawChart();
                Bundle bd = new Bundle();
                int chart_type = 0;
                View item = list.getChildAt(i);
                bd.putInt("chart_type",chart_type);
                dc.setArguments(bd);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.add(R.id.content,dc);
                transaction.addToBackStack(null);
                transaction.commit();
               // Toast.makeText(getActivity().getApplicationContext(),"点击了第"+i+"个"+item.findViewById(R.id.file_name),Toast.LENGTH_LONG).show();
            }
        });
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    //获取本地文件列表
    public String[] getFileList(){
        //获取SD卡目录
        File dir = new File(DIR_PATH);
        if(!dir.exists()){
            dir.mkdir();
        }
        File[] currentFiles = dir.listFiles();
        String[] filename = new String[currentFiles.length];
        for(int i=0;i<currentFiles.length;i++){
            filename[i] = currentFiles[i].getName();
        }
        return filename;
    }

    //点击事件

}
