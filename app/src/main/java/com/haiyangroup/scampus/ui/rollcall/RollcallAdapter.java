package com.haiyangroup.scampus.ui.rollcall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.entity.RollEntity;

import java.util.ArrayList;

/**
 * 点名页面的adapter实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class RollcallAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<RollEntity> mData;
    private Context mContext;

    public RollcallAdapter(Context context, ArrayList<RollEntity> data) {
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
            convertView = mInflater.inflate(R.layout.item_rollcall, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.studentId = (TextView) convertView.findViewById(R.id.studentId);
            holder.headImg = (ImageView) convertView.findViewById(R.id.head_image);

            holder.absent=(CheckBox)convertView.findViewById(R.id.absent);
            holder.attendance=(CheckBox)convertView.findViewById(R.id.attendance);
            holder.book=(CheckBox)convertView.findViewById(R.id.book);
            holder.chat=(CheckBox)convertView.findViewById(R.id.chat);
            holder.eat=(CheckBox)convertView.findViewById(R.id.eat);
            holder.late=(CheckBox)convertView.findViewById(R.id.late);
            holder.leave=(CheckBox)convertView.findViewById(R.id.leave);
            holder.play=(CheckBox)convertView.findViewById(R.id.play);
            holder.sleep=(CheckBox)convertView.findViewById(R.id.sleep);
            holder.uniform=(CheckBox)convertView.findViewById(R.id.uniform);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(mData.get(position).getName());
        holder.studentId.setText(mData.get(position).getStudentId());
//        holder.attendance.setOnCheckedChangeListener((buttonView, isChecked) ->
//            mData.get(position).setAttendance(isChecked)
//        );
//        holder.absent.setOnCheckedChangeListener((buttonView, isChecked) ->
//            mData.get(position).setAbsent(isChecked)
//        );
//        holder.uniform.setOnCheckedChangeListener((buttonView, isChecked) ->
//            mData.get(position).setUniform(isChecked)
//        );
//        holder.sleep.setOnCheckedChangeListener((buttonView, isChecked) ->
//            mData.get(position).setSleep(isChecked)
//        );
//        holder.play.setOnCheckedChangeListener((buttonView, isChecked) ->
//            mData.get(position).setPlay(isChecked)
//        );
//        holder.leave.setOnCheckedChangeListener((buttonView, isChecked) ->
//            mData.get(position).setLeave(isChecked)
//        );
//        holder.late.setOnCheckedChangeListener((buttonView, isChecked) ->
//            mData.get(position).setLate(isChecked)
//        );
//        holder.book.setOnCheckedChangeListener((buttonView, isChecked) ->
//            mData.get(position).setBook(isChecked)
//        );
//        holder.chat.setOnCheckedChangeListener((buttonView, isChecked) ->
//            mData.get(position).setChat(isChecked)
//        );
//        holder.eat.setOnCheckedChangeListener((buttonView, isChecked) ->
//            mData.get(position).setEat(isChecked)
//        );
        return convertView;
    }

    public final class ViewHolder {
        public ImageView headImg;
        public TextView name;
        public TextView studentId;
        public CheckBox attendance;
        public CheckBox late;
        public CheckBox absent;
        public CheckBox leave;
        public CheckBox sleep;
        public CheckBox play;
        public CheckBox uniform;
        public CheckBox chat;
        public CheckBox eat;
        public CheckBox book;

    }
}