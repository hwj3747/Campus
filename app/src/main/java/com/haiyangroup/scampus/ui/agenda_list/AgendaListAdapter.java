package com.haiyangroup.scampus.ui.agenda_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.entity.AgendaEntity;

import java.util.ArrayList;

/**
 * 日程列表页面的adapter实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AgendaListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<AgendaEntity> mData;
    private Context mContext;
    private Boolean flag;

    public AgendaListAdapter(Context context, ArrayList<AgendaEntity> data,Boolean flag) {
        this.flag=flag;
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
            convertView = mInflater.inflate(R.layout.item_agenda, null);
            holder.delete=(ImageView)convertView.findViewById(R.id.delete);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.agendaName= (TextView) convertView.findViewById(R.id.agenda_name);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(flag){
            holder.delete.setVisibility(View.VISIBLE);
        }
        else{
            holder.delete.setVisibility(View.GONE);
        }
        holder.date.setText(mData.get(position).getDate());
        holder.agendaName.setText(mData.get(position).getAgendaName());
        holder.delete.setOnClickListener(v->{
            mData.remove(position);
            notifyDataSetChanged();
        });
        return convertView;
    }


    public final class ViewHolder {
        public TextView date;
        public TextView agendaName;
        public ImageView delete;
    }

    public void setFlag(Boolean mFlag){
        flag=mFlag;
    }
}