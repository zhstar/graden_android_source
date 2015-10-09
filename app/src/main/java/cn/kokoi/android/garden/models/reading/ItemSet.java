package cn.kokoi.android.garden.models.reading;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zkl on 2015/5/18.
 */
public class ItemSet implements Serializable {

    public static final String TAG = "items";

    private String mName;
    private ArrayList<Item> mItems;

    public ItemSet(){
        mName = "start";
        mItems = new ArrayList<>();
    }

    public void addItem(Item item)
    {
        mItems.add(item);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
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
                "mName='" + mName + '\'' +
                ", mItems=" + mItems +
                '}';
    }
}
