package cn.kokoi.android.garden;

import android.support.v4.app.Fragment;

import cn.kokoi.android.garden.fragments.LoadingFragment;

/**
 * Created by zkl on 2015/5/13.
 */
public class LoadingActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {

        return LoadingFragment.newInstance();
    }

}
