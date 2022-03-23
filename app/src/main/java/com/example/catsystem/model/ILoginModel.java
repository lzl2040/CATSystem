package com.example.catsystem.model;

/**
 * author ： yxm521
 * time    ： 2022/3/23
 */
public interface ILoginModel {
    void login(String phone,String pwd,Callback callback);
    interface Callback{
        void onFailed(int msg_id);
        void onSucess();
    }
}
