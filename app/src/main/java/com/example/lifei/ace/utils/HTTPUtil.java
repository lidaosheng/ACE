package com.example.lifei.ace.utils;

import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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
 * Created by lifei on 2016/12/26.
 */
public class HTTPUtil{
    private HttpURLConnection conn;
    private URL url;
    private View view;
    private Button bt;
    private InputStream is;
    private OutputStream os;
    public static final String urlData = "http://123.206.13.40/Test/HelloWorld";

    public String postRequest() throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try{
                    url = new URL(urlData);
                    try{
                        //初始化连接
                        init();
                        //获取服务器数据
                        return read();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }catch (MalformedURLException e){
                    System.out.println("第二层的事情");
                }finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
                return "你的网络没准备好哦！";
            }
        });
        new Thread(task).start();
        System.out.println("线程启动了");
        //阻塞，直到结果返回才结束
        return task.get();
//        try{
//            url = new URL(urlData);
//            try{
//                //初始化连接
//                init();
//                //获取服务器数据
//                return read();
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }catch (MalformedURLException e){
//            System.out.println("第二层的事情");
//        }finally {
//            if (conn != null) {
//                conn.disconnect();
//            }
//        }
//        return "nothing found";
    }

    public void init()throws IOException{
        //打开服务器
        conn = (HttpURLConnection) url.openConnection();
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
    }

    public String read()throws IOException{
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
    }

    public void write()throws IOException{
        /**写入参数**/
        os=conn.getOutputStream();
        //封装写给服务器的数据（这里是要传递的参数）
        DataOutputStream dos=new DataOutputStream(os);
        //写方法：name是key值不能变，编码方式使用UTF-8可以用中文
        /**测试，以后要换成登录验证，用户名和密码**/
        //dos.writeBytes("name="+ URLEncoder.encode(et.getText().toString(), "UTF-8"));
        dos.writeBytes("name=lifei");
        //关闭外包装流
        dos.close();
    }
//    //创建HTTPClient对象
//    public static HttpClient httpClient = new DefaultHttpClient();
//    public static final String BASE_URL = "http://192.168.1.88:8888/ss/lifei.jsp";
//
//    public static String getRequest(final String url) throws Exception{
//        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                //call参考run
//                HttpGet get = new HttpGet(url);
//                //发送get请求
//                HttpResponse httpResponse = httpClient.execute(get);
//                //如果服务器成功的返回响应
//                if(httpResponse.getStatusLine().getStatusCode()==200){
//                    //获取服务器响应字符串
//                    String result = EntityUtils.toString(httpResponse.getEntity());
//                    return result;
//                }
//
//                return null;
//            }
//        });
//        new Thread(task).start();
//        //阻塞，直到结果返回才结束
//        return task.get();
//    }
}
