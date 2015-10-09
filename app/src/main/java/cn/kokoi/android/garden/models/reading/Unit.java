package cn.kokoi.android.garden.models.reading;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zkl on 2015/4/22.
 */
public class Unit implements Serializable {

    public static final String TAG = "unit";

    private String name;
    private String title;

    private ArrayList<ItemSet> mItemSets;

    public Unit() {
        mItemSets = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addItemSet(ItemSet itemSet){
        mItemSets.add(itemSet);
    }

    public ArrayList<ItemSet> getItemSets() {
        return mItemSets;
    }

    public void setItemSets(ArrayList<ItemSet> itemSets) {
        mItemSets = itemSets;
    }

    @Override
    public String toString() {
        return "Unit{" +
                " name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", mItemSets=" + mItemSets +
                '}';
    }
}
