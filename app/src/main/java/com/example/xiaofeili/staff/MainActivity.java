package com.example.xiaofeili.staff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaofeili.Common.Constant;
import com.example.xiaofeili.Task.PlanInfo;
import com.example.xiaofeili.Task.TaskInfo;
import com.example.xiaofeili.user.UserInfo;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements OnClickListener,OnPageChangeListener,AdapterView.OnItemClickListener
{

    // 底部菜单2个Linearlayout
    private LinearLayout ll_task;
    private LinearLayout ll_setting;

    // 底部菜单2个ImageView
    private ImageView iv_task;
    private ImageView iv_setting;

    // 底部菜单2个菜单标题
    private TextView tv_task;
    private TextView tv_setting;

    // 中间内容区域
    private ViewPager viewPager;

    // ViewPager适配器ContentAdapter
    private ContentAdapter adapter;

    private List<View> views;

    //任务list
    private ListView taskList;
    private SimpleAdapter taskListAdpter;
    private TaskInfo taskInfo;
    private PlanInfo planInfo;

    private View taskPage,settingPage;
    private TextView tvOption;
    private PopupWindow popupWindow;
    private View groupList;

    String[] s = {"equipmentName","time","equipmentCategory"};
    int[] ids = {R.id.equipment_name,R.id.time, R.id.equipment_category};

    SimpleAdapter simpleAdapter,simpleAdapter1,simpleAdapter2,simpleAdapter3;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化控件
        initView();
        //initTaskListView();
        // 初始化底部按钮事件
//        initEvent();
//        getTask();
        initTaskListView();
    }

    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //对菜单事件的响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_sync:
                Toast.makeText(this, "同步", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_upload:
                Toast.makeText(this, "上传", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initEvent()
    {
        // 设置按钮监听
        ll_task.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
        tvOption.setOnClickListener(this);
        //设置ViewPager滑动监听
        viewPager.setOnPageChangeListener(this);
    }

    private void initView()
    {
        // 底部菜单2个Linearlayout
        this.ll_task = (LinearLayout) findViewById(R.id.ll_task);
        this.ll_setting = (LinearLayout) findViewById(R.id.ll_setting);

        // 底部菜单2个ImageView
        this.iv_task = (ImageView) findViewById(R.id.iv_task);
        this.iv_setting = (ImageView) findViewById(R.id.iv_setting);

        // 底部菜单2个菜单标题
        this.tv_task = (TextView) findViewById(R.id.tv_task);
        this.tv_setting = (TextView) findViewById(R.id.tv_setting);

        // 中间内容区域ViewPager
        this.viewPager = (ViewPager) findViewById(R.id.vp_content);

        // 适配器
        taskPage= View.inflate(MainActivity.this, R.layout.task_page, null);
        settingPage = View.inflate(MainActivity.this, R.layout.setting_page, null);
        tvOption =(TextView)taskPage.findViewById(R.id.top_option);

        views = new ArrayList<View>();
        views.add(taskPage);
        views.add(settingPage);

        this.adapter = new ContentAdapter(views);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v)
    {
        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton();
        // ImageView和TetxView置为绿色，页面随之跳转
        switch (v.getId())
        {
            case R.id.ll_task:
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_setting:
                viewPager.setCurrentItem(1);
                break;
            case R.id.top_option:
              //  showWinndow(v);
            default:
                break;
        }

    }


    private void restartBotton()
    {
        // ImageView置为灰色
        iv_task.setImageResource(R.drawable.task_normal);
        iv_setting.setImageResource(R.drawable.setting_normal);
        // TextView置为灰色
        tv_task.setTextColor(Color.parseColor("#a9b7b7"));
        tv_setting.setTextColor(Color.parseColor("#a9b7b7"));
    }

    @Override
    public void onPageScrollStateChanged(int arg0)
    {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2)
    {

    }

    @Override
    public void onPageSelected(int arg0)
    {
        restartBotton();
        //当前view被选择的时候,改变底部菜单图片，文字颜色
        switch (arg0)
        {
            case 0:
                iv_task.setImageResource(R.drawable.task_processed);
                tv_task.setTextColor(Color.parseColor("#1da2e3"));
                break;
            case 1:
                iv_setting.setImageResource(R.drawable.setting_processed);
                tv_setting.setTextColor(Color.parseColor("#1da2e3"));
                break;
            default:
                break;
        }
    }

    public  void initTaskListView()
    {
        taskList = (ListView) taskPage.findViewById(R.id.task_list);
        taskList.setOnItemClickListener(this);
      //  HashMap<String,Object> map = null;
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
//        for(int i=0;i<planInfo.getList().size();i++)
//        {
//            map = new HashMap<>();
//            map.put("equipmentName",planInfo.getList().get(i).getPlan_name());
//            map.put("time","开始时间"+planInfo.getList().get(i).getPlan_start_time());
//            map.put("equipmentCategory",planInfo.getList().get(i).getPlan_cycle());
//            list.add(map);
//        }
     //   ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();
        ArrayList<HashMap<String, Object>> list2 = new ArrayList<HashMap<String, Object>>();
        ArrayList<HashMap<String, Object>> list3 = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> map = null;
        for (int i = 1; i <= 10; i++)
        {
            map = new HashMap<String, Object>();
            map.put("equipmentName","服务器");
            map.put("equipmentCategory","日常巡检");
            map.put("time","结束时间");
           // list1.add(map);
            list.add(map);
        }
//
//        map = null;
//        for (int i = 1; i <= 10; i++)
//        {
//            map = new HashMap<String, Object>();
//            map.put("equipmentName","空调");
//            map.put("equipmentCategory","保养计划");
//            list2.add(map);
//            list.add(map);
//        }
//        map = null;
//
//        for (int i = 1; i <= 10; i++)
//        {
//            map = new HashMap<String, Object>();
//            map.put("equipmentName","交换机");
//            map.put("equipmentCategory","故障保修");
//            list3.add(map);
//            list.add(map);
//        }
//
          simpleAdapter = new SimpleAdapter(this, list, R.layout.task_item, s, ids);
//        simpleAdapter1 = new SimpleAdapter(this, list1, R.layout.task_item, s, ids);
//        simpleAdapter2 = new SimpleAdapter(this, list2, R.layout.task_item, s, ids);
//        simpleAdapter3 = new SimpleAdapter(this, list3, R.layout.task_item, s, ids);

        taskList.setAdapter(simpleAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Toast.makeText(MainActivity.this,i+"-"+l,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,DataActivity.class);
        startActivity(intent);
    }

    private void showWinndow(View parent)
    {
        if(popupWindow == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            groupList = layoutInflater.inflate(R.layout.group_list, null);
            WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            popupWindow = new PopupWindow(groupList,  windowManager.getDefaultDisplay().getWidth()/2, (int)(windowManager.getDefaultDisplay().getHeight()/3.3));
        }
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
        int xPos = popupWindow.getWidth()/2-windowManager.getDefaultDisplay().getWidth() /2;
        Log.i("coder", "windowManager.getDefaultDisplay().getWidth()/2:"
                + windowManager.getDefaultDisplay().getWidth());
        //
        Log.i("coder", "popupWindow.getWidth()/2:" + popupWindow.getWidth());

        Log.i("coder", "xPos:" + xPos);
        popupWindow.showAsDropDown(parent, xPos/2, 6);


        TextView groupAll, group1, group2,group3;
        groupAll = (TextView) groupList.findViewById(R.id.groupAll);
        group1 = (TextView) groupList.findViewById(R.id.group1);
        group2 = (TextView) groupList.findViewById(R.id.group2);
        group3 = (TextView) groupList.findViewById(R.id.group3);

        groupAll.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                taskList.setAdapter(simpleAdapter);
                Toast.makeText(MainActivity.this,"你选择了第1组",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        group1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                taskList.setAdapter(simpleAdapter1);
                Toast.makeText(MainActivity.this,"你选择了第2组",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        group2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                taskList.setAdapter(simpleAdapter2);
                Toast.makeText(MainActivity.this,"你选择了第3组",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        group3.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                taskList.setAdapter(simpleAdapter3);
                Toast.makeText(MainActivity.this,"你选择了第4组",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
    }
    private void getTask()
    {
        final Handler handler = new Handler()
        {
            public void handleMessage(Message msg)
            {
                switch (msg.what) {
                    case 1:
                        getPlan();
                    case 2:
                        //Toast.makeText(getApplicationContext(),"登录失败,请检查用户名和密码",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        String address = Constant.BASE_URL+"Task";
        //String address = Constant.BASE_URL+"j_security_check?j_username=12345678&j_password=1234";
        String data = "{\"inspector_id\":\""+LoginActivity.staffId+"\"}";
        HttpUtil.sendHttpPostRequest(address,data, new HttpCallbackListener()
        {
            @Override
            public void onFinish(String response)
            {
                if(response.equals("Post Response: Failed"))
                {
                    Message message = new Message();
                    message.what=2;
                    handler.sendMessage(message);
                }
                else
                {
                    Message message = new Message();
                    message.what = 1;
                    Gson gson = new Gson();
                    handler.sendMessage(message);
                    taskInfo=gson.fromJson(response,TaskInfo.class);
                    System.out.println("Task"+response);
                }

            }
            @Override
            public void onError(Exception e)
            {
                Message message = new Message();
                message.what=2;
                handler.sendMessage(message);
                System.out.println(e);
            }
        });
    }
    private void getPlan()
    {
        final Handler handler = new Handler()
        {
            public void handleMessage(Message msg)
            {
                switch (msg.what) {
                    case 1:
                        initTaskListView();
                        System.out.println("计划的size:"+planInfo.getList().size());
                    case 2:
                       // Toast.makeText(getApplicationContext(),"登录失败,请检查用户名和密码",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        String address = Constant.BASE_URL+"Plan";
        //String address = Constant.BASE_URL+"j_security_check?j_username=12345678&j_password=1234";
        String data = "{\"plan_all\":\"3\"}";
        HttpUtil.sendHttpPostRequest(address, data, new HttpCallbackListener()
        {
            @Override
            public void onFinish(String response)
            {
                if(response.equals("Post Response: Failed"))
                {
                    Message message = new Message();
                    message.what=2;
                    handler.sendMessage(message);
                }
                else
                {
                    Gson gson = new Gson();
                    System.out.println("Plan"+response);
                    planInfo=gson.fromJson(response,PlanInfo.class);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);

                }

            }
            @Override
            public void onError(Exception e)
            {
                Message message = new Message();
                message.what=2;
                handler.sendMessage(message);
                System.out.println(e);
            }
        });
    }
}
