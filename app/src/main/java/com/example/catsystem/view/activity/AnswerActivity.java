package com.example.catsystem.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.catsystem.R;
import com.example.catsystem.base.BaseActivity;
import com.example.catsystem.util.ViewUtil;

/**
 * 回答界面
 */
public class AnswerActivity extends BaseActivity {
    private String TAG = "AnswerActivity";
    private RecyclerView recyclerView;
    private ImageView backImg;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Log.e(TAG,"onCreate...");
        initView();
        setListener();
        setAdapter();
    }

    @Override
    public void initView() {
        //super.initView();
        context = this;
        Log.e(TAG,"initView...");
        recyclerView = findViewById(R.id.recyclerview);
        backImg = findViewById(R.id.back);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.e(TAG,"setListener...");
        //设置返回图片的点击事件
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(context,ChooseSubjectActivity.class);
                finish();
            }
        });
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
    }
}