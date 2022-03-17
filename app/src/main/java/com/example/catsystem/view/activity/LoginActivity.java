package com.example.catsystem.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.catsystem.R;
import com.example.catsystem.base.BaseActivity;
import com.example.catsystem.util.EditTextWatcherImp;
import com.example.catsystem.util.ViewUtil;

public class LoginActivity extends BaseActivity {
    private EditText usernameEdt,pwdEdt;
    private ImageView clearNameImg,clearPwdImg;
    private CheckBox showPwd;
    private Button loginBtn;
    private TextView registerTxt;
    private Context context;
    //获得类名
    private String TAG = ViewUtil.getClassName(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        setListener();
    }

    @Override
    public void initView() {
//        super.initView();
        context = this;
        Log.e(TAG,"initView....");
        usernameEdt = findViewById(R.id.username);
        pwdEdt = findViewById(R.id.pwd);
        clearNameImg = findViewById(R.id.clear_username);
        clearPwdImg = findViewById(R.id.clear_pwd);
        showPwd = findViewById(R.id.open_eye);
        registerTxt = findViewById(R.id.register_txt);
        loginBtn = findViewById(R.id.login_btn);
    }

    @Override
    public void setListener() {
//        super.setListener();
        Log.e(TAG,"setListener....");
        //设置用户名字输入框文字改变监听
        usernameEdt.addTextChangedListener(new EditTextWatcherImp(){
            @Override
            public void afterTextChanged(Editable s) {
                ViewUtil.setClearImgCondition(usernameEdt,clearNameImg);
            }
        });

        //设置密码输入框文字改变监听
        pwdEdt.addTextChangedListener(new EditTextWatcherImp(){

            @Override
            public void afterTextChanged(Editable s) {
                ViewUtil.setClearImgCondition(pwdEdt,clearPwdImg);
            }
        });

        //设置登录按钮的点击监听事件
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(context,MainActivity.class);
                finish();
            }
        });

        //设置注册文字的点击监听事件
        registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(context,RegisterActivity.class);
                finish();
            }
        });

        //设置查看密码按钮的监听事件
        pwdEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                Log.e(TAG, "onFocusChange: pwdEdt hasFocus: " + hasFocus);
                if(hasFocus){
                    showPwd.setVisibility(View.VISIBLE);
                }else{
                    showPwd.setVisibility(View.INVISIBLE);
                }
            }
        });

        //设置隐藏/展示密码的点击监听事件
        showPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: showPwd.isChecked(): " + showPwd.isChecked());
                if(showPwd.isChecked()){
                    //显示密码
                    pwdEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //隐藏密码
                    pwdEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                //将光标移到最后
                pwdEdt.setSelection(pwdEdt.getText().length());
            }
        });
    }
}