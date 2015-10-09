package cn.kokoi.android.garden.models.practise;

/**
 * Created by kun on 15/5/14.
 */
public class Top {

    public static final String TAG = "top";

    private String mName;
    private String mContent;

    public Top() {

    }

    public Top(String name, String content){
        mName = name;
        mContent = content;
    }


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

    @Override
    public String toString() {
        return "Top{" +
                "mName='" + mName + '\'' +
                ", mContent='" + mContent + '\'' +
                '}';
    }
}
