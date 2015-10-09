package cn.kokoi.android.garden.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.kokoi.android.garden.R;

/**
 * Created by zkl on 2015/5/4.
 */
public class TitleBarManager {



    private View mContainer;
    private int mLayoutId;

    private TextView mTitleTextView;
    private ImageButton mLeftButton;

    public TitleBarManager(View container, int layoutId) {

        mContainer = container;
        mLayoutId = layoutId;

        this.findView();
    }

    protected void findView() {
        ViewGroup layout = (ViewGroup)mContainer.findViewById(mLayoutId);
        mTitleTextView = (TextView)layout.findViewById(R.id.titlebar_title_textView);
        mLeftButton = (ImageButton)layout.findViewById(R.id.titlebar_left_button);
        mLeftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    v.setBackgroundResource(R.drawable.back_highlight);
                }else if (event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP){
                    v.setBackgroundResource(R.drawable.back_normal);
                }
                return false;
            }
        });
    }




    /**
     * 设置标题文本
     * @param text
     */
    public void setTitle(String text) {
        if(mTitleTextView != null) {
            mTitleTextView.setText(text);
        }
    }


    /**
     * 设置标题文本
     * @param text
     */
    public void setTitle(int text) {
        if(mTitleTextView != null) {
            mTitleTextView.setText(text);
        }
    }

    public TextView getTitleTextView() {
        return mTitleTextView;
    }

    public ImageButton getLeftButton() {
        return mLeftButton;
    }


}
