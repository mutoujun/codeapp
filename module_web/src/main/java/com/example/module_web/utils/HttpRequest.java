package com.example.module_web.utils;

/**
 * Created by Administrator on 2018/2/24.
 */

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * 执行网络请求
 */
public class HttpRequest {

    /**
     * 请求
     * @param path
     */
    public void request(String path){
        new LeftMenuTask().execute(path);
    }

    class LeftMenuTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... param) {
            //网络访问
            try{
                URL url = new URL(param[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    //读取数据
                    InputStream in = connection.getInputStream();
                    byte[] bs = new byte[1024];
                    int len = 0;
                    StringBuilder builder = new StringBuilder();
                    String str;
                    while ((len = in.read(bs))!=-1){
                        str = new String(bs,0,len);
                        builder.append(str);
                    }
                    return builder.toString();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "error";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
}