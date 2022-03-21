package com.example.catsystem.model;

import android.os.AsyncTask;

import com.example.catsystem.entity.User;

/**
 * author ： yxm521
 * time    ： 2022/3/21
 */
public interface IRegisterModel {
    void register(User user,Callback callback);
    //回调
    interface Callback{
        void onSucess();
        void onFailed();
    }
}
