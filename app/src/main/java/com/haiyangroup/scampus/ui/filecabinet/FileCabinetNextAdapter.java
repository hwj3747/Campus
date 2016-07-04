package com.haiyangroup.scampus.ui.filecabinet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.entity.FolderEntity;

import java.util.ArrayList;

/**
 * 个人文件柜下一级页面的adapter实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class FileCabinetNextAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<FolderEntity> mData;
    private Context mContext;

    public FileCabinetNextAdapter(Context context, ArrayList<FolderEntity> data) {
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
            convertView = mInflater.inflate(R.layout.item_file_cabinet_next, null);
            holder.name = (TextView) convertView.findViewById(R.id.file_name);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(mData.get(position).getName());

        return convertView;
    }


    public final class ViewHolder {
        public TextView name;
    }
}