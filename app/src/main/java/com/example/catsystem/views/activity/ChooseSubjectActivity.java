package com.example.catsystem.views.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.catsystem.R;
import com.example.catsystem.base.BaseActivity;
import com.example.catsystem.entity.Subject;
import com.example.catsystem.presenter.ChooseSubjectPresenter;
import com.example.catsystem.util.ViewUtil;
import com.example.catsystem.views.adapter.SubjectAdapter;
import com.example.catsystem.views.view.IChooseSubjectView;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择科目界面
 */
public class ChooseSubjectActivity extends BaseActivity<ChooseSubjectPresenter, IChooseSubjectView> {
    private String TAG = "ChooseSubjectActivity";
    private GridView subjectGrid;
    private ImageView backImg;
    private List<Subject> data = new ArrayList<>();
    private String subjects[] = new String[]{"计算机组成原理","马克思主义原理","计算机组成原理"};
    private int subjectImgIds[] = new int[]{R.mipmap.jizu,R.mipmap.jizu,R.mipmap.jizu};
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subject);
        Log.e(TAG,"onCreate...");
        initView();
        setListener();
        setAdapter();
    }

    @Override
    public ChooseSubjectPresenter createPresenter() {
        return new ChooseSubjectPresenter();
    }

    @Override
    public void initView() {
        //super.initView();
        Log.e(TAG,"initView...");
        context = this;
        subjectGrid = findViewById(R.id.subject_list);
        backImg = findViewById(R.id.back);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.e(TAG,"setListener");
        //设置gridView每个item的点击事件
        subjectGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e(TAG,"选择了:"+subjects[i]);
                Intent intent = new Intent(context,AnswerActivity.class);
                intent.putExtra("selectSubject",i);
                startActivity(intent);
                //finish();
            }
        });

        //设置返回图片的点击事件
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(context,MainActivity.class);
                finish();
            }
        });
    }

    @Override
    public void setAdapter(){
        //super.setAdapter();
        Log.e(TAG,"setAdapter");
        addData();
        SubjectAdapter adapter = new SubjectAdapter(data,context);
        subjectGrid.setAdapter(adapter);
    }

    /**
     * 添加适配器的数据
     */
    public void addData(){
        for(int i = 0;i < subjects.length;i++){
            Subject subject = new Subject(subjects[i],subjectImgIds[i]);
            data.add(subject);
        }
    }
}