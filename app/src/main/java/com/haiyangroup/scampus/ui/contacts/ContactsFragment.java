package com.haiyangroup.scampus.ui.contacts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.ContactsChildEntity;
import com.haiyangroup.scampus.entity.ContactsGroupEntity;
import com.haiyangroup.scampus.present.ContactsPresenter;
import com.haiyangroup.scampus.view.ContactsView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;


/**
 * 联系人页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class ContactsFragment extends BaseFragment<ContactsView, ContactsComponent, ContactsPresenter> implements ContactsView {

    @Inject
    ContactsPresenter presenter;
    @InjectView(R.id.search_text)
    EditText searchText;
    @InjectView(R.id.expand_listView)
    ExpandableListView expandListView;

    @InjectView(R.id.result_list)
    ListView resultListView;

    ExpandableListAdapter expandableListAdapter;
    ResultListAdapter resultListAdapter;
    public ContactsFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contacts;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.contacts);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {
        ContactsGroupEntity contactsGroupEntity=new ContactsGroupEntity();
        contactsGroupEntity.setGroupName("同事");
        contactsGroupEntity.setNumber("4人");
        ArrayList<ContactsGroupEntity> group=new ArrayList<>();
        group.add(contactsGroupEntity);
        group.add(contactsGroupEntity);
        group.add(contactsGroupEntity);
        group.add(contactsGroupEntity);


        ContactsChildEntity contactsChildEntity=new ContactsChildEntity();
        contactsChildEntity.setName("周杰伦");
        contactsChildEntity.setMessage("你好");
        ArrayList<ContactsChildEntity> child=new ArrayList<>();
        child.add(contactsChildEntity);
        child.add(contactsChildEntity);
        child.add(contactsChildEntity);
        child.add(contactsChildEntity);

        ArrayList<ArrayList<ContactsChildEntity>> childList=new ArrayList<>();
        childList.add(child);
        childList.add(child);
        childList.add(child);
        childList.add(child);
        expandableListAdapter=new ExpandableListAdapter(getBaseContext(),group,childList);
        expandListView.setAdapter(expandableListAdapter);


        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(searchText.getText().toString().equals("")){
                    expandListView.setVisibility(View.VISIBLE);
                    resultListView.setVisibility(View.GONE);
                }
                else{
                    expandListView.setVisibility(View.GONE);
                    resultListView.setVisibility(View.VISIBLE);
                    resultListAdapter=new ResultListAdapter(getBaseContext(),searchByName(searchText.getText().toString()));
                    resultListView.setAdapter(resultListAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected ContactsComponent onCreateNonConfigurationComponent() {
        return DaggerContactsComponent.builder()
                .appComponent(getAppComponent(mActivity))
                .build();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save state of all @State annotated members
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    private ArrayList<ContactsChildEntity> searchByName(String name){
        ArrayList<ContactsChildEntity> resultList=new ArrayList<>();
        ArrayList<String> nameList=new ArrayList<>();
        nameList.add("周杰伦");
        nameList.add("林俊杰");
        nameList.add("王力宏");
        nameList.add("周杰");
        nameList.add("刘欢");
        nameList.add("张杰");

        for(String item:nameList){
            if(item.contains(name)){
                ContactsChildEntity contactsChildEntity=new ContactsChildEntity();
                contactsChildEntity.setName(item);
                contactsChildEntity.setMessage("你好");
                resultList.add(contactsChildEntity);
            }
        }

        return  resultList;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
