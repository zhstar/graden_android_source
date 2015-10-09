package cn.kokoi.android.garden.models.menu;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by kun on 15/5/16.
 */
public class MenuParser {

    public static Menu getMenu(XmlPullParser parser) throws Exception {
        Menu menu = null;
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT){
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    menu = new Menu();
                    break;
                case XmlPullParser.START_TAG:
                    String name = parser.getName();
                    if (name.equals(Item.TAG)){
                       menu.getItems().add(getItem(parser));
                    }
                    break;
            }
            eventType = parser.next();
        }
        return menu;

    }

    private static Item getItem(XmlPullParser parser) throws  Exception {
        Item item = new Item();
        int eventType = parser.getEventType();
        if (eventType == XmlPullParser.START_TAG && parser.getName().equals(Item.TAG)){
            while(true){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        item.setName(parser.getAttributeValue(null, "name"));
                        item.setBgImg(parser.getAttributeValue(null, "bgImg"));
                        parser.next();
                        item.setContent(parser.getText());
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals(Item.TAG)){
                            return item;
                        }
                        break;
                }
                eventType = parser.next();
            }
        }
        return null;
    }

}
