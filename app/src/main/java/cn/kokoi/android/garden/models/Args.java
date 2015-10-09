package cn.kokoi.android.garden.models;

import java.io.Serializable;

/**
 * Created by zkl on 2015/5/20.
 */
public class Args implements Serializable {

    private int mBookId;
    private String mUnitName;
    private Object mObject;



    public int getBookId() {
        return mBookId;
    }

    public void setBookId(int bookId) {
        mBookId = bookId;
    }

    public String getUnitName() {
        return mUnitName;
    }

    public void setUnitName(String unitName) {
        mUnitName = unitName;
    }

    public Object getObject() {
        return mObject;
    }

    public void setObject(Object object) {
        mObject = object;
    }
}
