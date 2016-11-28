package com.example.xiaofeili.staff;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 发送网络请求，处理返回结果
 */
public class HttpUtil
{
    /**
     * 发送GET请求
     * @param address 请求地址
     * @param data 发送的数据
     * @param listener 处理返回结果的回调函数
     */
    public static void sendHttpGetRequest(final String address, final String data, final HttpCallbackListener listener)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                HttpURLConnection conn = null;
                try
                {
                    URL url = new URL(address+ "?" +data);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null)
                    {
                        response.append(line);
                    }
                    if(listener != null)
                    {
                        listener.onFinish(response.toString());
                    }
                }catch (Exception e)
                {
                    if(listener != null)
                    {
                        listener.onError(e);
                    }
                }finally
                {
                    if (conn != null)
                    {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }

    /**
     * 发送POST请求
     * @param address 请求地址
     * @param data 发送的数据
     * @param listener 处理返回结果的回调函数
     */
    public static void sendHttpPostRequest(final String address, final String data, final HttpCallbackListener listener)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                HttpURLConnection conn = null;
                try
                {
                    URL url = new URL(address);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.addRequestProperty("Type","Get");
                    DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
                    outputStream.writeBytes(data);

                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null)
                    {
                        response.append(line);
                    }
                    if(listener != null)
                    {
                        listener.onFinish(response.toString());
                    }
                }catch (Exception e)
                {
                    if(listener != null)
                    {
                        listener.onError(e);
                    }
                }finally
                {
                    if (conn != null)
                    {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }
}
