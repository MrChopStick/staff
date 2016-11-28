package com.example.xiaofeili.staff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.xiaofeili.view.ElectricCurrent;
import com.example.xiaofeili.view.Line;
import com.example.xiaofeili.view.StandardOption;

//具体的巡查内容
public class DataActivity extends AppCompatActivity
{
    private View nextPage, lastPage;
    private LinearLayout dataLayout;
    private ScrollView dataScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        nextPage = (View) findViewById(R.id.next_page);
        lastPage = (View) findViewById(R.id.last_page);
        dataLayout = (LinearLayout) findViewById(R.id.data_layout);
        dataScrollView = (ScrollView) findViewById(R.id.dataScrollView);
        nextPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                StandardOption standardOption = new StandardOption(MyApplication.getContext());
                Line line = new Line(MyApplication.getContext());
                dataLayout.addView(line);
                dataLayout.addView(standardOption);
            }
        });
        lastPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ElectricCurrent electricCurrent = new ElectricCurrent(MyApplication.getContext());
                Line line = new Line(MyApplication.getContext());
                dataLayout.addView(line);
                dataLayout.addView(electricCurrent);
            }
        });


    }
}
