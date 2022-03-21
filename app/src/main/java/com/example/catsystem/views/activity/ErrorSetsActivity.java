package com.example.catsystem.views.activity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.catsystem.R;
import com.example.catsystem.base.BaseActivity;
import com.example.catsystem.presenter.ErrorSetsPresenter;
import com.example.catsystem.util.ViewUtil;
import com.example.catsystem.views.view.IErrorSetsView;

/**
 * 错题集
 */
public class ErrorSetsActivity extends BaseActivity<ErrorSetsPresenter, IErrorSetsView> {
    private String TAG = "ErrorSetsActivity";
    private RecyclerView recyclerView;
    private ImageView backImg;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_sets);
        Log.e(TAG,"onCreate...");
        initView();
        setListener();
        setAdapter();
    }

    @Override
    public ErrorSetsPresenter createPresenter() {
        return new ErrorSetsPresenter();
    }

    @Override
    public void initView() {
        //super.initView();
        Log.e(TAG,"initView...");
        context = this;
        recyclerView = findViewById(R.id.recyclerview);
        backImg = findViewById(R.id.back);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.e(TAG,"initView...");
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(context,MainActivity.class);
                finish();
            }
        });
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.e(TAG,"initView...");
    }
}