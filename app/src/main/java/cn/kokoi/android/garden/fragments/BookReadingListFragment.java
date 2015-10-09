package cn.kokoi.android.garden.fragments;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.kokoi.android.garden.R;
import cn.kokoi.android.garden.common.SoundManager;
import cn.kokoi.android.garden.common.Trace;
import cn.kokoi.android.garden.models.reading.Item;
import cn.kokoi.android.garden.models.reading.ItemSet;
import cn.kokoi.android.garden.models.reading.Snd;

/**
 * Created by zkl on 2015/5/14.
 */
public class BookReadingListFragment extends Fragment {
    public static final String EXTRA_ITEM_SET = "item_set";
    public static final String EXTRA_BOOK_ID = "book_id";
    public static final String EXTRA_UNIT_NAME = "unit";
    public static BookReadingListFragment newInstance(ItemSet itemSet) {
        BookReadingListFragment fragment = new BookReadingListFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ITEM_SET, itemSet);
        fragment.setArguments(args);
        return fragment;
    }
    public static BookReadingListFragment newInstance(ItemSet itemSet, int bookId, String unit) {
        BookReadingListFragment fragment = new BookReadingListFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ITEM_SET, itemSet);
        args.putInt(EXTRA_BOOK_ID, bookId);
        args.putString(EXTRA_UNIT_NAME, unit);
        fragment.setArguments(args);
        return fragment;
    }

    private int mBookId;
    private String mUnit;
    private Context mContext;
    private ItemSet mItemSet;
    private AnimationDrawable mAudioAnimate;
    private ImageView mCurrentAudioImageView;

    private int mCurrentItemTotalSnds = 0;
    private int mCurrentItemSndCounter = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();

        Bundle args = getArguments();

        mItemSet = (ItemSet)args.getSerializable(EXTRA_ITEM_SET);

        mBookId = args.getInt(EXTRA_BOOK_ID, 1);
        mUnit = args.getString(EXTRA_UNIT_NAME);

        mAudioAnimate = (AnimationDrawable)mContext.getResources().getDrawable(R.drawable.audio_animate);
        mAudioAnimate.setOneShot(false);

    }

    @Override
    public void onPause() {
        //Trace.print("onPause");
        stopAnimate();
        SoundManager.stop(mContext);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        //Trace.print("onDestory");
        super.onDestroy();
    }

    @Override
    public void onStop() {
        //Trace.print("onStop");
        stopAnimate();
        SoundManager.stop(mContext);
        super.onStop();
    }


    @Override
    public void onDetach() {
        //Trace.print("onDetach");
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_reading_tabs_start_a_b_c, container, false);

        ViewGroup item_container = (ViewGroup)v.findViewById(R.id.reading_start_item_container);

        int totalItems = mItemSet.getItems().size();
        for (int i = 0; i < totalItems; i++){
            Item item = mItemSet.getItems().get(i);
            addButton(item_container, item.getTitle(), item.getSnds(), totalItems, i+1);
        }

        return v;
    }

    private void addButton(ViewGroup container, String buttonText, ArrayList<Snd> snds, int totalItems, int currentItem) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.layout_reading_item, null);
        TextView title = (TextView)v.findViewById(R.id.item_title_textView);
        ImageView background = (ImageView)v.findViewById(R.id.item_bg_imageView);
        final ImageView audio_imageView = (ImageView)v.findViewById(R.id.item_audio_imageView);

        if (totalItems == 1) {
            background.setBackgroundResource(R.drawable.reading_item_bg1);
        }else{
            if (currentItem == 1){
                background.setBackgroundResource(R.drawable.reading_item_bg2);
            }else if (totalItems == currentItem){
                background.setBackgroundResource(R.drawable.reading_item_bg4);
            }else{
                background.setBackgroundResource(R.drawable.reading_item_bg3);
            }
        }
        title.setText(buttonText);
        container.addView(v);

        final ArrayList<Snd> mSnds = snds;

        v.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCurrentItemSndCounter = 0;
                mCurrentItemTotalSnds = mSnds.size();
                playAnimate(audio_imageView);

                /*SoundManager.play(mContext, mSnds.get(0).getContent()).setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mCounter++;
                        stopAnimate();
                    }
                });*/

                playSnd(mSnds, mCurrentItemSndCounter);
            }
        });
    }

    private void playSnd(final ArrayList<Snd> snds, int index)
    {
        String url = snds.get(index).getContent();
        //Trace.toast(mContext, "" + url);
        SoundManager.play(mContext, url).setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mCurrentItemSndCounter++;
                if (mCurrentItemSndCounter >= mCurrentItemTotalSnds){
                    stopAnimate();
                }else{
                    playSnd(snds, mCurrentItemSndCounter);
                }
            }
        });
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
