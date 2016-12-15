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
package com.codbking.widget.genview;

import android.content.Context;
import android.database.Cursor;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;


import com.codbking.widget.adapters.AbstractWheelAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

/**
 * 可以顯示特定樣式的滾輪的Adapter,
 * "Only support List, Map,String Array,Cursor,SparseArray,SparseBooleanArray,SparseIntArray,Vector, and basic data type"
 * 
 * @author josephWang
 * 
 */
public class WheelGeneralAdapter extends AbstractWheelAdapter {
	/**
	 * 資料形態 Enumeration
	 * 
	 * @author JosephWang
	 */
	public static enum DataType {
		ARRAYLIST, LINKEDHASHMAP, CURSOR, OBJECT_ARRAY, SPARSE_ARRAY, SPARSE_BOOLEAN_ARRAY, SPARSE_INT_ARRAY, VECTOR, LINKEDLIST, OTHERS;
	}

	private DataType dataType = DataType.OTHERS;

	/**
	 * 回傳資料形態。
	 * 
	 * @return
	 */
	public DataType getDataType() {
		return dataType;
	}

	private Object data;
	private GenWheelView generator;

	private Context context;

	public WheelGeneralAdapter(Context context, GenWheelView generator) {
		this.generator = generator;
		this.context = context;

	}

	public Object getData() {
		return data;
	}

	public void setData(Object object) throws UnSupportedWheelViewException {
		// Multiple data list without header
		if (object instanceof ArrayList<?>) {
			dataType = DataType.ARRAYLIST;
		} else if (object instanceof LinkedHashMap<?, ?>) {
			dataType = DataType.LINKEDHASHMAP;
		} else if (object instanceof Cursor) {
			dataType = DataType.CURSOR;
		} else if (object instanceof Object[]) {
			dataType = DataType.OBJECT_ARRAY;
		} else if (object instanceof SparseArray<?>) {
			dataType = DataType.SPARSE_ARRAY;
		} else if (object instanceof SparseBooleanArray) {
			dataType = DataType.SPARSE_BOOLEAN_ARRAY;
		} else if (object instanceof SparseIntArray) {
			dataType = DataType.SPARSE_INT_ARRAY;
		} else if (object instanceof Vector<?>) {
			dataType = DataType.VECTOR;
		} else if (object instanceof LinkedList<?>) {
			dataType = DataType.LINKEDLIST;
		} else {
			throw new UnSupportedWheelViewException();
		}
		data = object;
	}

	public void setData(Object[] object) {
		dataType = DataType.OBJECT_ARRAY;
		data = object;
	}

	public void setData(ArrayList<?> object) {
		dataType = DataType.ARRAYLIST;
		data = object;
	}

	public void setData(Vector<?> object) {
		dataType = DataType.VECTOR;
		data = object;
	}

	public void setData(SparseArray<?> object) {
		dataType = DataType.SPARSE_ARRAY;
		data = object;
	}

	public void setData(LinkedList<?> object) {
		dataType = DataType.LINKEDLIST;
		data = object;
	}

	public int getCountWithoutHeader() {
		int count = 0;
		switch (dataType) {
		case ARRAYLIST:
			count = ((ArrayList<?>) data).size();
			break;
		case LINKEDHASHMAP:
			Iterator<?> iterator = ((LinkedHashMap<?, ?>) data).entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<?, ?> entry = (Entry<?, ?>) iterator.next();
				if (entry.getValue() instanceof List) {
					count = count + ((List<?>) entry.getValue()).size();
				}
			}
			break;
		case CURSOR:
			count = ((Cursor) data).getCount();
			break;
		case OBJECT_ARRAY:
			count = ((Object[]) data).length;
			break;
		case SPARSE_ARRAY:
			count = ((SparseArray<?>) data).size();
			break;
		case SPARSE_BOOLEAN_ARRAY:
			count = ((SparseBooleanArray) data).size();
			break;
		case SPARSE_INT_ARRAY:
			count = ((SparseIntArray) data).size();
			break;
		case VECTOR:
			count = ((Vector<?>) data).size();
			break;
		case LINKEDLIST:
			count = ((LinkedList<?>) data).size();
			break;
		default:
			break;
		}
		return count;
	}

	@Override
	public int getItemsCount() {
		switch (dataType) {
		case ARRAYLIST:
			return ((ArrayList<?>) data).size();
		case LINKEDHASHMAP:
			// All elements count (include key)
			int count = 0;
			Iterator<?> iterator = ((LinkedHashMap<?, ?>) data).entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<?, ?> entry = (Entry<?, ?>) iterator.next();
				if (entry.getValue() instanceof List) {
					count = count + 1 + ((List<?>) entry.getValue()).size();
				}
			}
			return count;
		case CURSOR:
			return ((Cursor) data).getCount();
		case OBJECT_ARRAY:
			return ((Object[]) data).length;
		case SPARSE_ARRAY:
			return ((SparseArray<?>) data).size();
		case SPARSE_BOOLEAN_ARRAY:
			return ((SparseBooleanArray) data).size();
		case SPARSE_INT_ARRAY:
			return ((SparseIntArray) data).size();
		case VECTOR:
			return ((Vector<?>) data).size();
		case LINKEDLIST:
			return ((LinkedList<?>) data).size();
		default:
			return 0;
		}
	}

	@Override
	public View getItem(int position, View convertView, ViewGroup parent) {
		return generator.setup(context, position, convertView, parent, data);
	}
}