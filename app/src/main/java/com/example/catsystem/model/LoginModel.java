package com.example.catsystem.model;

import android.util.Log;

import com.example.catsystem.R;
import com.example.catsystem.request.UserRequest;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author ： yxm521
 * time    ： 2022/3/23
 * 登录界面的model层
 */
public class LoginModel implements ILoginModel{
    private String TAG = "LoginModel";
    @Override
    public void login(String phone, String pwd, Callback callback) {
        UserRequest request = new UserRequest();
        request.login(phone, pwd, new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //先一律视为网络未连接
                Log.e(TAG,"error:"+e.toString());
                callback.onFailed(R.string.network_error);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.e(TAG,"onResponse:response:"+response.body().string());
                switch (response.code()){
                    case 200:
                        callback.onSucess();
                        break;
                    case 500:
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
