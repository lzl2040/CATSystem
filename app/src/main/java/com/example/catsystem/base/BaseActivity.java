package com.example.catsystem.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.catsystem.controller.ActivityController;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将activity加入到列表中,方便管理
        ActivityController.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将activity加入到列表中
        ActivityController.removeActivity(this);
    }

    /**
     * 获得各种view
     */
    public void initView(){

    }

    /**
     * 设置监听事件
     */
    public void setListener(){

    }

    /**
     * 设置适配器
     */
    public void setAdapter(){

    }

}
