package cn.kokoi.android.garden.models.reading;


import org.xmlpull.v1.XmlPullParser;


/**
 * Created by zkl on 2015/4/22.
 */
public class ReadingConfigParser {


    public static Reading getReading(XmlPullParser parser) throws Exception{
        Reading reading = null;
        int eventType = parser.getEventType();
        while(eventType != XmlPullParser.END_DOCUMENT){
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    reading = new Reading();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals(Reading.TAG)){
                        //reading.setRootDir(parser.getAttributeValue(null, "rootDir"));
                    }else if (parser.getName().equals(Unit.TAG)){
                        //Unit unit = getUnit(parser);
                        //Trace.print("Unit:" + unit.toString());
                        //reading.getUnits().add(getUnit(parser));
                        reading.addUnit(getUnit(parser));
                    }
                    break;
            }
            eventType = parser.next();
        }
        //Trace.print(reading.toString());
        return reading;
    }


    private static Unit getUnit(XmlPullParser parser) throws  Exception{
        Unit unit = new Unit();
        int eventType = parser.getEventType();
        if(eventType == XmlPullParser.START_TAG && parser.getName().equals(Unit.TAG)){
            while (true){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals(Unit.TAG)){
                            unit.setName(parser.getAttributeValue(null, "name"));
                            unit.setTitle(parser.getAttributeValue(null, "title"));
                        }else if (parser.getName().equals(ItemSet.TAG)){
                            unit.addItemSet(getItemSet(parser));
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals(Unit.TAG)){
                            return unit;
                        }
                        break;
                }
                eventType = parser.next();
            }
        }
        return null;
    }

    private static ItemSet getItemSet(XmlPullParser parser) throws Exception{
        ItemSet itemSet = new ItemSet();
        int eventType = parser.getEventType();
        if(eventType == XmlPullParser.START_TAG && parser.getName().equals(ItemSet.TAG)){
            while(true){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals(ItemSet.TAG)){
                            String name = parser.getAttributeValue(null, "name");
                            itemSet.setName(name);
                        }else if (parser.getName().equals(Item.TAG)){
                            itemSet.addItem(getItem(parser));
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals(ItemSet.TAG)){
                            return itemSet;
                        }
                        break;
                }
                eventType = parser.next();
            }
        }
        return null;
    }


    private static Item getItem(XmlPullParser parser) throws Exception{
        Item item = new Item();
        int eventType = parser.getEventType();
        if(eventType == XmlPullParser.START_TAG && parser.getName().equals(Item.TAG)){
            while (true){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals(Item.TAG)){
                            item.setTitle(parser.getAttributeValue(null, "title"));
                        }else if (parser.getName().equals(Snd.TAG)){
                            parser.next();
                            Snd snd = new Snd();
                            snd.setContent(parser.getText());
                            item.addSnd(snd);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals(Item.TAG)){
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
