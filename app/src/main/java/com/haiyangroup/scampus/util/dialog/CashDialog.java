package com.haiyangroup.scampus.util.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.haiyangroup.scampus.R;

/**
 * 自定义的清除缓存对话框，可以直接当组件在布局中使用
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class CashDialog extends Dialog {

    public CashDialog(Context context) {
        super(context, R.style.Dialog);
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_cash, null);
        super.setContentView(mView);
    }


}
