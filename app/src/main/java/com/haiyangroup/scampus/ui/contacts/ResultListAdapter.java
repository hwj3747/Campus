package com.haiyangroup.scampus.ui.contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.entity.ContactsChildEntity;

import java.util.ArrayList;

/**
 * 联系人页面的联系人搜索结果adapter实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class ResultListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<ContactsChildEntity> mData;
    private Context mContext;

    public ResultListAdapter(Context context, ArrayList<ContactsChildEntity> data) {
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
        ChildViewHolder childViewHolder;
        View view = convertView;
        if (view == null) {
            childViewHolder = new ChildViewHolder();
            view = mInflater.inflate(R.layout.item_contacts_childview, null);
            childViewHolder.headImage=(ImageView)view.findViewById(R.id.head_image);
            childViewHolder.sendMessage=(ImageView)view.findViewById(R.id.send_message);
            childViewHolder.phone=(ImageView)view.findViewById(R.id.phone);
            childViewHolder.name=(TextView)view.findViewById(R.id.name);
            childViewHolder.message=(TextView)view.findViewById(R.id.message);
            view.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder)view.getTag();
        }
        childViewHolder.name.setText(mData.get(position).getName());
        childViewHolder.message.setText(mData.get(position).getMessage());
        childViewHolder.sendMessage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + "18850207839"));
            mContext.startActivity(intent);
        });
        childViewHolder.phone.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "18850207839"));
            try {
                mContext.startActivity(intent);
            } catch (Exception e) {

            }
        });
        return view;
    }

    class ChildViewHolder{
        ImageView headImage;
        TextView name;
        TextView message;
        ImageView sendMessage;
        ImageView phone;
    }
}