package cn.kokoi.android.garden;

import android.content.Intent;
import android.support.v4.app.Fragment;

import cn.kokoi.android.garden.fragments.BookMenuFragment;

/**
 * Created by zkl on 2015/5/14.
 */
public class BookMenuActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        Intent intent = getIntent();
        int bookId = intent.getIntExtra(BookMenuFragment.EXTRA_BOOK_ID, 1);
        return BookMenuFragment.newInstance(bookId);
    }
}
