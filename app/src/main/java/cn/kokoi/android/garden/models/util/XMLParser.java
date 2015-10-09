package cn.kokoi.android.garden.models.util;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

/**
 * Created by kun on 15/5/14.
 */
public class XMLParser {

    public static XmlPullParser getParser(Context context, String filename) {
        try{
            InputStream is = context.getApplicationContext().getAssets().open(filename);
            return getParser(is);
        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }
        return null;
    }

    public static XmlPullParser getParser(InputStream is){
        XmlPullParser parser = Xml.newPullParser();
        try{
            parser.setInput(is, "UTF-8");
        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }
        return parser;
    }

}
