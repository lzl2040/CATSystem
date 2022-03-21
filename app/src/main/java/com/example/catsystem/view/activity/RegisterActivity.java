package com.example.catsystem.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catsystem.R;
import com.example.catsystem.base.BaseActivity;
import com.example.catsystem.util.CheckUtil;
import com.example.catsystem.util.EditTextWatcherImp;
import com.example.catsystem.util.ViewUtil;
import com.mob.tools.utils.SharePrefrenceHelper;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.UserInterruptException;

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
    //smssdk需要的一些变量
    private String currentId = "86";
    private SharePrefrenceHelper helper;
    private EventHandler eventHandler;
    private static final int REQUEST_CODE_VERIFY = 1001;
    private int currentSecond;
    private static final int COUNTDOWN = 60;
    private static final String KEY_START_TIME = "start_time";
    private static final String TEMP_CODE = "1319972";
    private Handler handler;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG,"onActivityResult...");
        if (requestCode == REQUEST_CODE_VERIFY) {
            // 重置"获取验证码"按钮
            getCodeBtn.setText(R.string.get_code);
            getCodeBtn.setEnabled(true);
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.e(TAG,"onCreate...");
        initView();
        getHelper();
        setListener();
        getVertifyCode();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy....");
        /**
         * 移除这个事件消息接收
         */
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        SMSSDK.unregisterEventHandler(eventHandler);
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

                String phone = phoneEdt.getText().toString();
                if(CheckUtil.checkPhoneValid(phone,context)){
                    long startTime = helper.getLong(KEY_START_TIME);
                    if (System.currentTimeMillis() - startTime < COUNTDOWN * 1000) {
                        ViewUtil.showErrorToast(getString(R.string.busy_hint),context);
                        return;
                    }
                    if (!CheckUtil.isNetworkConnected(context)) {
                        ViewUtil.showErrorToast(getString(R.string.network_error),context);
                        return;
                    }
                    SMSSDK.getVerificationCode("86", phone.trim());
                }
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

    /**
     * 获得SharePrefrenceHelper
     */
    public void getHelper(){
        helper = new SharePrefrenceHelper(context);
        helper.open("sms_sp");
    }

    /**
     * 设置验证码倒计时
     */
    public void setVertifyCodeCountDown(){
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 5){
                    if (getCodeBtn != null) {
                        if (currentSecond > 0) {
                            getCodeBtn.setText(getString(R.string.get_code) + " (" + currentSecond + "s)");
                            getCodeBtn.setEnabled(false);
                            currentSecond--;
                            handler.sendEmptyMessageDelayed(5, 1000);
                        } else {
                            getCodeBtn.setText(R.string.get_code);
                            getCodeBtn.setEnabled(true);
                        }
                    }
                }
                else if(msg.what == 3){
//                    Toast.makeText(context,"登录成功",Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(context);
//                    getActivity().startActivity(intent);
//                    getActivity().finish();
                }else if(msg.what == 4){
                    ViewUtil.showErrorToast(getString(R.string.register_fail),context);
                    //Toast.makeText(getActivity(),"登录失败",Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    /**
     * 设置事件消息的处理话柄
     */
    public void registerEventHandler(){
        eventHandler = new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //已经提交验证码
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE || event == SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE){
                    //得到验证码
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                if (result == SMSSDK.RESULT_COMPLETE) {
                                    currentSecond = COUNTDOWN;
                                    handler.sendEmptyMessage(5);
                                    helper.putLong(KEY_START_TIME, System.currentTimeMillis());
                                } else {
                                    if (data != null && (data instanceof UserInterruptException)) {
                                        // 由于此处是开发者自己决定要中断发送的，因此什么都不用做
                                        return;
                                    }
                                    ViewUtil.processError(data,context);
                                }
                        }
                    });
                }
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }

    /**
     * 获得验证码的具体步骤
     */
    public void getVertifyCode(){
        setVertifyCodeCountDown();
        registerEventHandler();
    }
}