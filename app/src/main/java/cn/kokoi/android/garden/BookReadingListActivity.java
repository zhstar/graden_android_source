package cn.kokoi.android.garden;

import android.content.Intent;
import android.support.v4.app.Fragment;

import cn.kokoi.android.garden.fragments.BookReadingListFragment;
import cn.kokoi.android.garden.models.reading.ItemSet;

/**
 * Created by zkl on 2015/5/14.
 */
public class BookReadingListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        Intent intent = getIntent();
        ItemSet itemSet = (ItemSet)intent.getSerializableExtra(BookReadingListFragment.EXTRA_ITEM_SET);
        return BookReadingListFragment.newInstance(itemSet);
    }
}
