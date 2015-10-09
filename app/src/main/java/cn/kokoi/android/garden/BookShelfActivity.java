package cn.kokoi.android.garden;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import cn.kokoi.android.garden.fragments.BookShelfFragment;

/**
 * Created by zkl on 2015/5/12.
 */
public class BookShelfActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return BookShelfFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //System.out.println(ConfigParser.getInstance().parse(this, "book5/reading/config.xml").toString());
        /*try{
            System.out.println(PractiseParser.getPractise(XMLParser.getParser(this, "book5/practise/re1/xml/data2.xml")).toString());
        }catch (Exception e){

        }*/


    }
}
