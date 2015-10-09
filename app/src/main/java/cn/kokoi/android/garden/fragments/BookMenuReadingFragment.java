package cn.kokoi.android.garden.fragments;

import android.content.Intent;
import android.os.Bundle;

import cn.kokoi.android.garden.BookReadingActivity;
import cn.kokoi.android.garden.models.Args;
import cn.kokoi.android.garden.models.menu.MenuParser;
import cn.kokoi.android.garden.models.reading.Reading;
import cn.kokoi.android.garden.models.reading.ReadingConfigParser;
import cn.kokoi.android.garden.models.util.GradeFactory;
import cn.kokoi.android.garden.models.util.XMLParser;

/**
 * Created by zkl on 2015/5/14.
 */
public class BookMenuReadingFragment extends BookMenuBaseFragment {

    public static final String EXTRA_BOOK_ID = "book_id";

    public static BookMenuReadingFragment newInstance(int bookId) {
        BookMenuReadingFragment fragment = new BookMenuReadingFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_BOOK_ID, bookId);
        fragment.setArguments(args);
        return fragment;
    }

    private Reading mReading;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        mBookId = args.getInt(EXTRA_BOOK_ID, 1);

        String itemsUrl = GradeFactory.getBookDirNameById(mBookId) + "/items.xml";
        String configUrl = GradeFactory.getBookDirNameById(mBookId) + "/reading/config.xml";

        try{
            mMenu = MenuParser.getMenu(XMLParser.getParser(mContext, itemsUrl));
            mReading = ReadingConfigParser.getReading(XMLParser.getParser(mContext, configUrl));
        }catch (Exception e){

        }

    }

    @Override
    protected Intent getIntent(int itemPosition) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        Args args = new Args();
        args.setObject(mReading.getUnits().get(itemPosition));
        bundle.putSerializable(BookReadingFragment.EXTRA_ARGS, args);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected Class<?> getIntentActivityClass() {
        return BookReadingActivity.class;
    }


}
