package cn.kokoi.android.garden.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

import cn.kokoi.android.garden.BookShelfActivity;
import cn.kokoi.android.garden.R;
import cn.kokoi.android.garden.models.reading.ReadingConfigParser;

/**
 * Created by zkl on 2015/5/13.
 */
public class LoadingFragment extends Fragment {

    public static LoadingFragment newInstance() {
        LoadingFragment fragment = new LoadingFragment();
        return fragment;
    }

    int totalCounts = 1;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what <= 0){
                finishTimer();
            }
            super.handleMessage(msg);
        }
    };

    Timer mTimer = new Timer();

    TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = totalCounts--;
            mHandler.sendMessage(message);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTimer.schedule(mTimerTask, 1000, 1000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loading, container, false);

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestory", "onDestory");
    }

    private void finishTimer() {

        mTimerTask.cancel();
        mTimer.cancel();

        Intent intent = new Intent();
        intent.setClass(getActivity(), BookShelfActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();

    }

}
