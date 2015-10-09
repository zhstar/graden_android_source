package cn.kokoi.android.garden.models;

/**
 * Created by zkl on 2015/5/12.
 */
public class BookShelfItem {

    public static final String TAG = "book";

    private int mId;

    private String mName;

    private int mBackgroundResourceId;

    private String mBackground;

    public BookShelfItem() {

    }

    public BookShelfItem(int id, int backgroundResourceId){
        mId = id;
        mBackgroundResourceId = backgroundResourceId;
    }

    public BookShelfItem(int id, int backgroundResourceId, String background){
        mId = id;
        mBackgroundResourceId = backgroundResourceId;
        mBackground = background;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getBackgroundResourceId() {
        return mBackgroundResourceId;
    }

    public void setBackgroundResourceId(int backgroundResourceId) {
        mBackgroundResourceId = backgroundResourceId;
    }

    public String getBackground() {
        return mBackground;
    }
    public void setBackground(String background) {
        mBackground = background;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
