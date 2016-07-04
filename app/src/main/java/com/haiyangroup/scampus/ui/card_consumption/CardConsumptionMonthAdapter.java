package com.haiyangroup.scampus.ui.card_consumption;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.entity.CardConsumptionEntity;

import java.util.ArrayList;

/**
 * 校园卡消费详情页面的adapter实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class CardConsumptionMonthAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<CardConsumptionEntity> mData;
    private Context mContext;

    public CardConsumptionMonthAdapter(Context context, ArrayList<CardConsumptionEntity> data) {
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
            convertView = mInflater.inflate(R.layout.item_card_consumption_month, null);
            holder.consumption_num = (TextView) convertView.findViewById(R.id.consumption_num);
            holder.balance_num = (TextView) convertView.findViewById(R.id.balance_num);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.expend_name = (TextView) convertView.findViewById(R.id.expend_name);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.date.setText(mData.get(position).getDate());
        holder.consumption_num.setText(mData.get(position).getConsumption_num());
        holder.balance_num.setText(mData.get(position).getBalance_num());
        holder.expend_name.setText(mData.get(position).getExpend_name());
        return convertView;
    }


    public final class ViewHolder {
        public TextView date;
        public TextView consumption_num;
        public TextView balance_num;
        public TextView expend_name;
    }
}