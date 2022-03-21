package com.example.catsystem.views.view;

import com.example.catsystem.base.IBaseView;

/**
 * author ： yxm521
 * time    ： 2022/3/21
 * 注册界面的UI逻辑
 */
public interface IRegisterView extends IBaseView {
    //加载注册进度条
    void showProgress();
    //隐藏注册进度条
    void hideProgress();
}
