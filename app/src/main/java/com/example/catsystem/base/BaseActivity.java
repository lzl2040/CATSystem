package com.example.catsystem.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.catsystem.R;
import com.example.catsystem.controller.ActivityController;

public abstract class BaseActivity<T extends BasePresenter,V extends IBaseView> extends AppCompatActivity {
    protected T presenter;
    private String TAG = "BaseActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //绑定
        presenter = createPresenter();
        presenter.attchView((V)this);
        //将activity加入到列表中,方便管理
        ActivityController.addActivity(this);
    }

    /**
     * 创建presenter
     * @return
     */
    public abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        presenter.detachView();
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

    /**
     * 往适配器添加数据
     */
    public void addData(){

    }

    /**
     * 获得数据
     */
    public void getData(){

    }

}
