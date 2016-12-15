package com.codbking.widget.genview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public interface IGenWheelView {
    public View setup(Context context, int position, View convertView, ViewGroup parent, Object data);
}
