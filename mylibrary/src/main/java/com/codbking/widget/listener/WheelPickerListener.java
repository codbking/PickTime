package com.codbking.widget.listener;

import com.codbking.widget.bean.DateBean;
import com.codbking.widget.bean.DateType;

/**
 * Created by codbking on 2016/9/22.
 */

public interface WheelPickerListener {
     void onSelect(DateType type, DateBean bean);
}
