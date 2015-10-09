package cn.kokoi.android.garden;

import android.content.Intent;
import android.support.v4.app.Fragment;

import cn.kokoi.android.garden.fragments.BookMenuPractiseFragment;

/**
 * Created by zkl on 2015/5/14.
 */
public class BookMenuPractiseActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        Intent intent = getIntent();
        int bookId = intent.getIntExtra(BookMenuPractiseFragment.EXTRA_BOOK_ID, 1);
        return BookMenuPractiseFragment.newInstance(bookId);
    }

}
