/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codbking.widget.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.Locale;

public class UIAdjuster {
	public static void closeKeyBoard(Context act) {
		if (act != null && act instanceof Activity) {
			closeKeyBoardInner((Activity) act);
		}
	}

	private static void closeKeyBoardInner(Activity act) {
		InputMethodManager imm = ((InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE));
		if (act.getCurrentFocus() != null && act.getCurrentFocus().getWindowToken() != null) {
			imm.hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	public static int setListViewHeightBasedOnChildren(ListView listView) {
		int height = 0;
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return height;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + listView.getPaddingTop() + listView.getPaddingBottom();
		height = params.height;
		listView.setLayoutParams(params);
		listView.requestLayout();
		return height;
	}

	public static void addViewIfNotNull(ViewGroup group, View view) {
		if (view != null) {
			group.addView(view);
		}
	}

	/**
	 * @param context
	 * @param dip
	 * @return px
	 */
	public static float computeDIPtoPixel(Context context, float dip) {
		Resources resources = context == null ? Resources.getSystem() : context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = metrics.density * dip;
		return px;
	}

	/**
	 * @param dynamic
	 * @param maxWidth
	 * @return size
	 */
	public static float computeDynamicWidth(TextView dynamic, int maxWidth) {
		Rect rect = new Rect();
		Paint paint = new Paint();
		return computeDynamicWidth(dynamic, maxWidth, paint, rect);
	}

	/**
	 * @param dynamic
	 * @param maxWidth
	 * @param paint
	 * @param rect
	 * @return size
	 */
	public static float computeDynamicWidth(TextView dynamic, int maxWidth, Paint paint, Rect rect) {
		String string = (String) dynamic.getText();
		float size = dynamic.getTextSize();
		while (true) {
			paint.setTextSize(size);
			paint.getTextBounds(string, 0, string.length(), rect);
			if (rect.width() > maxWidth) {
				size = size - 1;
			} else {
				break;
			}
		}
		return size;
	}

	/**
	 * @param string
	 * @param size
	 * @return width of text
	 */
	public static int computeStringWidth(String string, float size) {
		Rect rect = new Rect();
		Paint paint = new Paint();
		paint.setTextSize(size);
		paint.getTextBounds(string, 0, string.length(), rect);
		return rect.width();
	}

	/**
	 * return Locale. if (TRADITIONAL_CHINESE) return true else false
	 * 
	 * @param context
	 * @return
	 */
	public static boolean getLanguage(Context context) {
		Configuration conf = context.getResources().getConfiguration();
		if (conf.locale.equals(Locale.TAIWAN) || conf.locale.equals(Locale.TRADITIONAL_CHINESE)) {
			return true;
		} else {
			return false;
		}
	}

//	/**
//	 * Show Progress Dialog
//	 *
//	 * @param context
//	 * @param msg
//	 * @param cancelable
//	 * @return
//	 */
//	public static ProgressDialog showProgressDialog(Context context, String msg, boolean cancelable) {
//
//		ProgressDialog p = new ProgressDialog(context);
//		p.setCancelable(cancelable);
//		p.setMessage(msg);
//		p.show();
//		p.setContentView(R.layout.custom_progress_dialog);
//		p.setOnKeyListener(new DialogInterface.OnKeyListener() {
//			@Override
//			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//				if (keyCode == KeyEvent.KEYCODE_SEARCH && event.getRepeatCount() == 0) {
//					return true; // Pretend we processed it
//				}
//				return false; // Any other keys are still processed as normal
//			}
//		});
//
//		return p;
//	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	public static String getLocale(Context context) {
		Configuration conf = context.getResources().getConfiguration();
		if (conf.locale.equals(Locale.TAIWAN) || conf.locale.equals(Locale.TRADITIONAL_CHINESE)) {
			return "zh_TW";
		} else {
			return "en_US";
		}
	}
}