package com.codbking.widget.genview;

import android.content.Context;
import android.database.Cursor;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

public abstract class GenWheelView implements IGenWheelView {
	/** just for reference **/
	protected abstract View genBody(Context context, View convertView, Object element, int position);

	@Override
	public View setup(Context context, int position, View convertView, ViewGroup parent, Object data) {
		if (data instanceof Object[]) {
			return genBody(context, convertView, ((Object[]) data)[position], position);
		} else if (data instanceof ArrayList<?>) {
			return genBody(context, convertView, ((ArrayList<?>) data).get(position), position);
		} else if (data instanceof LinkedHashMap<?, ?>) {
			LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) data;
			Iterator<?> iterator = map.entrySet().iterator();
			int index = position;
			while (iterator.hasNext()) {
				Entry<?, ?> entry = (Entry<?, ?>) iterator.next();
				if (entry.getValue() instanceof List<?>) {
					if (index <= ((List<?>) entry.getValue()).size()) {
						return genBody(context, convertView, ((List<?>) entry.getValue()).get(index - 1), position);
					} else {
						index = index - ((List<?>) entry.getValue()).size() - 1;
					}
				}
			}
			return null;
		} else if (data instanceof Cursor) {
			((Cursor) data).moveToPosition(position);
			return genBody(context, convertView, data, position);
		} else if (data instanceof SparseArray<?>) {
			return genBody(context, convertView, ((SparseArray<?>) data).valueAt(position), position);
		} else if (data instanceof SparseBooleanArray) {
			Boolean checked = ((SparseBooleanArray) data).get(((SparseBooleanArray) data).keyAt(position));
			return genBody(context, convertView, checked, position);
		} else if (data instanceof SparseIntArray) {
			return genBody(context, convertView, ((SparseIntArray) data).valueAt(position), position);
		} else if (data instanceof Vector<?>) {
			return genBody(context, convertView, ((Vector<?>) data).get(position), position);
		} else if (data instanceof LinkedList<?>) {
			return genBody(context, convertView, ((LinkedList<?>) data).get(position), position);
		} else {
			return convertView;
		}
	}
}