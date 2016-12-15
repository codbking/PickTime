package com.codbking.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

import com.codbking.widget.bean.DateType;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by codbking on 2016/8/11.
 */
public class DatePickDialog extends Dialog implements OnChangeLisener {

    private TextView titleTv;
    private FrameLayout wheelLayout;
    private TextView cancel;
    private TextView sure;
    private TextView messgeTv;

    private String title;
    private String format;
    private DateType type = DateType.TYPE_ALL;

    //开始时间
    private Date startDate = new Date();
    //年分限制，默认上下5年
    private int yearLimt = 5;

    private OnChangeLisener onChangeLisener;

    private OnSureLisener onSureLisener;

    private DatePicker mDatePicker;

    //设置标题
    public void setTitle(String title) {
       this.title=title;
    }

    //设置模式
    public void setType(DateType type) {
        this.type = type;
    }

    //设置选择日期显示格式，设置显示message,不设置不显示message
    public void setMessageFormat(String format) {
        this.format = format;
    }

    //设置开始时间
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    //设置年份限制，上下年份
    public void setYearLimt(int yearLimt) {
        this.yearLimt = yearLimt;
    }

    //设置选择回调
    public void setOnChangeLisener(OnChangeLisener onChangeLisener) {
        this.onChangeLisener = onChangeLisener;
    }

    //设置点击确定按钮，回调
    public void setOnSureLisener(OnSureLisener onSureLisener) {
        this.onSureLisener = onSureLisener;
    }

    public DatePickDialog(Context context) {
        super(context, R.style.dialog_style);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cbk_dialog_pick_time);

        initView();
        initParas();
    }

    private DatePicker getDatePicker() {
        DatePicker picker = new DatePicker(getContext(), type);
        picker.setStartDate(startDate);
        picker.setYearLimt(yearLimt);
        picker.setOnChangeLisener(this);
        picker.init();
        return picker;
    }

    private void initView() {
        this.sure = (TextView) findViewById(R.id.sure);
        this.cancel = (TextView) findViewById(R.id.cancel);
        this.wheelLayout = (FrameLayout) findViewById(R.id.wheelLayout);
        this.titleTv = (TextView) findViewById(R.id.title);
        messgeTv = (TextView) findViewById(R.id.message);

        mDatePicker = getDatePicker();
        this.wheelLayout.addView(mDatePicker);

        //setValue
        this.titleTv.setText(title);

        this.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        this.sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onSureLisener != null) {
                    onSureLisener.onSure(mDatePicker.getSelectDate());
                }
            }
        });
    }

    private void initParas() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = DateUtils.getScreenWidth(getContext());
        getWindow().setAttributes(params);
    }

    @Override
    public void onChanged(Date date) {

        if (onChangeLisener != null) {
            onChangeLisener.onChanged(date);
        }

        if (!TextUtils.isEmpty(format)) {
            String messge = "";
            try {
                messge = new SimpleDateFormat(format).format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            messgeTv.setText(messge);
        }
    }


}
