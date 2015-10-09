package cn.kokoi.android.garden.models.practise;

import java.util.ArrayList;

/**
 * Created by kun on 15/5/14.
 */
public class Practise {
    public static final String TAG = "data";


    private Top mTop;
    private Mod mMod;

    private ArrayList<ItemSet> mItemSets;

    public Practise() {
        mItemSets = new ArrayList<>();
    }

    public Top getTop() {
        return mTop;
    }

    public void setTop(Top top) {
        mTop = top;
    }

    public Mod getMod() {
        return mMod;
    }

    public void setMod(Mod mod) {
        mMod = mod;
    }


    public ArrayList<ItemSet> getItemSets() {
        return mItemSets;
    }

    public void setItemSets(ArrayList<ItemSet> itemSets) {
        mItemSets = itemSets;
    }

    @Override
    public String toString() {
        return "Practise{" +
                "mTop=" + mTop +
                ", mMod=" + mMod +
                ", mItemSets=" + mItemSets +
                '}';
    }
}
