package com.haiyangroup.scampus.ui.scoreresult;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.entity.ScoreEntity;

import java.util.ArrayList;

/**
 * 成绩查询结果页面的adapter实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class ScoreAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<ScoreEntity> mData;
    private  Context mContext;
    public ScoreAdapter(Context context, ArrayList<ScoreEntity> data){
        mContext=context;
        mData=data;
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mData.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {

            holder=new ViewHolder();

            convertView = mInflater.inflate(R.layout.item_score_result, null);
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.studengId = (TextView)convertView.findViewById(R.id.studentId);
            holder.score = (TextView)convertView.findViewById(R.id.score);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.name.setText(mData.get(position).getName());
        holder.studengId.setText(mData.get(position).getStudentNo());
        holder.score.setText(mData.get(position).getScore());
        if(position%2==0){
            holder.name.setBackgroundColor(mContext.getResources().getColor(R.color.column_color));
            holder.studengId.setBackgroundColor(mContext.getResources().getColor(R.color.column_color));
            holder.score.setBackgroundColor(mContext.getResources().getColor(R.color.column_color));
        }
        else{
            holder.name.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.studengId.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.score.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        return convertView;
    }


    public final class ViewHolder{
        public TextView name;
        public TextView studengId;
        public TextView score;
    }
}