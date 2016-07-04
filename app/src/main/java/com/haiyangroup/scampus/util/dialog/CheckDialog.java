package com.haiyangroup.scampus.util.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.util.rximageloader.RxImageLoader;

/**
 * 自定义的请假批准对话框，可以直接当组件在布局中使用
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class CheckDialog extends Dialog {

    ImageView headImage;
    TextView name,day,reason,sure,cancel,pass,noPass;
    PickListen pickListen;
    String headImageStr,nameStr,dayStr,reasonStr;
    Boolean flag=true;

    public CheckDialog(Context context,String mHeadImage,String mName,String mDay,String mReason,PickListen mPickListen) {
        super(context, R.style.Dialog);
        headImageStr=mHeadImage;
        nameStr=mName;
        dayStr=mDay;
        reasonStr=mReason;
        pickListen=mPickListen;
        setCustomDialog();
    }

    /**
     * 初始化对话框
     * @return void
     */
    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_check, null);
        headImage = (ImageView) mView.findViewById(R.id.head_image);
        name=(TextView) mView.findViewById(R.id.name);
        day=(TextView) mView.findViewById(R.id.day);
        reason=(TextView) mView.findViewById(R.id.reason);
        sure=(TextView) mView.findViewById(R.id.sure);
        cancel= (TextView) mView.findViewById(R.id.cancel);
        pass= (TextView) mView.findViewById(R.id.pass);
        noPass=(TextView)mView.findViewById(R.id.noPass);

        headImage.setTag(headImageStr);
        RxImageLoader.getLoaderObservable(headImage,headImageStr,"").subscribe();
        pass.setOnClickListener(v -> {
            pass.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.btn_leave_pass_on), null, null, null);
            noPass.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.btn_leave_no_pass), null, null, null);
            flag=true;
        });

        noPass.setOnClickListener(v->{
            pass.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.btn_leave_pass), null, null, null);
            noPass.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.btn_leave_no_pass_on), null, null, null);
            flag=false;
        });
        name.setText(nameStr);
        day.setText(dayStr);
        reason.setText(reasonStr);
        sure.setOnClickListener(v->{
            pickListen.show(flag+"");
            CheckDialog.this.dismiss();
        });
        cancel.setOnClickListener(v -> {
            CheckDialog.this.dismiss();
        });
        super.setContentView(mView,new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void setContentView(int layoutResID) {
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
    }

    @Override
    public void setContentView(View view) {
    }

}
