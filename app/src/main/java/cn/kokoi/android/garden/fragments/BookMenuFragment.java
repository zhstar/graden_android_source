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
import android.widget.RadioGroup;
import android.widget.TabHost;

import cn.kokoi.android.garden.BookMenuPractiseActivity;
import cn.kokoi.android.garden.BookMenuReadingActivity;
import cn.kokoi.android.garden.R;
import cn.kokoi.android.garden.models.util.GradeFactory;
import cn.kokoi.android.garden.widget.TitleBarManager;

/**
 * Created by zkl on 2015/5/14.
 */
public class BookMenuFragment extends Fragment {

    public static final String EXTRA_BOOK_ID = "book_id";
    public static BookMenuFragment newInstance(int bookId){
        BookMenuFragment fragment = new BookMenuFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_BOOK_ID, bookId);
        fragment.setArguments(args);
        return fragment;
    }

    private Context mContext;
    private LocalActivityManager groupActivity;
    private TabHost mTabHost;
    private Intent mReadingIntent;
    private Intent mPractiseIntent;
    private int mBookId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupActivity = new LocalActivityManager(getActivity(), false);
        groupActivity.dispatchCreate(savedInstanceState);

        mContext = getActivity();
        mBookId = getArguments().getInt(EXTRA_BOOK_ID, 1);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        TitleBarManager titleBarManager = new TitleBarManager(v, R.id.book_menu_titlebar);
        titleBarManager.setTitle(GradeFactory.getGradeNameByBookId(mBookId));
        titleBarManager.getLeftButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        mTabHost = (TabHost)v.findViewById(R.id.book_menu_tabHost);
        mTabHost.setup(groupActivity);


        mReadingIntent = new Intent();
        mReadingIntent.putExtra(BookMenuReadingFragment.EXTRA_BOOK_ID, mBookId);
        mReadingIntent.setClass(getActivity(), BookMenuReadingActivity.class);

        mPractiseIntent = new Intent();
        mPractiseIntent.putExtra(BookMenuPractiseFragment.EXTRA_BOOK_ID, mBookId);
        mPractiseIntent.setClass(getActivity(), BookMenuPractiseActivity.class);

        final String readingLabel = "reading";
        final String practiseLabel = "practise";
        mTabHost.addTab(mTabHost.newTabSpec(readingLabel)
                .setIndicator(readingLabel)
                .setContent(mReadingIntent));

        mTabHost.addTab(mTabHost.newTabSpec(practiseLabel)
                .setIndicator(practiseLabel)
                .setContent(mPractiseIntent));

        ViewGroup radiogroup_container = (ViewGroup)v.findViewById(R.id.book_menu_radiogroup);

        ((RadioGroup)radiogroup_container.findViewById(R.id.menu_radiogroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.menu_reading_radioButton) {
                    mTabHost.setCurrentTabByTag(readingLabel);
                }else if (checkedId == R.id.menu_practise_radioButton){
                    mTabHost.setCurrentTabByTag(practiseLabel);
                }
            }
        });

        return v;
    }



}
