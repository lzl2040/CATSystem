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
 * 问题回答完了之后使用的适配器
 */
public class QuestionResultAdapter extends RecyclerView.Adapter<QuestionResultAdapter.ViewHolder> {
    private Context context;
    private List<Question> data;

    public QuestionResultAdapter(Context context, List<Question> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question_result,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = data.get(position);
        //holder.explainTxt.setText(question.getExplanation());
        holder.questionTitleTxt.setText(question.getContent());
        holder.choiceABtn.setText(question.getChoiceA());
        holder.choiceBBtn.setText(question.getChoiceB());
        holder.choiceCBtn.setText(question.getChoiceC());
        holder.choiceDBtn.setText(question.getChoiceD());
        holder.explainTxt.setText(question.getExplanation());

        //根据选项判断正确，从而显示不同的样式
        if(question.getAnswer().equals(question.getUserAnswer())){
            //用户该题答对的情况
            holder.resultTxt.setText(R.string.answer_correct);
            holder.resultImg.setImageResource(R.mipmap.correct);
        }else {
            //用户没有答对的情况
            holder.resultTxt.setText("我的答案为:"+question.getUserAnswer()+" 正确答案为:"+question.getAnswer());
            holder.resultImg.setImageResource(R.mipmap.error);
        }
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
        TextView resultTxt,questionTitleTxt,explainTxt;
        ImageView resultImg;
        public ViewHolder(View view){
            super(view);
            choiceGroup = view.findViewById(R.id.choice_group);
            choiceABtn = view.findViewById(R.id.choice_A);
            choiceBBtn = view.findViewById(R.id.choice_B);
            choiceCBtn = view.findViewById(R.id.choice_C);
            choiceDBtn = view.findViewById(R.id.choice_D);
            resultTxt = view.findViewById(R.id.result_text);
            resultImg = view.findViewById(R.id.answer_result);
            questionTitleTxt = view.findViewById(R.id.question_title);
            explainTxt = view.findViewById(R.id.explanation);
        }
    }

}
