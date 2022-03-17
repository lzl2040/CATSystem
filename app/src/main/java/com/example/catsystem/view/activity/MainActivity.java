package com.example.catsystem.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.catsystem.R;
import com.example.catsystem.base.BaseActivity;
import com.example.catsystem.util.ViewUtil;
import com.example.catsystem.view.fragment.MyFragment;
import com.example.catsystem.view.fragment.TestFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {
    private String TAG = ViewUtil.getClassName(this);
    private BottomNavigationView bottomNavigationView;
    //fragment管理器
    private FragmentManager fragmentManager;
    //fragment事务
    private FragmentTransaction fragmentTransaction;
    private Fragment preFragment = null;
    //存放fragment的Map
    Map<Integer,Fragment> fragmentMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        setListener();
    }

    @Override
    public void initView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    @Override
    public void setListener() {
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                setFragmentTransaction(fragmentMap.get(item.getItemId()));
            }
        });
    }

    /**
     * 初始化fragment,将fragment存进map
     */
    private void initFragment(){
        fragmentManager = getSupportFragmentManager();
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.test, TestFragment.newInstance());
        fragmentMap.put(R.id.my, MyFragment.newInstance());

        //设置当前的
        setFragmentTransaction(fragmentMap.get(R.id.test));

        //通过getItem的方式默认将最左便的导航设置成未选择
        bottomNavigationView.getMenu().getItem(0).setChecked(false);

        //通过findItem的方式将我们想要选择的导航按钮设置为选择（两种方式各有优点）
        bottomNavigationView.getMenu().findItem(R.id.test).setChecked(true);
    }

    /**
     * 设置fragment切换的逻辑
     * @param nowFragment 当前的fragment
     * @return
     */
    private boolean setFragmentTransaction(Fragment nowFragment){
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
}