package com.example.lifei.ace;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import com.example.lifei.ace.fragment.DownloadFragment;
import com.example.lifei.ace.fragment.Top2;

public class MainActivity extends AppCompatActivity{

    private DownloadFragment df;
    private Top2 top2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 填充标题栏
        setContentView(R.layout.activity_main);
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        setDefaultFragment();
    }

    //设置默认fragment
    private void setDefaultFragment()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        top2 = new Top2();
        df = new DownloadFragment();
        transaction.replace(R.id.content, df);
        transaction.replace(R.id.page_title,top2);
        transaction.commit();
    }

}
