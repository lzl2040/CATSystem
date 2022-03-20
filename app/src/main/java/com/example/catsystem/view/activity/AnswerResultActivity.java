package com.example.catsystem.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.catsystem.R;
import com.example.catsystem.base.BaseActivity;
import com.example.catsystem.entity.Question;
import com.example.catsystem.view.adapter.QuestionResultAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试结果的界面
 */
public class AnswerResultActivity extends BaseActivity {
    private String TAG = "AnswerResultActivity";
    private List<Question> data = new ArrayList<>();
    private RecyclerView recyclerView;
    private Button testDataBtn;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_result);
        getData();
        initView();
        setListener();
        setAdapter();
    }

    @Override
    public void initView() {
        //super.initView();
        Log.e(TAG,"initView...");
        context = this;
        recyclerView = findViewById(R.id.recyclerview);
        testDataBtn = findViewById(R.id.test_data_btn);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.e(TAG,"setListener...");
        testDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.e(TAG,"setAdapter...");
        addData();
        //设置布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //设置适配器
        QuestionResultAdapter adapter = new QuestionResultAdapter(context,data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addData() {
        //super.addData();
        Log.e(TAG,"addData...");
    }

    @Override
    public void getData() {
        //super.getData();
        Log.e(TAG,"getData...");
    }
}