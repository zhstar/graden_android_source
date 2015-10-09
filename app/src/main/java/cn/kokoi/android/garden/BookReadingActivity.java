package cn.kokoi.android.garden;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import cn.kokoi.android.garden.fragments.BookReadingFragment;
import cn.kokoi.android.garden.models.Args;
import cn.kokoi.android.garden.models.reading.Unit;

/**
 * Created by zkl on 2015/5/13.
 */
public class BookReadingActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Unit unitInstance = (Unit)((Args)bundle.getSerializable(BookReadingFragment.EXTRA_ARGS)).getObject();
        return BookReadingFragment.newInstance(unitInstance);
    }
}
