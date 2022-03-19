package com.example.catsystem.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.catsystem.R;
import com.example.catsystem.base.BaseFragment;
import com.example.catsystem.util.ViewUtil;
import com.example.catsystem.view.activity.ErrorSetsActivity;

public class MyFragment extends BaseFragment {
    private RelativeLayout errorSets,studyData,aboutUs;
    private TextView usernameTxt;
    private ImageView userHeader;
    private Button testSituationBtn,contactBtn;
    private ImageView settingImg;
    private String TAG = "MyFragment";

    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(TAG,"onCreateView...");
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        setListener();
        return view;
    }

    @Override
    public void initView(View view) {
        //super.initView(view);
        Log.e(TAG,"initView...");
        errorSets = view.findViewById(R.id.error_problem);
        studyData = view.findViewById(R.id.study_data);
        aboutUs = view.findViewById(R.id.about);
        usernameTxt = view.findViewById(R.id.username);
        userHeader = view.findViewById(R.id.header);
        testSituationBtn = view.findViewById(R.id.test_situation);
        contactBtn = view.findViewById(R.id.contact);
        settingImg = view.findViewById(R.id.setting);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.e(TAG,"setListener...");
        //设置点击错题集的监听事件
        errorSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(getActivity(), ErrorSetsActivity.class);
            }
        });

        //设置点击答题数据的监听事件
        studyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //设置点击关于我们的监听事件
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //设置点击设置的监听事件
        settingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //设置点击答题情况的监听事件
        testSituationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //设置点击联系我们的监听事件
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}