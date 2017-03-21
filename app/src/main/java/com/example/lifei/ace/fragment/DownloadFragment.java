package com.example.lifei.ace.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.lifei.ace.R;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {
    private Button download;
    private TextView showData;
    private InputStream is;
    private ProgressBar progressBar; //进度条
    public static final String urlData = "http://123.206.13.40/Test/HelloWorld";

    public DownloadFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download,container,false);
        progressBar = (ProgressBar)view.findViewById(R.id.progress);
        download = (Button)view.findViewById(R.id.download);
        showData = (TextView)view.findViewById(R.id.showData);
        //给下载按钮添加下载事件
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyAsyncTask().execute(urlData);
            }
        });
        return view;
    }
    //一个内部类，继承AsyncTask
    class MyAsyncTask extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE); //设置进度条可见

        }
        @Override
        protected String doInBackground(String... params){
            //获取传进来的参数，execute中传的
            String webpath = params[0];
            HttpURLConnection conn = null;
            URL url;
            try {
                url = new URL(webpath);
                conn = (HttpURLConnection)url.openConnection();
                //设置输入输出流(url连接可以输入输出),即设置从conn输入和输出
                conn.setDoOutput(true);
                conn.setDoInput(true);
                //设置请求方式为POST
                conn.setRequestMethod("POST");
                //设置缓存为false
                conn.setUseCaches(false);
                //设定传送的内容是可序列化的java对象
                //如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException
                conn.setRequestProperty("Content-type", "application/x-java-serialized-object");
                //连接的同时，将请求信息发送出去
                conn.connect();

                /**读服务器数据**/
                is=conn.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line=null;
                StringBuffer sb=new StringBuffer();
                while((line=br.readLine())!=null){
                    sb.append(line);
                }
                br.close();
                return sb.toString();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return "获取数据失败，请检查网络连接...";

        }
        @Override
        protected void onPostExecute(String s){
            //这里的参数是
            //隐藏progressBar
//            progressBar.setVisibility(View.GONE);
            showData.setText(s);

        }
    }

}
