package com.example.catsystem.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.example.catsystem.base.BasePresenter;
import com.example.catsystem.controller.ActivityController;
import com.example.catsystem.entity.User;
import com.example.catsystem.model.IRegisterModel;
import com.example.catsystem.model.RegisterModel;
import com.example.catsystem.views.view.IRegisterView;
import com.example.catsystem.R;

import java.lang.ref.WeakReference;

/**
 * author ： yxm521
 * time    ： 2022/3/20
 * 注册程序的presenter层
 */
public class RegisterPresenter<T extends IRegisterView> extends BasePresenter {
    private RegisterModel model = new RegisterModel();

    public void register(User user, Context context){
        if(view.get() != null && model != null){
            T mid = ((T)view.get());
            //展示进度条
            mid.showProgress();
            model.register(user, new IRegisterModel.Callback() {
                @Override
                public void onSucess() {
                    //隐藏进度条
                    mid.hideProgress();
                    //显示登录成功
                    mid.showSuccessMessage(context.getString(R.string.register_sucess),context);
                }

                @Override
                public void onFailed() {
                    //隐藏进度条
                    mid.hideProgress();
                    //显示登录失败
                    mid.showErrorMessage(context.getString(R.string.register_fail),context);
                }
            });
        }
    }
}
