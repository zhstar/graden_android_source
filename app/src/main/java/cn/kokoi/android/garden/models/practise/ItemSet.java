package cn.kokoi.android.garden.models.practise;

import java.util.ArrayList;

/**
 * Created by kun on 15/5/14.
 */
public class ItemSet {
    public static final String TAG = "items";

    private int mId;
    private String mTitle;
    private String mType;
    private int mColumn;
    private ArrayList<Item> mItems;

    public ItemSet(){
        mItems = new ArrayList<>();
    }

    public ItemSet(int id, String title, String type, int column, ArrayList<Item> items)
    {
        mId = id;
        mTitle = title;
        mType = type;
        mColumn = column;
        mItems = items;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public int getColumn() {
        return mColumn;
    }

    public void setColumn(int column) {
        mColumn = column;
    }

    public ArrayList<Item> getItems() {
        return mItems;
    }

    public void setItems(ArrayList<Item> items) {
        mItems = items;
    }

    @Override
    public String toString() {
        return "ItemSet{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mType='" + mType + '\'' +
                ", mColumn=" + mColumn +
                ", mItems=" + mItems +
                '}';
    }
}
