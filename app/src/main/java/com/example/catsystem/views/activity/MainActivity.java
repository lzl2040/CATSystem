package com.example.catsystem.views.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.catsystem.R;
import com.example.catsystem.base.BaseActivity;
import com.example.catsystem.presenter.MainPresenter;
import com.example.catsystem.util.StaticData;
import com.example.catsystem.util.ViewUtil;
import com.example.catsystem.views.fragment.MyFragment;
import com.example.catsystem.views.fragment.TestFragment;
import com.example.catsystem.views.view.IMainView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity<MainPresenter, IMainView> implements IMainView{
    private String TAG = ViewUtil.getClassName(this);
    private BottomNavigationView bottomNavigationView;
    //fragment管理器
    private FragmentManager fragmentManager;
    //fragment事务
    private FragmentTransaction fragmentTransaction;
    private Fragment preFragment = null;
    //存放fragment的Map
    private Map<Integer,Fragment> fragmentMap;
    //fragment数目
    private int nums = 2;
    //当前的fragment索引
    private int curId = 0;
    //当前fragment对应的int值
    private int fragmentIds[] = new int[]{R.id.test,R.id.my};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG,"onCreate...");
        initView();
        initFragment();
        setListener();
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void initView() {
        Log.e(TAG,"initView...");
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    @Override
    public void setListener() {
        Log.e(TAG,"setListener...");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //注意这个itemId很大
                Log.e(TAG,"item_id="+item.getItemId());
                StaticData.setBottomPosition(item.getItemId());
                return setFragmentTransaction(fragmentMap.get(item.getItemId()));
            }
        });
    }

    /**
     * 初始化fragment,将fragment存进map
     */
    private void initFragment(){
        Log.e(TAG,"initFragment...");
        fragmentManager = getSupportFragmentManager();
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.test, TestFragment.newInstance());
        fragmentMap.put(R.id.my, MyFragment.newInstance());
        curId = StaticData.getBottomPosition();
        if(curId == 0){
            curId = R.id.test;
        }
        Log.e(TAG,"curId:"+curId);
        //设置当前的
        setFragmentTransaction(fragmentMap.get(curId));

        //通过getItem的方式默认将最左便的导航设置成未选择
        //findItem根据id找相应的MenuItem
        bottomNavigationView.getMenu().findItem(curId).setChecked(true);

    }

    /**
     * 设置fragment切换的逻辑
     * @param nowFragment 当前的fragment
     * @return
     */
    private boolean setFragmentTransaction(Fragment nowFragment){
        Log.e(TAG,"setFragmentTransaction...");
        fragmentTransaction = fragmentManager.beginTransaction();
        if(nowFragment == null){
            Log.e(TAG, "setFragmentTransaction: nowFragment is null do nothing!");
            return false;
        }
        if(preFragment != null){
            //隐藏上一步的fragment
            fragmentTransaction.hide(preFragment);
            preFragment = nowFragment;
        }
        if(!nowFragment.isAdded()){
            fragmentManager.beginTransaction().remove(nowFragment).commit();
            fragmentTransaction.add(R.id.frame_container, nowFragment);
        }
        fragmentTransaction.show(nowFragment);
        //更新上一次的fragment
        preFragment=nowFragment;
        fragmentTransaction.commitAllowingStateLoss();
        return true;
    }

    @Override
    public void showErrorMessage(String msg, Context context) {

    }

    @Override
    public void showSuccessMessage(String msg, Context context) {

    }
}