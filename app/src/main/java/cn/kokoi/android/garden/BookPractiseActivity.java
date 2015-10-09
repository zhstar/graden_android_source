package cn.kokoi.android.garden;

import android.content.Intent;
import android.support.v4.app.Fragment;

import cn.kokoi.android.garden.fragments.BookPractiseFragment;
import cn.kokoi.android.garden.models.Args;

/**
 * Created by kun on 15/5/15.
 */
public class BookPractiseActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        Intent intent = getIntent();
        Args args = (Args)intent.getSerializableExtra(BookPractiseFragment.EXTRA_ARGS);
        return BookPractiseFragment.newInstance(args.getBookId(), args.getUnitName());
    }
}
