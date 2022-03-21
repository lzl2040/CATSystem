package com.example.catsystem.model;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.catsystem.entity.User;
import com.example.catsystem.util.Constant;

/**
 * author ： yxm521
 * time    ： 2022/3/20
 */
public class RegisterModel implements IRegisterModel{

    @Override
    public void register(User user, Callback callback) {
        new Handler(Looper.getMainLooper())
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSucess();
                    }
                }, Constant.NETWORK_INTERVAL);
    }
}
