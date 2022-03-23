package com.example.catsystem.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.example.catsystem.R;

/**
 * author ： yxm521
 * time    ： 2022/3/21
 * 检查输入是否规范
 */
public class CheckUtil {

    /**
     * 检查电话号码是否有效,并给与提示
     * @param phone 电话号码
     * @param context 上下文
     * @return 有效返回true,无效返回false
     */
    public static boolean checkPhoneValid(String phone, Context context){
        if(phone == null | phone.equals("")){
            ViewUtil.showErrorToast(context.getString(R.string.null_phone),context);
            return false;
        }else if(phone.length() != Constant.PHONE_LEN){
            ViewUtil.showErrorToast(context.getString(R.string.phone_length),context);
            return false;
        }else {
            return true;
        }
    }

    /**
     * 检查网络是否连接
     * @return 连接返回true,否则false
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 检查用户名长度
     * @param username
     * @return 用户名长度小于5返回false
     */
    public static boolean checkNameLength(String username,Context context){
        if(username.length() < 5){
            return false;
        }
        return true;
    }

    /**
     * 检查文本是否为空
     * @param text 输入的文本
     * @return 为空返回true,否则返回false
     */
    public static boolean checkTextIsNull(String text){
        if(text.equals("") || text == null){
            return true;
        }
        return false;
    }

    /**
     * 检查密码长度
     * @param pwd 密码
     * @return 长度满足返回true
     */
    public static boolean checkPwdLength(String pwd,Context context){
        if(pwd.length() >= 8 && pwd.length() <= 15){
            return true;
        }
        return false;
    }

    /**
     * 检查两次密码是否一致
     * @param pwd 原密码
     * @param rpwd 重复密码
     * @return
     */
    public static boolean checkRepeatPwdSameAsPwd(String pwd,String rpwd){
        if(pwd.equals(rpwd)){
            return true;
        }
        return false;
    }
}
