package com.example.xiaofeili.staff;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiaofeili.Common.Constant;
import com.example.xiaofeili.user.UserID;
import com.example.xiaofeili.user.UserInfo;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity
{

    private Button bt_login;
    private EditText et_username,et_password;
    private UserID userID=new UserID();
    private UserInfo userInfo;
    public static String staffId;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init_view();
        init_event();
    }
    private void init_view()
    {
        bt_login=(Button)findViewById(R.id.login);
        et_username=(EditText)findViewById(R.id.user_name);
        et_password=(EditText)findViewById(R.id.user_pwd);
    }
    private void init_event()
    {
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
//        bt_login.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                if(get_user()==false)
//                    return;
//                if(check_user()==false)
//                    return;
//                final Handler handler = new Handler()
//                {
//                    public void handleMessage(Message msg)
//                    {
//                        switch (msg.what) {
//                            case 1:
//                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                                startActivity(intent);
//                                break;
//                            case 2:
//                                Toast.makeText(getApplicationContext(),"登录失败,请检查用户名和密码",Toast.LENGTH_SHORT).show();
//                                break;
//                        }
//                    }
//                };
//                Gson gson = new Gson();
//                String data=gson.toJson(userID);
//                System.out.println("请求参数:"+data);
//                String address = Constant.BASE_URL+"Login";
//                //String address = Constant.BASE_URL+"j_security_check?j_username=12345678&j_password=1234";
//                HttpUtil.sendHttpPostRequest(address,data, new HttpCallbackListener()
//                {
//                    @Override
//                    public void onFinish(String response)
//                    {
//                        if(response.equals("Post Response: Failed"))
//                        {
//                            Message message = new Message();
//                            message.what=2;
//                            handler.sendMessage(message);
//                        }
//                        else
//                        {
//                            Message message = new Message();
//                            message.what = 1;
//                            Gson gson = new Gson();
//                            userInfo = gson.fromJson(response,UserInfo.class);
//                            handler.sendMessage(message);
//                            staffId=userID.getStaff_id();
//                            System.out.println("J"+response+"H"+userInfo.getStaff_name());
//                        }
//
//                    }
//                    @Override
//                    public void onError(Exception e)
//                    {
//                        Message message = new Message();
//                        message.what=2;
//                        handler.sendMessage(message);
//                        System.out.println(e);
//                    }
//                });
//            }
//        });
    }
    private boolean get_user()
    {
        if(et_username.getText()==null||et_username.getText().toString().length()==0)
        {
            Toast.makeText(MyApplication.getContext(),"请输入用户名",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(et_password.getText()==null||et_password.getText().toString().length()==0)
        {
            Toast.makeText(MyApplication.getContext(),"请输入密码",Toast.LENGTH_SHORT).show();
            return false;
        }

        userID.setStaff_id(et_username.getText().toString().trim());
        userID.setStaff_passwd(et_password.getText().toString().trim());
        System.out.println(et_username.getText().toString());
        return true;
    }
    private boolean check_user()
    {
        if(userID.getStaff_id().length()==0)
        {
            Toast.makeText(MyApplication.getContext(),"用户名不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(userID.getStaff_passwd().length()==0)
        {
            Toast.makeText(MyApplication.getContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
