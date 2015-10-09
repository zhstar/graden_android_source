package cn.kokoi.android.garden.common;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zkl on 2015/4/21.
 */
public class SoundManager {

    public static class SoundListener {
        public void onCompletion(MediaPlayer player) {
            // sub class do something
        }
    }

    private static MediaPlayer player;

    public static MediaPlayer play(Context context, String path, final SoundListener listener) {
        if(player == null){
            player = getMediaPlayer(context);
        }
        player.reset();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try{
            AssetFileDescriptor afd = context.getAssets().openFd(path);
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            player.prepare();
            player.start();
            player.setVolume(1, 1);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    listener.onCompletion(player);
                }
            });
        }catch (IOException e){
            Log.e("File Error", e.getMessage());
        }
        return player;
    }
    public static MediaPlayer play(Context context, String path) {
        if(player == null){
            player = getMediaPlayer(context);
        }
        player.reset();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try{
            AssetFileDescriptor afd = context.getAssets().openFd(path);
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            player.prepare();
            player.start();
            player.setVolume(1, 1);

        }catch (IOException e){
            Log.e("File Error", e.getMessage());
        }
        return player;
    }

    public static void stop(Context context) {
        if ( player != null ){
            player.release();
            player = null;
        }

    }

    public static MediaPlayer getMediaPlayer(Context context){
        MediaPlayer mediaplayer = new MediaPlayer();
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return mediaplayer;
        }
        try {
            Class<?> cMediaTimeProvider = Class.forName( "android.media.MediaTimeProvider" );
            Class<?> cSubtitleController = Class.forName( "android.media.SubtitleController" );
            Class<?> iSubtitleControllerAnchor = Class.forName( "android.media.SubtitleController$Anchor" );
            Class<?> iSubtitleControllerListener = Class.forName( "android.media.SubtitleController$Listener" );
            Constructor constructor = cSubtitleController.getConstructor(new Class[]{Context.class, cMediaTimeProvider, iSubtitleControllerListener});
            Object subtitleInstance = constructor.newInstance(context, null, null);
            Field f = cSubtitleController.getDeclaredField("mHandler");
            f.setAccessible(true);
            try {
                f.set(subtitleInstance, new Handler());
            }
            catch (IllegalAccessException e) {return mediaplayer;}
            finally {
                f.setAccessible(false);
            }
            Method setsubtitleanchor = mediaplayer.getClass().getMethod("setSubtitleAnchor", cSubtitleController, iSubtitleControllerAnchor);
            setsubtitleanchor.invoke(mediaplayer, subtitleInstance, null);
            //Log.e("", "subtitle is setted :p");
        } catch (Exception e) {}
        return mediaplayer;
    }




}
