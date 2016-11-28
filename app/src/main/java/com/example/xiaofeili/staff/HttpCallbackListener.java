package com.example.xiaofeili.staff;

/**
 * 处理网络请求结果的接口
 */
public interface HttpCallbackListener
{
    void onFinish(String response);
    void onError(Exception e);
}
