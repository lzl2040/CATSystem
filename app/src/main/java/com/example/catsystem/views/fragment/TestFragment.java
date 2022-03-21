package com.example.catsystem.views.fragment;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.catsystem.R;
import com.example.catsystem.base.BaseFragment;
import com.example.catsystem.util.ViewUtil;
import com.example.catsystem.views.activity.ChooseSubjectActivity;

public class TestFragment extends BaseFragment {
    private String TAG = "TestFragment";
    private LottieAnimationView animationView;
    private TextView startTxt;
    public TestFragment() {
        // Required empty public constructor
    }

    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
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
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        initView(view);
        setListener();
        return view;
    }

    @Override
    public void initView(View view) {
        //super.initView(view);
        Log.e(TAG,"initView...");
        startTxt = view.findViewById(R.id.start_test);
        animationView = view.findViewById(R.id.lottieAnimationView);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.e(TAG,"setListener...");
        //设置开始答题的点击事件
        startTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(getActivity(), ChooseSubjectActivity.class);
            }
        });
    }
}