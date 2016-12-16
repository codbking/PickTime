package com.codbking.example;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.bean.DateType;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void showDatePickDialog(DateType type) {
        DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(5);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(type);
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd HH:mm");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(null);
        dialog.show();
    }

    @OnClick({R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item1:
                showDatePickDialog(DateType.TYPE_ALL);
                break;
            case R.id.item2:
                showDatePickDialog(DateType.TYPE_YMDHM);
                break;
            case R.id.item3:
                showDatePickDialog(DateType.TYPE_YMDH);
                break;
            case R.id.item4:
                showDatePickDialog(DateType.TYPE_YMD);
                break;
            case R.id.item5:
                showDatePickDialog(DateType.TYPE_HM);
                break;
        }
    }
}
