package com.example.catsystem.base;

import android.content.Context;

/**
 * author ： yxm521
 * time    ： 2022/3/21
 * view层的顶层逻辑
 */
public interface IBaseView {
    void showErrorMessage(String msg, Context context);
    void showSuccessMessage(String msg,Context context);
}
