package com.example.lifei.ace.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifei.ace.R;
import com.example.lifei.ace.utils.HTTPUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    private HttpURLConnection conn;
    private URL url;
    private View view;
    private Button bt;
    private InputStream is;
    private OutputStream os;
    //写完后，将数据发送服务器
    private EditText et;
    private TextView tv;
    public SecondFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab02,container,false);
        bt = (Button)view.findViewById(R.id.tab02_bt);
        tv = (TextView)view.findViewById(R.id.tab02_tv);
        //绑定事件
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取服务器上的数据
                String data = null;
                try {
                    data = new HTTPUtil().postRequest();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //将上面获取的数据显示
                tv.setText(data);
            }

        });
        return view;
    }
}
