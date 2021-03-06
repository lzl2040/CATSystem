package com.example.catsystem.views.activity;

import androidx.annotation.Nullable;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.catsystem.R;
import com.example.catsystem.base.BaseActivity;
import com.example.catsystem.entity.User;
import com.example.catsystem.presenter.RegisterPresenter;
import com.example.catsystem.util.CheckUtil;
import com.example.catsystem.util.DemoResHelper;
import com.example.catsystem.util.EditTextWatcherImp;
import com.example.catsystem.util.ViewUtil;
import com.example.catsystem.views.view.IRegisterView;
import com.mob.tools.utils.SharePrefrenceHelper;

import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.UserInterruptException;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter,IRegisterView> implements IRegisterView {
    private EditText phoneEdt,nameEdt,pwdEdt,rpwdEdt,codeEdt;
    private Button getCodeBtn,registerBtn;
    private CheckBox checkBox;
    private TextView userAgreement;
    private ImageView clearPhoneImg,clearNameImg,clearPwdImg,clearRpwdImg,backImg;
    private String TAG = ViewUtil.getClassName(this);
    private Context context;
    private static final int REQUEST_CODE_VERIFY = 1001;
    //private Handler handler;
    private ProgressDialog dialog;

    private SharePrefrenceHelper helper;
    private Handler handler;
    //smssdk需要的一些变量
    private EventHandler eventHandler;
    private static final int COUNTDOWN = 60;
    private static final String KEY_START_TIME = "start_time";
    private static final String TEMP_CODE = "1319972";
    private int currentSecond;
    private User user;

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
        getHelper(context);
        setListener();
        setVertifyCode();
    }

    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy....");
    }

    @Override
    public void initView() {
        Log.e(TAG,"initView....");
        //super.initView();
        context = this;
        //设置进度条
        dialog = new ProgressDialog(context);
        dialog.setTitle("正在注册");

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
                /**
                 * 1.先检查输入是否合法
                 * 2.输入合法才提交验证码
                 */
                String phone = phoneEdt.getText().toString();
                String name = nameEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                String rpwd = rpwdEdt.getText().toString();
                boolean isAgree = checkBox.isChecked();
                user = new User(name,pwd,phone);
                String code =codeEdt.getText().toString();
                if(!isAgree){
                    ViewUtil.showErrorToast(getString(R.string.determine_agreement),context);
                }else if(!CheckUtil.checkPhoneValid(user.getPhone(),context)){

                }else if(CheckUtil.checkTextIsNull(user.getName())){
                    ViewUtil.showErrorToast(getString(R.string.name_null_warning),context);
                }else if(!CheckUtil.checkNameLength(user.getName(),context)){
                    ViewUtil.showErrorToast(getString(R.string.name_length_error),context);
                }else if(CheckUtil.checkTextIsNull(user.getPwd()) || CheckUtil.checkTextIsNull(rpwd)){
                    ViewUtil.showErrorToast(getString(R.string.pwd_null_warning),context);
                } else if(!CheckUtil.checkPwdLength(user.getPwd(),context)){
                    ViewUtil.showErrorToast(getString(R.string.pwd_length_error),context);
                }else if(!CheckUtil.checkRepeatPwdSameAsPwd(user.getPwd(),rpwd)){
                    ViewUtil.showErrorToast(getString(R.string.repeat_rpwd_error),context);
                }else{
                    SMSSDK.submitVerificationCode("86",phone,code);
                }
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
     * 验证码配置步骤
     */
    public void setVertifyCode(){
        setVertifyCodeCountDown();
        registerEventHandler();
    }

    /**
     * 获得SharePrefrenceHelper
     * @param context
     */
    private void getHelper(Context context){
        helper = new SharePrefrenceHelper(context);
        helper.open("sms_sp");
    }

    /**
     * 设置事件消息的处理话柄
     */
    private void registerEventHandler(){
        eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //已经提交验证码
                    Log.e(TAG,"已经提交验证码");
                    if(result == SMSSDK.RESULT_COMPLETE){
                        String phone = phoneEdt.getText().toString();
                        String name = nameEdt.getText().toString();
                        String pwd = pwdEdt.getText().toString();
                        String rpwd = rpwdEdt.getText().toString();
                        user = new User(name,pwd,phone);
                        //因为这个是耗时操作,所以要另外开一个子线程
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                presenter.register(user,context,rpwd);
                            }
                        });
                    }else{
                        Message msg = new Message();
                        msg.what = 4;
                        msg.obj = getString(R.string.vertify_code_error);
                        handler.sendMessage(msg);
                    }

                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE || event == SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE){
                    //点击发送验证码
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
                                Message msg = new Message();
                                msg.what = 4;
                                msg.obj = getString(R.string.vertify_code_error);
                                handler.sendMessage(msg);
                                //processError(data,context);
                            }
                        }
                    });
                }
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }

    /**
     * 设置验证码倒计时
     */
    private void setVertifyCodeCountDown(){
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 5){
                    if(getCodeBtn != null){
                        if(currentSecond > 0){
                            getCodeBtn.setText(getString(R.string.get_code) + " (" + (currentSecond) + "s)");
                            getCodeBtn.setEnabled(false);
                            currentSecond--;
                            handler.sendEmptyMessageDelayed(5, 1000);
                        }else{
                            getCodeBtn.setText(R.string.get_code);
                            getCodeBtn.setEnabled(true);
                        }
                    }
                }
                else if(msg.what == 3){
                    //注册成功时的情况
                    dialog.dismiss();
                    ViewUtil.showNotice(getString(R.string.register_sucess),context);
                    ViewUtil.jumpTo(context,LoginActivity.class);
                    finish();
                }else if(msg.what == 4){
                    //注册失败
                    dialog.hide();
                    String warning = String.valueOf(msg.obj);
                    ViewUtil.showErrorToast(warning,context);
                }else if(msg.what == 1){
                    //展示进度条,只能在这里显示，因为子进程不能更新UI
                    dialog.show();
                }
            }
        };
    }

    @Override
    public void showProgress() {
        handler.sendEmptyMessage(1);
    }

    @Override
    public void hideProgressWhenSuccess() {
        handler.sendEmptyMessage(3);
    }

    @Override
    public void hideProgressWhenFailed(String msg) {
        Message msgs = new Message();
        msgs.obj = (Object)msg;
        msgs.what = 4;
        handler.sendMessage(msgs);
    }

    @Override
    public void showErrorMessage(String msg, Context context) {

    }

    @Override
    public void showSuccessMessage(String msg, Context context) {

    }
}