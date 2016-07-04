package com.haiyangroup.scampus.ui.contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.entity.ContactsChildEntity;
import com.haiyangroup.scampus.entity.ContactsGroupEntity;

import java.util.ArrayList;

/**
 * 联系人页面的联系人分组adapter实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mLayoutInflater;
    protected Context context;
    private ArrayList<ContactsGroupEntity> groupArray;
    private ArrayList<ArrayList<ContactsChildEntity>> childArray;

    public ExpandableListAdapter(Context context,  ArrayList<ContactsGroupEntity> groupArray, ArrayList<ArrayList<ContactsChildEntity>> childArray){
        this.context=context;
        this.groupArray = groupArray;
        this.childArray = childArray;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public ExpandableListAdapter(Context context){
        this.context=context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groupArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childArray.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_contacts_groupview, null);
            groupViewHolder.groupName=(TextView)convertView.findViewById(R.id.group_name);
            groupViewHolder.groupNum=(TextView)convertView.findViewById(R.id.group_num);
            groupViewHolder.arrow=(ImageView)convertView.findViewById(R.id.ico_arrow);
            groupViewHolder.arrowDown=(ImageView)convertView.findViewById(R.id.ico_arrow_down);
            convertView.setTag(groupViewHolder);
        }else{
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        groupViewHolder.groupName.setText(groupArray.get(groupPosition).getGroupName());
        groupViewHolder.groupNum.setText(groupArray.get(groupPosition).getNumber());
        if (isExpanded) {
            groupViewHolder.arrow.setVisibility(View.GONE);
            groupViewHolder.arrowDown.setVisibility(View.VISIBLE);
        }else {
            groupViewHolder.arrowDown.setVisibility(View.GONE);
            groupViewHolder.arrow.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        View view = convertView;
        if (view == null) {
            childViewHolder = new ChildViewHolder();
            view = mLayoutInflater.inflate(R.layout.item_contacts_childview, null);
            childViewHolder.headImage=(ImageView)view.findViewById(R.id.head_image);
            childViewHolder.sendMessage=(ImageView)view.findViewById(R.id.send_message);
            childViewHolder.phone=(ImageView)view.findViewById(R.id.phone);
            childViewHolder.name=(TextView)view.findViewById(R.id.name);
            childViewHolder.message=(TextView)view.findViewById(R.id.message);
            view.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder)view.getTag();
        }
        childViewHolder.name.setText(childArray.get(groupPosition).get(childPosition).getName());
        childViewHolder.message.setText(childArray.get(groupPosition).get(childPosition).getMessage());
        childViewHolder.sendMessage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + "18850207839"));
            context.startActivity(intent);
        });
        childViewHolder.phone.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"18850207839"));
            try {
                context.startActivity(intent);
            }catch (Exception e){

            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder{
        ImageView arrow;
        ImageView arrowDown;
        TextView groupName;
        TextView groupNum;
    }

    class ChildViewHolder{
        ImageView headImage;
        TextView name;
        TextView message;
        ImageView sendMessage;
        ImageView phone;
    }
}
