package com.example.catsystem.model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.catsystem.R;
import com.example.catsystem.entity.User;
import com.example.catsystem.request.UserRequest;
import com.example.catsystem.util.Constant;
import com.mob.tools.utils.SharePrefrenceHelper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author ： yxm521
 * time    ： 2022/3/20
 * 注册界面的model层
 */
public class RegisterModel implements IRegisterModel{
    private String TAG = "RegisterModel";

    @Override
    public void register(User user, Callback callback) {
        UserRequest request = new UserRequest();
        //异步消息处理
        request.register(user, new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG,"error:"+e.toString());
                //先一律视为网络未连接
                callback.onFailed(R.string.network_error);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.e(TAG,"response:"+response.body().string() +" response code:"+response.code());
                switch (response.code()){
                    case 200:
                        callback.onSucess();
                        break;
                    case 500:
                        callback.onFailed(R.string.phone_has_register);
                    default:
                        break;
                }
            }
        });
    }
}
