package com.example.catsystem.presenter;

import android.content.Context;

import com.example.catsystem.R;
import com.example.catsystem.base.BasePresenter;
import com.example.catsystem.model.ILoginModel;
import com.example.catsystem.model.LoginModel;
import com.example.catsystem.util.CheckUtil;
import com.example.catsystem.views.view.ILoginView;

/**
 * author ： yxm521
 * time    ： 2022/3/21
 */
public class LoginPresenter<V extends ILoginView> extends BasePresenter {
    private LoginModel model = new LoginModel();
    private String TAG = "LoginPresenter";

    public void login(String phone, String pwd, Context context){
        V mid = ((V)view.get());
        if(mid != null && model != null){
            if(!CheckUtil.checkPhoneValid(phone,context)){
                //电话号码无效的提示

            }else if(CheckUtil.checkTextIsNull(pwd)){
                //密码为空的提示

            }else{
                model.login(phone, pwd, new ILoginModel.Callback() {
                    @Override
                    public void onFailed(int msg_id) {

                    }

                    @Override
                    public void onSucess() {
                        mid.showSuccessMessage(context.getString(R.string.login_sucess),context);
                    }
                });
            }
        }
    }
}
