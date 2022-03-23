package com.example.catsystem.views.activity;

import android.content.Context;
import android.os.Bundle;
import com.example.catsystem.R;
import com.example.catsystem.base.BaseActivity;
import com.example.catsystem.presenter.AboutPresenter;
import com.example.catsystem.views.view.IAboutView;

/**
 * 关于我们的界面
 */
public class AboutActivity extends BaseActivity<AboutPresenter, IAboutView> implements IAboutView{
    private String TAG = "AboutActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        setListener();
    }

    @Override
    public AboutPresenter createPresenter() {
        return new AboutPresenter();
    }

    @Override
    public void initView() {
        //super.initView();
    }

    @Override
    public void setListener() {
        //super.setListener();
    }

    @Override
    public void showErrorMessage(String msg, Context context) {

    }

    @Override
    public void showSuccessMessage(String msg, Context context) {

    }
}