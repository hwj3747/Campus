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
import com.haiyangroup.scampus.util.TimeUtil;

import java.lang.reflect.Field;

/**
 * 自定义的有4个选项的滑动选择框，可以直接当组件在布局中使用
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class ThreeNumberPickerDialog {

    Context context;
    String[] listShow1;
    String[] listShow2;
    int days;
    String dataInfo;
    AlertDialog al;
    Window window;
    NumberPicker np1;
    NumberPicker np2;
    NumberPicker np3;
    TextView sure;
    TextView cancel;
    PickListen pickListen;

    public ThreeNumberPickerDialog(Context context, int days,String[] listShow1, String[] listShow2, PickListen pickListen) {
        this.context = context;
        this.days=days;
        this.listShow1 = listShow1;
        this.listShow2 = listShow2;
        this.pickListen=pickListen;
        init();
    }

    public void init() {
        al = new AlertDialog.Builder(context,R.style.PickDialog).create();
        al.show();
        window = al.getWindow();
        window.setContentView(R.layout.pickdialog_three);
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
        np1 = (NumberPicker) window.findViewById(R.id.picker1);
        np2 = (NumberPicker) window.findViewById(R.id.picker2);
        np3 = (NumberPicker) window.findViewById(R.id.picker3);

        sure=(TextView)window.findViewById(R.id.sure);
        cancel=(TextView)window.findViewById(R.id.cancel);

        String[] list1 = TimeUtil.allDay(days);
        np1.setDisplayedValues(list1);
        np1.setMaxValue(list1.length - 1);
        np1.setMinValue(0);
        np1.setValue(0);
        setNumberPickerTextColor(np1, context.getResources().getColor(R.color.black));
        setNumberPickerDividerColor(np1);

        np1.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                switch (scrollState) {
                    case NumberPicker.OnScrollListener.SCROLL_STATE_IDLE:
                    default:
                        break;
                }
            }
        });

        String[] MyList1 = listShow1;
        dataInfo = MyList1[0];
//        np2.setWrapSelectorWheel(false);
        np2.setDisplayedValues(MyList1);
        np2.setMaxValue(MyList1.length - 1);
        np2.setMinValue(0);
        np2.setValue(0);
        setNumberPickerTextColor(np2, context.getResources().getColor(R.color.black));
        setNumberPickerDividerColor(np2);

        String[] MyList2 = listShow2;
        dataInfo = MyList2[0];
//        np2.setWrapSelectorWheel(false);
        np3.setDisplayedValues(MyList2);
        np3.setMaxValue(MyList2.length - 1);
        np3.setMinValue(0);
        np3.setValue(0);
        setNumberPickerTextColor(np3, context.getResources().getColor(R.color.black));
        setNumberPickerDividerColor(np3);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickListen.show(np1.getDisplayedValues()[np1.getValue()]  + " " + np2.getDisplayedValues()[np2.getValue()] + ":" + np3.getDisplayedValues()[np3.getValue()]);
                al.dismiss();
            }
        });

        cancel.setOnClickListener(v->{
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
