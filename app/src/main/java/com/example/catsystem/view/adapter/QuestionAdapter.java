package com.example.catsystem.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catsystem.R;
import com.example.catsystem.entity.Question;
import com.example.catsystem.util.StaticData;

import java.util.List;

/**
 * author ： yxm521
 * time    ： 2022/3/20
 * 回答问题时的适配器
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<Question> data;
    private Context context;

    public QuestionAdapter(List<Question> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = data.get(position);
        holder.questionTitleTxt.setText(question.getContent());
        holder.choiceABtn.setText(question.getChoiceA());
        holder.choiceBBtn.setText(question.getChoiceB());
        holder.choiceCBtn.setText(question.getChoiceC());
        holder.choiceDBtn.setText(question.getChoiceD());

        //设置选择组的点击事件
        holder.choiceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == holder.choiceABtn.getId()) {
                    StaticData.updateQuestion(position, "A");
                } else if (i == holder.choiceBBtn.getId()) {
                    StaticData.updateQuestion(position, "B");
                } else if (i == holder.choiceCBtn.getId()) {
                    StaticData.updateQuestion(position, "C");
                } else if (i == holder.choiceDBtn.getId()) {
                    StaticData.updateQuestion(position, "D");
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * 单个问题item里面的控件
     */
    static class ViewHolder extends RecyclerView.ViewHolder{
        RadioGroup choiceGroup;
        RadioButton choiceABtn,choiceBBtn,choiceCBtn,choiceDBtn;
        TextView questionTitleTxt;
        public ViewHolder(View view){
            super(view);
            choiceGroup = view.findViewById(R.id.choice_group);
            choiceABtn = view.findViewById(R.id.choice_A);
            choiceBBtn = view.findViewById(R.id.choice_B);
            choiceCBtn = view.findViewById(R.id.choice_C);
            choiceDBtn = view.findViewById(R.id.choice_D);
            questionTitleTxt = view.findViewById(R.id.question_title);
        }
    }
}
