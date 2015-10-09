package cn.kokoi.android.garden.models.practise;

import android.support.annotation.Nullable;

/**
 * Created by kun on 15/5/14.
 */
public class Mod {
    public static final String TAG = "mod";
    private String mTitle;
    private String mContent;

    public Mod(){

    }

    public Mod(String title, @Nullable String content){
        mTitle = title;
        mContent = content;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Override
    public String toString() {
        return "Mod{" +
                "mTitle='" + mTitle + '\'' +
                ", mContent='" + mContent + '\'' +
                '}';
    }
}
