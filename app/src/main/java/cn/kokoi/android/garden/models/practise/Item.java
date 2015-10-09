package cn.kokoi.android.garden.models.practise;

import java.io.Serializable;

/**
 * Created by kun on 15/5/14.
 */
public class Item implements Serializable {

    public static final String TAG = "item";

    private String mId;
    private String mSndUrl;
    private String mContent;

    public Item() {

    }

    public Item(String id, String sndUrl, String content) {
        mId = id;
        mSndUrl = sndUrl;
        mContent = content;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getSndUrl() {
        return mSndUrl;
    }

    public void setSndUrl(String sndUrl) {
        mSndUrl = sndUrl;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Override
    public String toString() {
        return "Item{" +
                "mId=" + mId +
                ", mSndUrl='" + mSndUrl + '\'' +
                ", mContent='" + mContent + '\'' +
                '}';
    }
}
