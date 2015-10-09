package cn.kokoi.android.garden.models.menu;

import java.util.ArrayList;

/**
 * Created by kun on 15/5/16.
 */
public class Menu {


    private ArrayList<Item> mItems;

    public Menu(){
        mItems = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return mItems;
    }

    public void setItems(ArrayList<Item> items) {
        mItems = items;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "mItems=" + mItems +
                '}';
    }
}
