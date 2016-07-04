package com.haiyangroup.scampus.ui.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.entity.AttendanceEntity;

import java.util.ArrayList;

/**
 * 学生考勤页面的adapter实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AttendanceAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<AttendanceEntity> mData;
    private Context mContext;

    public AttendanceAdapter(Context context, ArrayList<AttendanceEntity> data) {
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
            convertView = mInflater.inflate(R.layout.item_attendance, null);
            holder.day = (TextView) convertView.findViewById(R.id.day);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.course = (TextView) convertView.findViewById(R.id.course);
            holder.number = (TextView) convertView.findViewById(R.id.number);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.day.setText("星期"+mData.get(position).getWeek()+"("+mData.get(position).getTime()+")");
        holder.time.setText(mData.get(position).getStart()+"-"+mData.get(position).getEnd());
        holder.course.setText(mData.get(position).getLesson());
        holder.number.setText("共"+mData.get(position).getCount()+"条");

        return convertView;
    }


    public final class ViewHolder {
        public TextView course;
        public TextView day;
        public TextView time;
        public TextView number;
    }
}