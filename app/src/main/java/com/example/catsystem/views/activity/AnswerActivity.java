package com.example.catsystem.views.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.catsystem.R;
import com.example.catsystem.base.BaseActivity;
import com.example.catsystem.entity.Question;
import com.example.catsystem.presenter.AnswerPresenter;
import com.example.catsystem.util.Constant;
import com.example.catsystem.util.StaticData;
import com.example.catsystem.util.ViewUtil;
import com.example.catsystem.views.adapter.QuestionAdapter;
import com.example.catsystem.views.view.IAnswerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 回答界面
 */
public class AnswerActivity extends BaseActivity<AnswerPresenter, IAnswerView> {
    private String TAG = "AnswerActivity";
    private RecyclerView recyclerView;
    private ImageView backImg;
    private Button submitBtn;
    private Context context;
    private List<Question> data = new ArrayList<>();
    //选择的科目
    private int selectedSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Log.e(TAG,"onCreate...");
        getData();
        initView();
        setListener();
        setAdapter();
    }

    @Override
    public AnswerPresenter createPresenter() {
        return new AnswerPresenter();
    }

    @Override
    public void initView() {
        //super.initView();
        Log.e(TAG,"initView...");
        context = this;
        recyclerView = findViewById(R.id.recyclerview);
        backImg = findViewById(R.id.back);
        submitBtn = findViewById(R.id.submit_btn);
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

        //设置提交按钮的点击监听事件
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"click:submitBtn");
                boolean isAllSeleted = checkAllQuestionSelected();
                if(isAllSeleted){
                    ViewUtil.jumpTo(context,AnswerResultActivity.class);
                    finish();
                }else{
                    ViewUtil.showNotice(getString(R.string.has_quesion_no_select),context);
                }
            }
        });
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.e(TAG,"setAdapter...");
        addData();
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置适配器
        QuestionAdapter adapter = new QuestionAdapter(data,context);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addData() {
        //super.addData();
        Log.e(TAG,"addData...");
        for(int i = 0;i < 5;i++){
            Question question = new Question();
            question.setContent("123");
            question.setChoiceA("A.123");
            question.setChoiceB("B.123");
            question.setChoiceC("C.123");
            question.setChoiceD("D.123");
            question.setAnswer("A");
            question.setExplanation("2324");
            data.add(question);
        }
        //保存进数据流传工具类里面,方便之后取出
        StaticData.setQuestions(data);
    }

    @Override
    public void getData() {
        //super.getData();
        Log.e(TAG,"getData...");
        //获得当前选择的科目是哪一个
        Intent intent = getIntent();
        selectedSubject = intent.getIntExtra("selectSubject",0);
    }

    /**
     * 检查所有问题是否被选中
     * @return 都选中返回true,否则返回false
     */
    public boolean checkAllQuestionSelected(){
        List<Question> questions = StaticData.getQuestions();
        for(Question question:questions){
            if(question.getUserAnswer().equals(Constant.noSelect)){
                return false;
            }
        }
        return true;
    }
}