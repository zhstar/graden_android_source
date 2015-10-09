package cn.kokoi.android.garden.models.menu;

/**
 * Created by kun on 15/5/16.
 */
public class Item {
    public static final String TAG = "item";
    private String mName;
    private String mBgImg;
    private String mContent;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getBgImg() {
        return mBgImg;
    }

    public void setBgImg(String bgImg) {
        mBgImg = bgImg;
    }

    @Override
    public String toString() {
        return "Item{" +
                "mName='" + mName + '\'' +
                ", mBgImg='" + mBgImg + '\'' +
                ", mContent='" + mContent + '\'' +
                '}';
    }
}
