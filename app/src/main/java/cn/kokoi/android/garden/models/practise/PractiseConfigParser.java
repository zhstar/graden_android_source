package cn.kokoi.android.garden.models.practise;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by kun on 15/5/14.
 */
public class PractiseConfigParser {

    private static void trace(String str){
        System.out.println(str);
    }

    public static Practise getPractise(XmlPullParser parser) throws Exception{
        Practise practise = null;
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT){
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    practise = new Practise();
                    break;
                case XmlPullParser.START_TAG:
                    String name = parser.getName();
                    if (name.equals(Top.TAG)){
                        practise.setTop(getTop(parser));
                    }else if (name.equals(Mod.TAG)){
                        practise.setMod(getMod(parser));
                    }else if (name.equals(ItemSet.TAG)){
                        practise.getItemSets().add(getItemSet(parser));
                    }
                    break;
            }
            eventType = parser.next();
        }
        return practise;
    }

    private static Top getTop(XmlPullParser parser) throws Exception{
        int eventType = parser.getEventType();
        Top top = new Top();
        if (eventType == XmlPullParser.START_TAG && parser.getName().equals(Top.TAG)){
            while (true){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        top.setName(parser.getAttributeValue(null, "name"));
                        eventType = parser.next();
                        top.setContent(parser.getText());
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals(Top.TAG)){
                            //trace(top.toString());
                            return top;
                        }
                        break;
                }
                eventType = parser.next();
            }
        }
        return null;
    }


    private static Mod getMod(XmlPullParser parser) throws Exception{
        int eventType = parser.getEventType();
        Mod mod = new Mod();
        if (eventType == XmlPullParser.START_TAG && parser.getName().equals(Mod.TAG)){
            while (true){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        mod.setTitle(parser.getAttributeValue(null, "title"));
                        // Mod no text
                        //eventType = parser.next();
                        //mod.setContent(parser.getText());
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals(Mod.TAG)){
                            //trace("Mod:" + mod.toString());
                            return mod;
                        }
                        break;
                }
                eventType = parser.next();
            }
        }
        return null;
    }

    private static ItemSet getItemSet(XmlPullParser parser) throws Exception {
        ItemSet itemSet = new ItemSet();
        int eventType = parser.getEventType();
        if (eventType == XmlPullParser.START_TAG && parser.getName().equals(ItemSet.TAG)) {
            while (true){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equals(ItemSet.TAG)){
                            itemSet.setId(Integer.parseInt(parser.getAttributeValue(null, "id")));
                            itemSet.setTitle(parser.getAttributeValue(null, "title"));
                            itemSet.setType(parser.getAttributeValue(null, "type"));
                            itemSet.setColumn(Integer.parseInt(parser.getAttributeValue(null, "column")));
                        }else if (name.equals(Item.TAG)){
                            itemSet.getItems().add(getItem(parser));
                        }
                        //eventType = parser.next();

                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals(ItemSet.TAG)){
                            //trace(itemSet.toString());
                            return itemSet;
                        }
                        break;
                }
                eventType = parser.next();
            }
        }
        return null;
    }

    private static Item getItem(XmlPullParser parser) throws Exception {
        Item item = new Item();
        int eventType = parser.getEventType();
        if (eventType == XmlPullParser.START_TAG && parser.getName().equals(Item.TAG)){
            while(true){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        //item.setId(Integer.parseInt(parser.getAttributeValue(null, "id")));
                        item.setId(parser.getAttributeValue(null, "id"));
                        item.setSndUrl(parser.getAttributeValue(null, "url"));
                        parser.next();
                        item.setContent(parser.getText());
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals(Item.TAG)){
                            //trace(item.toString());
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
