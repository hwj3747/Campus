package com.haiyangroup.scampus.util.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.haiyangroup.scampus.R;

import java.lang.reflect.Field;

/**
 * 自定义的只有一个选项的滑动选择框，可以直接当组件在布局中使用
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class OneNumberPickerDialog {

    Context context;
    String[] listShow;
    String dataInfo;
    AlertDialog al;
    Window window;
    NumberPicker np;
    TextView sure;
    TextView cancel;
    PickListen pickListen;

    public OneNumberPickerDialog(Context context, String[] listShow, PickListen pickListen) {
        this.context = context;
        this.listShow = listShow;
        this.pickListen = pickListen;
        init();
    }

    public void init() {
        al = new AlertDialog.Builder(context,R.style.PickDialog).create();
        al.show();
        window = al.getWindow();
       // window.getDecorView().setPadding(0, 0, 0, 0);
        window.setContentView(R.layout.pickdialog_one);
        al.getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();

        params.width = WindowManager.LayoutParams.MATCH_PARENT;// 设置两边无缝隙
        window.setAttributes(params);
    }

    /**
     * 初始化滑动选择器
     * @return void
     */
    public void showDialog() {
        np = (NumberPicker) window.findViewById(R.id.picker);
        sure = (TextView) window.findViewById(R.id.sure);
        cancel = (TextView) window.findViewById(R.id.cancel);
        String[] MyList = listShow;
        dataInfo = MyList[0];
        np.setWrapSelectorWheel(false);

        np.setDisplayedValues(MyList);
        np.setMaxValue(MyList.length - 1);
        np.setMinValue(0);
        np.setValue(0);

        setNumberPickerTextColor(np, context.getResources().getColor(R.color.black));
        setNumberPickerDividerColor(np);
        //  setNumberPickerTextColor(np,context.getResources().getColor(R.color.purple));
        np.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                switch (scrollState) {
                    case NumberPicker.OnScrollListener.SCROLL_STATE_IDLE:
                        dataInfo = view.getDisplayedValues()[view.getValue()];
                        break;
                    default:
                        break;
                }
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickListen.show(dataInfo);
                al.dismiss();
            }
        });

        cancel.setOnClickListener(v -> {
            al.dismiss();
        });
    }

    /**
     * 设置滑动选择器的文本颜色
     * @param numberPicker，设置的选择器<br>
     * @param color 设置的颜色
     * @return boolean
     */
    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        boolean result = false;
        final int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText) child).setTextColor(color);
                    numberPicker.invalidate();
                    result = true;
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 设置滑动选择器的分割线颜色
     * @param numberPicker，设置的选择器<br>
     * @return void
     */
    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        NumberPicker picker = numberPicker;
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值
                    pf.set(picker, new ColorDrawable(context.getResources().getColor(R.color.pick_background)));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
