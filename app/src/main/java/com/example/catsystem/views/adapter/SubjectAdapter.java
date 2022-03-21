package com.example.catsystem.views.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.catsystem.R;
import com.example.catsystem.entity.Subject;

import java.util.List;

public class SubjectAdapter extends BaseAdapter {
    private List<Subject> data;
    private Context context;
    private String TAG = "SubjectAdapter";

    public SubjectAdapter(List<Subject> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.e(TAG,"getView...");
        ViewHolder holder = null;
        if(view != null){
            holder = (ViewHolder) view.getTag();
        }else{
            //要这样设置才能适应item高度
            //如果是view = LayoutInflater.from(context).inflate(R.layout.item_subject,null);
            view = LayoutInflater.from(context).inflate(R.layout.item_subject,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.subjectNameTxt.setText(data.get(i).getName());
        holder.subjectImg.setImageResource(data.get(i).getSubject_img());
//        //设置高度,注意这个不是dp
//        int height = 400;
//        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height);
//        view.setLayoutParams(params);
        return view;
    }

    static class ViewHolder{
        private TextView subjectNameTxt;
        private ImageView subjectImg;
        public ViewHolder(View view){
            subjectNameTxt = view.findViewById(R.id.subject_name);
            subjectImg = view.findViewById(R.id.subject_img);
        }
    }
}
