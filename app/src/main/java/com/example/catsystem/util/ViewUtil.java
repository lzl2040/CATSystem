package com.example.catsystem.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 各种view的工具类
 */
public class ViewUtil {

    /**
     * 设置清除按钮的显示问题
     * @param editText 输入框
     * @param clear 对应输入框的清除按钮
     */
    public static void setClearImgCondition(EditText editText, ImageView clear){
        if(editText.getText().length() > 0){
            clear.setVisibility(View.VISIBLE);
        }else{
            clear.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 获取每个类的名字
     * @param context 类的上下文
     * @return 返回类名
     */
    public static String getClassName(Context context){
        return context.getClass().getSimpleName();
    }

    /**
     * 跳转界面
     * @param context 所处界面的上下文
     * @param cls 要跳转界面的class内容
     */
    public static void jumpTo(Context context, Class cls){
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }

    /**
     * 提示消息
     * @param msg 消息内容
     * @param context 上下文
     */
    public static void showNotice(String msg,Context context){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
