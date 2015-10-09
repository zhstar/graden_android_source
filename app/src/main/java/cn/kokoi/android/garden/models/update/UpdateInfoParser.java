package cn.kokoi.android.garden.models.update;

import org.xmlpull.v1.XmlPullParser;

import cn.kokoi.android.garden.common.Trace;

/**
 * Created by zkl on 2015/5/21.
 */
public class UpdateInfoParser {

    public static UpdateInfo getUpdateInfo(XmlPullParser parser) throws Exception {
        UpdateInfo info = new UpdateInfo();
        int eventType = parser.getEventType();
        while(eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType){
                case XmlPullParser.START_TAG:
                    String name = parser.getName();
                    if (name.equals("version")){
                        parser.next();
                        info.setVersion(parser.getText());
                    }else if (name.equals("apkurl")){
                        parser.next();
                        info.setApkUrl(parser.getText());
                    }else if (name.equals("description")){
                        parser.next();
                        info.setDescription(parser.getText());
                    }
                    break;
            }
            eventType = parser.next();
        }
        return info;
    }

}
