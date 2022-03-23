package com.example.catsystem.request;

import android.util.Log;

import com.example.catsystem.entity.User;
import com.example.catsystem.util.StaticData;
import com.google.gson.Gson;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author ： yxm521
 * time    ： 2022/3/22
 */
public class UserRequest {
    private String TAG = "UserRequest";
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Request request;
    private RequestBody requestBody;
    private final Gson gson = StaticData.getGson();
    private final MediaType jsonType=MediaType.Companion.parse("application/json;charset=utf-8");

    public void register(User user, Callback callback){
        String jsonUser = gson.toJson(user);
        Log.e(TAG,"json格式:"+jsonUser);
        requestBody = RequestBody.Companion.create(jsonUser,jsonType);
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getRegister())
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void login(String phone,String pwd,Callback callback){
        FormBody formBody = new FormBody.Builder()
                .add("phone",phone)
                .add("pwd",pwd)
                .build();
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getLogin())
                .post(formBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
