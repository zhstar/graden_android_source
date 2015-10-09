package cn.kokoi.android.garden.fragments;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import cn.kokoi.android.garden.R;
import cn.kokoi.android.garden.common.SoundManager;
import cn.kokoi.android.garden.common.Trace;
import cn.kokoi.android.garden.models.practise.Item;
import cn.kokoi.android.garden.models.practise.ItemSet;
import cn.kokoi.android.garden.models.practise.Practise;
import cn.kokoi.android.garden.models.practise.PractiseConfigParser;
import cn.kokoi.android.garden.models.util.GradeFactory;
import cn.kokoi.android.garden.models.util.XMLParser;
import cn.kokoi.android.garden.widget.TitleBarManager;

/**
 * Created by kun on 15/5/15.
 */
public class BookPractiseFragment extends Fragment {


    public static final String EXTRA_ARGS = "args";

    public static final String EXTRA_BOOK_ID = "book_id";
    public static final String EXTRA_UNIT_NAME = "unit";

    public static BookPractiseFragment newInstance(int bookId, String unit){
        BookPractiseFragment fragment = new BookPractiseFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_BOOK_ID, bookId);
        args.putString(EXTRA_UNIT_NAME, unit);
        fragment.setArguments(args);
        return fragment;
    }

    private Boolean mDefaultShowAnswer = false;

    private int mBookId;
    private String mUnit;

    private String mUnitRootUrl;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private Practise mPractise;

    private TextView mTipTextView;
    private ViewGroup mItemContainer;
    private AnimationDrawable mAudioAnimate;
    private ImageView mCurrentAudioImageView;

    //@TargetApi(21)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
        mLayoutInflater = LayoutInflater.from(mContext);

        Bundle args = getArguments();

        mBookId = args.getInt(EXTRA_BOOK_ID, 1);
        mUnit = args.getString(EXTRA_UNIT_NAME);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1){
            mUnit = args.getString(EXTRA_UNIT_NAME, "unit1");
        }else{
            mUnit = args.getString(EXTRA_UNIT_NAME);
        }*/


        mUnitRootUrl = GradeFactory.getBookDirNameById(mBookId) + "/practise/" + mUnit + "/";

        String dataXmlUrl = mUnitRootUrl + "xml/data.xml";


        try{
            //debugging
            mPractise = PractiseConfigParser.getPractise(XMLParser.getParser(mContext, dataXmlUrl));

        }catch (Exception e){

        }

        mAudioAnimate = (AnimationDrawable)mContext.getResources().getDrawable(R.drawable.audio_animate);
        mAudioAnimate.setOneShot(false);

    }

    @Override
    public void onPause() {
        stopAnimate();
        SoundManager.stop(mContext);
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_practise, container, false);

        TitleBarManager titleBarManager = new TitleBarManager(v, R.id.book_practise_titlebar);
        titleBarManager.setTitle(mPractise.getTop().getContent());
        titleBarManager.getLeftButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.stop(mContext);
                getActivity().finish();
            }
        });

        mTipTextView = (TextView)v.findViewById(R.id.tip_textView);
        mItemContainer = (ViewGroup)v.findViewById(R.id.practise_item_container);

        mTipTextView.setText(mPractise.getMod().getTitle());


        for (ItemSet itemSet:mPractise.getItemSets()){
            mItemContainer.addView(getSectionView(itemSet));
        }


        return v;
    }

    private View getSectionView(ItemSet itemSet) {
        View v = mLayoutInflater.inflate(R.layout.layout_practise_item_section, null);
        final ViewGroup container = (ViewGroup)v.findViewById(R.id.practise_section_container);
        TextView title = (TextView)v.findViewById(R.id.section_title_textView);
        CheckBox showAnswerCb = (CheckBox)v.findViewById(R.id.show_answer_checkBox);

        title.setText(itemSet.getTitle());

        for (Item item:itemSet.getItems()){
            container.addView(getCellView(item));
            if (item.getSndUrl() == null){
                showAnswerCb.setVisibility(View.GONE);
            }else{
                showAnswerCb.setChecked(mDefaultShowAnswer);
                setSectionAnswerVisibly(container, showAnswerCb.isChecked());
            }
        }


        showAnswerCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                setSectionAnswerVisibly(container, isChecked);
            }
        });


        return v;
    }

    private void setSectionAnswerVisibly(ViewGroup v, Boolean visibly){
        int count = v.getChildCount();
        ViewGroup subView;
        for (int i = 0; i < count; i++){
            subView = (ViewGroup)v.getChildAt(i);
            ((TextView)subView.findViewById(R.id.cell_text_textView)).setVisibility(visibly?View.VISIBLE:View.GONE);
        }
    }

    private View getCellView(Item item) {
        View v = mLayoutInflater.inflate(R.layout.layout_practise_item_cell, null);
        TextView id_textView = (TextView)v.findViewById(R.id.cell_id_textView);
        TextView text_textView = (TextView)v.findViewById(R.id.cell_text_textView);
        id_textView.setText(item.getId());
        text_textView.setText(item.getContent());
        final ImageView audio_imageView = (ImageView)v.findViewById(R.id.cell_audio_imageView);
        if (item.getSndUrl() == null/* || item.getSndUrl().isEmpty()*/){
            audio_imageView.setVisibility(View.GONE);
        }else{
            final String audioUrl = mUnitRootUrl + item.getSndUrl();
            audio_imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    playAnimate(audio_imageView);
                    SoundManager.play(mContext, audioUrl).setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            stopAnimate();
                        }
                    });
                }
            });
            audio_imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
        }
        return v;
    }

    private void playAnimate(ImageView imageView){
        if (mCurrentAudioImageView != null){
            mCurrentAudioImageView.setBackgroundResource(R.drawable.audio1);
            mAudioAnimate.stop();
        }
        imageView.setBackgroundDrawable(mAudioAnimate);
        mAudioAnimate.start();
        mCurrentAudioImageView = imageView;
    }

    private void stopAnimate(){
        if (mCurrentAudioImageView != null){
            mCurrentAudioImageView.setBackgroundResource(R.drawable.audio1);
        }
        mAudioAnimate.stop();
        mCurrentAudioImageView = null;
    }

}
