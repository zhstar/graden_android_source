package cn.kokoi.android.garden;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import cn.kokoi.android.garden.fragments.BookMenuReadingFragment;

/**
 * Created by zkl on 2015/5/14.
 */
public class BookMenuReadingActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        Intent intent = getIntent();
        int bookId = intent.getIntExtra(BookMenuReadingFragment.EXTRA_BOOK_ID, 0);
        //Toast.makeText(this, "bookId:" + bookId, Toast.LENGTH_SHORT).toast();
        return BookMenuReadingFragment.newInstance(bookId);
    }
}
