package com.haiyangroup.scampus.ui.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.entity.CalendarEntity;
import com.haiyangroup.scampus.util.TimeUtil;
import com.haiyangroup.scampus.util.rximageloader.RxImageLoader;

import java.util.ArrayList;

/**
 * 校历页面的adapter实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class CalendarAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<CalendarEntity> mData;
    private Context mContext;

    public CalendarAdapter(Context context, ArrayList<CalendarEntity> data) {
        mContext = context;
        mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mData.size()+1;
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
        if (getItemViewType(position) == 0) {
            convertView = mInflater.inflate(R.layout.item_calendar_bannar, null);
        } else {
            if (convertView == null) {

                holder = new ViewHolder();

                convertView = mInflater.inflate(R.layout.item_calendar_bottom, null);
                holder.day = (TextView) convertView.findViewById(R.id.calendar_day);
                holder.imgPic = (ImageView) convertView.findViewById(R.id.calendar_picture);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.imgPic.setTag(mData.get(position-1).getPicture());
            RxImageLoader.getLoaderObservable(holder.imgPic,mData.get(position-1).getPicture(),"").subscribe();
           // holder.imgPic.setImageDrawable(mContext.getResources().getDrawable(Integer.parseInt(mData.get(position-1).getCalendarPic())));
            holder.day.setText(mData.get(position-1).getTitle()+"【"+ TimeUtil.translateTime(mData.get(position - 1).getStart())+"~"+TimeUtil.translateTime(mData.get(position-1).getEnd())+"】");
        }
        return convertView;
    }


    public final class ViewHolder {
        public ImageView imgPic;
        public TextView day;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        else
            return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}