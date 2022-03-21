package com.example.catsystem.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
            ViewUtil.showNotice(context.getString(R.string.null_phone),context);
            return false;
        }else if(phone.length() != Constant.PHONE_LEN){
            ViewUtil.showNotice(context.getString(R.string.phone_length),context);
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
}
