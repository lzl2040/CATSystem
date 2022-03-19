package com.example.catsystem.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.catsystem.R;
import com.example.catsystem.base.BaseActivity;
import com.example.catsystem.util.EditTextWatcherImp;
import com.example.catsystem.util.ViewUtil;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity {
    private EditText phoneEdt,nameEdt,pwdEdt,rpwdEdt,codeEdt;
    private Button getCodeBtn,registerBtn;
    private CheckBox checkBox;
    private TextView userAgreement;
    private ImageView clearPhoneImg,clearNameImg,clearPwdImg,clearRpwdImg,backImg;
    private String TAG = ViewUtil.getClassName(this);
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.e(TAG,"onCreate...");
        initView();
        setListener();
    }

    @Override
    public void initView() {
        Log.e(TAG,"initView....");
        //super.initView();
        context = this;
        phoneEdt = findViewById(R.id.phone);
        nameEdt = findViewById(R.id.username);
        pwdEdt = findViewById(R.id.pwd);
        rpwdEdt = findViewById(R.id.rpwd);
        codeEdt = findViewById(R.id.code);
        getCodeBtn = findViewById(R.id.get_code);
        registerBtn = findViewById(R.id.register_btn);
        checkBox = findViewById(R.id.checkBox);
        userAgreement = findViewById(R.id.user_agreement);
        clearPhoneImg = findViewById(R.id.clear_phone);
        clearNameImg = findViewById(R.id.clear_username);
        clearPwdImg = findViewById(R.id.clear_pwd);
        clearRpwdImg = findViewById(R.id.clear_rpwd);
        backImg = findViewById(R.id.back);
    }

    @Override
    public void setListener() {
        Log.e(TAG,"setListener....");
        //super.setListener();
        //设置电话号码输入框文字改变的监听
        phoneEdt.addTextChangedListener(new EditTextWatcherImp(){

            @Override
            public void afterTextChanged(Editable s) {
                //super.afterTextChanged(s);
                ViewUtil.setClearImgCondition(phoneEdt,clearPhoneImg);
            }
        });

        //设置用户名输入框文字改变的监听
        nameEdt.addTextChangedListener(new EditTextWatcherImp(){

            @Override
            public void afterTextChanged(Editable s) {
                //super.afterTextChanged(s);
                ViewUtil.setClearImgCondition(nameEdt,clearNameImg);
            }
        });

        //设置密码输入框文字改变的监听
        pwdEdt.addTextChangedListener(new EditTextWatcherImp(){

            @Override
            public void afterTextChanged(Editable s) {
                //super.afterTextChanged(s);
                ViewUtil.setClearImgCondition(pwdEdt,clearPwdImg);
            }
        });

        //设置再次输入密码文字改变的监听
        rpwdEdt.addTextChangedListener(new EditTextWatcherImp(){

            @Override
            public void afterTextChanged(Editable s) {
                //super.afterTextChanged(s);
                ViewUtil.setClearImgCondition(rpwdEdt,clearRpwdImg);
            }
        });

        //设置获取验证码按钮的点击监听事件
        getCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //设置注册按钮的点击监听事件
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //设置清空文字的点击监听事件
        //清除电话号码
        clearPhoneImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneEdt.setText(R.string.blank);
            }
        });

        //清除名字
        clearNameImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameEdt.setText(R.string.blank);
            }
        });

        //清除密码
        clearPwdImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwdEdt.setText(R.string.blank);
            }
        });

        //清除重复密码
        clearRpwdImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpwdEdt.setText(R.string.blank);
            }
        });

        //设置用户协议的点击监听事件
        userAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //设置返回按键
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(context,LoginActivity.class);
                finish();
            }
        });
    }

}