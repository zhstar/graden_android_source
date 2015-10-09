package cn.kokoi.android.garden.fragments;

import android.content.Intent;
import android.os.Bundle;

import cn.kokoi.android.garden.BookPractiseActivity;
import cn.kokoi.android.garden.models.Args;
import cn.kokoi.android.garden.models.menu.MenuParser;
import cn.kokoi.android.garden.models.util.GradeFactory;
import cn.kokoi.android.garden.models.util.XMLParser;

/**
 * Created by zkl on 2015/5/14.
 */
public class BookMenuPractiseFragment extends BookMenuBaseFragment {


    public static final String EXTRA_BOOK_ID = "book_id";

    public static BookMenuPractiseFragment newInstance(int bookId) {
        BookMenuPractiseFragment fragment = new BookMenuPractiseFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_BOOK_ID, bookId);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        mBookId = args.getInt(EXTRA_BOOK_ID, 1);

        String itemsUrl = GradeFactory.getBookDirNameById(mBookId) + "/items.xml";

        try{
            mMenu = MenuParser.getMenu(XMLParser.getParser(mContext, itemsUrl));
        }catch (Exception e){

        }

    }

    @Override
    protected Intent getIntent(int itemPosition) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        Args args = new Args();
        args.setBookId(mBookId);
        args.setUnitName(mMenu.getItems().get(itemPosition).getName());
        bundle.putSerializable(BookPractiseFragment.EXTRA_ARGS, args);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected Class<?> getIntentActivityClass() {
        return BookPractiseActivity.class;
    }

}
