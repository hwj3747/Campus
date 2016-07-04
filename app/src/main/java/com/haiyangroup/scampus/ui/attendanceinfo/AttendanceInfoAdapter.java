package com.haiyangroup.scampus.ui.attendanceinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.entity.AttendanceInfoEntity;

import java.util.ArrayList;

/**
 * 学生考勤详情页面的adapter实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AttendanceInfoAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<AttendanceInfoEntity> mData;
    private Context mContext;

    public AttendanceInfoAdapter(Context context, ArrayList<AttendanceInfoEntity> data) {
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
            convertView = mInflater.inflate(R.layout.item_attendance_info, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.className = (TextView) convertView.findViewById(R.id.className);
            holder.reason = (TextView) convertView.findViewById(R.id.reason);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(position+1+":"+mData.get(position).getName()+"("+mData.get(position).getStudentNo()+")");
        holder.className.setText(mData.get(position).getClassName());
        holder.reason.setText(mData.get(position).getReason());
        return convertView;
    }


    public final class ViewHolder {
        public TextView name;
        public TextView className;
        public TextView reason;
    }
}