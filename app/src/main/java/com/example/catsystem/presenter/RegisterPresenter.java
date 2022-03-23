package com.example.catsystem.presenter;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.catsystem.base.BasePresenter;
import com.example.catsystem.base.IBaseView;
import com.example.catsystem.controller.ActivityController;
import com.example.catsystem.entity.User;
import com.example.catsystem.model.IRegisterModel;
import com.example.catsystem.model.RegisterModel;
import com.example.catsystem.util.CheckUtil;
import com.example.catsystem.util.ViewUtil;
import com.example.catsystem.views.view.IRegisterView;
import com.example.catsystem.R;
import com.mob.tools.utils.SharePrefrenceHelper;

import java.lang.ref.WeakReference;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.UserInterruptException;

/**
 * author ： yxm521
 * time    ： 2022/3/20
 * 注册程序的presenter层
 */
public class RegisterPresenter<T extends IRegisterView> extends BasePresenter {
    private RegisterModel model = new RegisterModel();
    private String TAG = "RegisterPresenter";

    public void register(User user, Context context,String rpwd){
        T mid = ((T)view.get());
        if(mid != null && model != null){
                Log.e(TAG,"23");
                mid.showProgress();
                model.register(user, new IRegisterModel.Callback() {
                    @Override
                    public void onSucess() {
                        mid.hideProgressWhenSuccess();
                    }
                    @Override
                    public void onFailed(int msg_id) {
                        mid.hideProgressWhenFailed(context.getString(msg_id));
                    }
                });
        }
    }
}
