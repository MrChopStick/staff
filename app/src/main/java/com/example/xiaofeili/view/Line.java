package com.example.xiaofeili.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.xiaofeili.staff.R;

/**
 * Created by XiaofeiLi on 2016/10/11.
 */
public class Line extends LinearLayout
{
    public Line(Context context)
    {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.line,this);
    }
}
