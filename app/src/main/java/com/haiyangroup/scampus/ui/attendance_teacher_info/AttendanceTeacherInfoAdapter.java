package com.haiyangroup.scampus.ui.attendance_teacher_info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.entity.AttendanceTeacherInfoEntity;

import java.util.ArrayList;

/**
 * 教师出勤详细页面的adapter实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AttendanceTeacherInfoAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<AttendanceTeacherInfoEntity> mData;
    private Context mContext;

    public AttendanceTeacherInfoAdapter(Context context, ArrayList<AttendanceTeacherInfoEntity> data) {
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
            convertView = mInflater.inflate(R.layout.item_attendance_teacher_info, null);
            holder.className = (TextView) convertView.findViewById(R.id.className);
            holder.section = (TextView) convertView.findViewById(R.id.section);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.className.setText(mData.get(position).getClassName());
        holder.section.setText(mData.get(position).getSection());
        return convertView;
    }


    public final class ViewHolder {
        public TextView className;
        public TextView section;
    }
}