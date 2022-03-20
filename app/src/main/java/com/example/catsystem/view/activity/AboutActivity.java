package com.example.catsystem.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.catsystem.R;
import com.example.catsystem.base.BaseActivity;

/**
 * 关于我们的界面
 */
public class AboutActivity extends BaseActivity {
    private String TAG = "AboutActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        setListener();
    }

    @Override
    public void initView() {
        //super.initView();
    }

    @Override
    public void setListener() {
        //super.setListener();
    }
}