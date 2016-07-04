package com.haiyangroup.scampus.ui.homework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.entity.HomeworkEntity;

import java.util.ArrayList;

/**
 * 布置作业页面的adapter实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class HomeworkAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<HomeworkEntity> mData;
    private Context mContext;

    public HomeworkAdapter(Context context, ArrayList<HomeworkEntity> data) {
        mContext = context;
        mData = data;
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
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_homework, null);
           // holder.className = (TextView) convertView.findViewById(R.id.className);
            holder.time = (TextView) convertView.findViewById(R.id.day);
            holder.course = (TextView) convertView.findViewById(R.id.course);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.className.setText(mData.get(position).getLesson());
        holder.time.setText(mData.get(position).getTime()+" 星期"+mData.get(position).getWeek());
        holder.course.setText(mData.get(position).getLesson());
        return convertView;
    }


    public final class ViewHolder {
        public TextView course;
        public TextView className;
        public TextView time;
    }
}