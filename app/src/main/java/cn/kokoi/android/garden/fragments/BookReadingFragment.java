package cn.kokoi.android.garden.fragments;

import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import java.util.ArrayList;

import cn.kokoi.android.garden.BookReadingListActivity;
import cn.kokoi.android.garden.R;
import cn.kokoi.android.garden.models.reading.ItemSet;
import cn.kokoi.android.garden.models.reading.Unit;
import cn.kokoi.android.garden.widget.TitleBarManager;

/**
 * Created by zkl on 2015/5/13.
 */
public class BookReadingFragment extends Fragment {

    public static final String EXTRA_ARGS = "args";

    public static BookReadingFragment newInstance( Unit unitInstance){
        BookReadingFragment fragment = new BookReadingFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ARGS, unitInstance);
        fragment.setArguments(args);
        return fragment;
    }

    private LocalActivityManager groupActivity;
    private TabHost mTabHost;


    private Context mContext;
    private String mUnitTitle;
    private Unit mUnitInstance;

    private ArrayList<ItemSet> mItemSets;

    private RadioButton mStartRB;
    private RadioButton mARB;
    private RadioButton mBRB;
    private RadioButton mCRB;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        groupActivity = new LocalActivityManager(getActivity(), false);
        groupActivity.dispatchCreate(savedInstanceState);


        Bundle args = getArguments();
        mUnitInstance = (Unit)args.getSerializable(EXTRA_ARGS);
        if (mUnitInstance != null){
            mItemSets = mUnitInstance.getItemSets();
            mUnitTitle = mUnitInstance.getTitle();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        groupActivity.dispatchStop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reading, container, false);

        TitleBarManager titleBarManager = new TitleBarManager(v, R.id.book_reading_titlebar);
        titleBarManager.setTitle(mUnitTitle);
        titleBarManager.getLeftButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


        ViewGroup radiogroup_container = (ViewGroup)v.findViewById(R.id.reading_radiogroup);
        ((RadioGroup)radiogroup_container.findViewById(R.id.reading_tab_radiogroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                groupActivity.dispatchResume();
                if (checkedId == R.id.start_radioButton) {
                    mTabHost.setCurrentTabByTag("start");
                }else if (checkedId == R.id.a_radioButton){
                    mTabHost.setCurrentTabByTag("a");
                }else if (checkedId == R.id.b_radioButton){
                    mTabHost.setCurrentTabByTag("b");
                }else if (checkedId == R.id.c_radioButton){
                    mTabHost.setCurrentTabByTag("c");
                }
                groupActivity.dispatchPause(false);
            }
        });

        mStartRB = (RadioButton)radiogroup_container.findViewById(R.id.start_radioButton);
        mARB = (RadioButton)radiogroup_container.findViewById(R.id.a_radioButton);
        mBRB = (RadioButton)radiogroup_container.findViewById(R.id.b_radioButton);
        mCRB = (RadioButton)radiogroup_container.findViewById(R.id.c_radioButton);

        mTabHost = (TabHost)v.findViewById(R.id.book_reading_tabHost);

        mTabHost.setup(groupActivity);


        if (mItemSets.size() == 1){
            mStartRB.setBackgroundResource(R.drawable.start);
            mStartRB.setButtonDrawable(R.drawable.custom_start2_radiobutton_style);
            mARB.setVisibility(View.GONE);
            mBRB.setVisibility(View.GONE);
            mCRB.setVisibility(View.GONE);
        }

        for (ItemSet itemSet: mItemSets){
            String label = itemSet.getName();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable(BookReadingListFragment.EXTRA_ITEM_SET, itemSet);
            intent.putExtras(bundle);
            intent.setClass(mContext, getActivityClassWithLabel(label));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mTabHost.addTab(mTabHost.newTabSpec(label).setIndicator(label).setContent(intent));
        }


        return v;
    }

    private Class<?> getActivityClassWithLabel(String label){
        Class<?> cls = null;
        switch (label){
            case "start":
                cls = BookReadingListActivity.class;
                break;
            case "a":
                cls = BookReadingListActivity.class;
                break;
            case "b":
                cls = BookReadingListActivity.class;
                break;
            case "c":
                cls = BookReadingListActivity.class;
                break;
        }
        return cls;
    }

}
