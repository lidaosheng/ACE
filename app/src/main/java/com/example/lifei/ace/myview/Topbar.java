package com.example.lifei.ace.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lifei.ace.R;

/**
 * Created by lifei on 2017/1/4.
 */
public class Topbar extends RelativeLayout{
    private Button leftButton,rightButton;
    private TextView tvTitles;
    //左按钮
    private String leftText;
    private int leftTextColor;
    private Drawable leftBackground;
    //右按钮
    private String rightText;
    private int rightTextColor;
    private Drawable rightBackground;
    //中间标题
    private String titleText;
    private int titleTextColor;
    private float titleTextSize;
    //控件之间位置
    private LayoutParams leftParams,rightParams,titleParams;

    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取属性配置文件(当调用该标签时，才会获取)中的值，并赋给自定义控件View(这样能通过你写的标签，属性获得值)
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        //左按钮属性获取
        leftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor,0);
        leftBackground = ta.getDrawable(R.styleable.Topbar_leftBackground);
        leftText = ta.getString(R.styleable.Topbar_leftText);
        //右按钮属性，从配置文件中获取
        rightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor,0);
        rightBackground = ta.getDrawable(R.styleable.Topbar_rightBackground);
        rightText = ta.getString(R.styleable.Topbar_rightText);
        //中间标题块，属性获取
        titleText = ta.getString(R.styleable.Topbar_title);
        titleTextColor = ta.getColor(R.styleable.Topbar_titleTextColor,0);
        titleTextSize = ta.getDimension(R.styleable.Topbar_titleTextSize,0);
        //回收TypedArray，其一：节省资源，其二：避免因为缓存造成的错误
        ta.recycle();

        //实例化组件
        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitles = new TextView(context);
        //将上面获取的属性赋值给组件
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);
        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);
        tvTitles.setTextColor(titleTextColor);
        tvTitles.setTextSize(titleTextSize);
        tvTitles.setText(titleText);
        tvTitles.setGravity(Gravity.CENTER);

        setBackgroundColor(0xFFF59563);
        //控件样式属性都定义好了，下面将这些控件布置好位置
        leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(leftButton,leftParams);

        rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightButton,rightParams);

        titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        titleParams.addRule(CENTER_IN_PARENT);
        addView(tvTitles,titleParams);

    }
}
//本质上，xml文件的工作原理，还是作为控件View的外部配置文件，通过函数，将参数传入
//该类：1.将属性获取出来
//     2.将属性赋值给控件中的组件
//     3.设置一个样式，由于本类是RelativeLayout的子类，直接add 组件以及捆绑的样式