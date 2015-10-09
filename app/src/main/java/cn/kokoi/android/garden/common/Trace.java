package cn.kokoi.android.garden.common;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zkl on 2015/5/18.
 */
public class Trace {

    public static void toast(Context context, String content){
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void print(String content){
        System.out.println(content);
    }

}
