package com.codbking.widget.genview;

public class UnSupportedWheelViewException extends Exception {
    private static final long serialVersionUID = 1894662683963152958L;
    String mistake;

    public UnSupportedWheelViewException() {
        super();
        mistake = "Only support List, Map,String Array,Cursor,SparseArray,SparseBooleanArray,SparseIntArray,Vector, and basic data type";
    }

    public UnSupportedWheelViewException(String err) {
        super(err); // call super class constructor
        mistake = err; // save message
    }

    public String getError() {
        return mistake;
    }
}
