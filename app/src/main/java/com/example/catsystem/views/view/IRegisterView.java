package com.example.catsystem.views.view;

import android.os.Handler;

import com.example.catsystem.base.IBaseView;

/**
 * author ： yxm521
 * time    ： 2022/3/21
 * 注册界面的UI逻辑
 */
public interface IRegisterView extends IBaseView {
    //加载注册进度条
    void showProgress();
    //注册成功时隐藏注册进度条
    void hideProgressWhenSuccess();
    //注册失败时隐藏注册进度条
    void hideProgressWhenFailed(String msg);
//    //显示验证码倒计时
//    void showCountDown(Handler handler, int currentSecond);
//    //隐藏倒计时
//    void hideCountDown();
//    //展示网络繁忙
//    void showBusyHint();
//    //展示网络未连接
//    void showNoNetwork();
//    //展示注册失败
//    void showRegisterError();
    //用户名为空
}
