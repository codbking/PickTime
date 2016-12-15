package com.codbking.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.codbking.widget.view.OnWheelChangedListener;
import com.codbking.widget.view.OnWheelScrollListener;
import com.codbking.widget.view.WheelView;
import com.codbking.widget.genview.GenWheelText;
import com.codbking.widget.genview.WheelGeneralAdapter;

/**
 * Created by codbking on 2016/8/11.
 */
 abstract class BaseWheelPick
        extends LinearLayout
        implements OnWheelChangedListener
        , OnWheelScrollListener
         {

    protected int textColor = 0xffdddddd;
    protected int selectColor = 0xff444444;
    protected int split = 0xffdddddd;
    protected int splitHeight = 1;
    protected Context ctx;
    private GenWheelText genView;

    public BaseWheelPick(Context context) {
        super(context);
        init(context);
    }

    public BaseWheelPick(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DatePicker);
        textColor = a.getColor(R.styleable.DatePicker_picker_text_color, 0xffdddddd);
        selectColor = a.getColor(R.styleable.DatePicker_picker_select_textColor, 0xff444444);
        split = a.getColor(R.styleable.DatePicker_picker_split, 0xffdddddd);
        splitHeight = (int) a.getDimension(R.styleable.DatePicker_picker_split_height, 0.5f);

        a.recycle();
        init(context);
    }

    private void init(Context context) {
        genView = new GenWheelText(textColor);
        this.ctx = context;
        LayoutInflater.from(context).inflate(getLayout(), this);
    }

    protected void setWheelListener(WheelView wheelView, Object[] data, boolean isCyclic) {
        WheelGeneralAdapter viewAdapter = new WheelGeneralAdapter(ctx, genView);
        if (data[0] instanceof Integer) {
            viewAdapter.setData(convertData(wheelView, (Integer[]) data));
        } else {
            viewAdapter.setData(data);
        }
        wheelView.setSelectTextColor(textColor, selectColor);
        wheelView.setCyclic(isCyclic);
        wheelView.setViewAdapter(viewAdapter);
        wheelView.addChangingListener(this);
        wheelView.addScrollingListener(this);
    }

    protected String[] convertData(WheelView wheelView, Integer[] data) {
        return new String[0];
    }

    protected abstract int getLayout();

    protected abstract int getItemHeight();

    protected abstract void setData(Object[] datas);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(splitHeight);
        paint.setColor(split);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        int itemHeight = getItemHeight();

        for (int i = 0; i < 5; i++) {
            canvas.drawLine(0, (i + 1) * itemHeight, getWidth(), (i + 1) * itemHeight, paint);
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {

    }

}
