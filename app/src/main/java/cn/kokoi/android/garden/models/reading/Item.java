package cn.kokoi.android.garden.models.reading;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zkl on 2015/4/22.
 */
public class Item implements Serializable{

    public static final String TAG = "item";

    private String title;

    public ArrayList<Snd> getSnds() {
        return mSnds;
    }

    public void setSnds(ArrayList<Snd> snds) {
        mSnds = snds;
    }

    public void addSnd(Snd snd){
        mSnds.add(snd);
    }

    private ArrayList<Snd> mSnds;

    public Item() {
        mSnds = new ArrayList<>();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", mSnds=" + mSnds +
                '}';
    }
}
