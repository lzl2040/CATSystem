package com.example.catsystem.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.catsystem.privacy.OnDialogListener;
import com.example.catsystem.privacy.PrivacyDialog;
import com.example.catsystem.util.Constant;
import com.example.catsystem.util.DemoResHelper;
import com.example.catsystem.util.DemoSpHelper;
import com.example.catsystem.util.EditTextWatcherImp;
import com.example.catsystem.util.ViewUtil;
import com.mob.MobSDK;
import com.mob.OperationCallback;

import java.util.ArrayList;

/**
 * 登录界面
 */
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
        Log.e(TAG,"onCreate...");
        initView();
        initSMSSDK();
        setListener();
    }

    @Override
    public void initView() {
//        super.initView();
        Log.e(TAG,"initView....");
        context = this;
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

        //设置清除用户名/电话号码的监听
        clearNameImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameEdt.setText(R.string.blank);
            }
        });

        //设置清除密码的监听
        clearPwdImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwdEdt.setText(R.string.blank);
            }
        });
    }

    /**
     * 初始化SMSSDK
     */
    public void initSMSSDK(){
        //ViewUtil.showNotice("开始初始化",context);
        checkPrivacy();
        MobSDK.init(context, Constant.appKey,Constant.appSecretKey);
    }

    /**
     * 检查是否授权SMSSDK
     */
    public void checkPrivacy(){
        if (!DemoSpHelper.getInstance().isPrivacyGranted()) {
            //没有授权的情况
            //ViewUtil.showNotice("没有授权",context);
            PrivacyDialog privacyDialog = new PrivacyDialog(context, new OnDialogListener() {
                @Override
                public void onAgree() {
                    //同意使用
                    uploadResult(true);
                    DemoSpHelper.getInstance().setPrivacyGranted(true);
                    goOn();
                }

                @Override
                public void onDisagree() {
                    //不同意使用
                    uploadResult(false);
                    DemoSpHelper.getInstance().setPrivacyGranted(false);
                    Handler handler = new Handler(new Handler.Callback() {
                        @Override
                        public boolean handleMessage(Message msg) {
                            System.exit(0);
                            return false;
                        }
                    });
                    handler.sendEmptyMessageDelayed(0, 500);
                }
            });
            privacyDialog.show();
        } else {
            goOn();
        }
    }

    /**
     * 进行动态权限的申请
     */
    private void goOn() {
        // 动态权限申请
        if (Build.VERSION.SDK_INT >= 23) {
            int readPhone = checkSelfPermission("android.permission.READ_PHONE_STATE");
            int receiveSms = checkSelfPermission("android.permission.RECEIVE_SMS");
            int readContacts = checkSelfPermission("android.permission.READ_CONTACTS");
            int readSdcard = checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE");

            int requestCode = 0;
            final ArrayList<String> permissions = new ArrayList<String>();
            if (readPhone != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 0;
                permissions.add("android.permission.READ_PHONE_STATE");
            }
            if (receiveSms != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 1;
                permissions.add("android.permission.RECEIVE_SMS");
            }
            if (readContacts != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 2;
                permissions.add("android.permission.READ_CONTACTS");
            }
            if (readSdcard != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 3;
                permissions.add("android.permission.READ_EXTERNAL_STORAGE");
            }
            if (requestCode > 0) {
                String[] permission = new String[permissions.size()];
                this.requestPermissions(permissions.toArray(permission), requestCode);
                return;
            }
        }
    }

    /**
     * 将授权结果上传
     * @param granted
     */
    private void uploadResult(boolean granted) {
        MobSDK.submitPolicyGrantResult(granted, new OperationCallback<Void>() {
            @Override
            public void onComplete(Void aVoid) {
                // Nothing to do
            }

            @Override
            public void onFailure(Throwable throwable) {
                // Nothing to do
                Log.e(TAG, "Submit privacy grant result error", throwable);
            }
        });
    }

}