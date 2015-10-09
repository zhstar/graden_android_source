package cn.kokoi.android.garden.models.reading;

import java.io.Serializable;

/**
 * Created by zkl on 2015/5/20.
 */
public class Snd implements Serializable {

    public static final String TAG = "snd";

    private String mContent;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
