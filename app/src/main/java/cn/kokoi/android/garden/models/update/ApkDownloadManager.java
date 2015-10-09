package cn.kokoi.android.garden.models.update;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.kokoi.android.garden.common.Trace;

/**
 * Created by kun on 2015/5/26.
 */
public class ApkDownloadManager {

    private static ApkDownloadManager instance;
    public static ApkDownloadManager getInstance(Context context) {
        if (instance == null){
            instance = new ApkDownloadManager(context);
        }
        return instance;
    }

    private Context mContext;
    private String mLocalApkFilename;
    private long mDownloadId;
    private DownloadManager mDownloadManager;
    private DownloadCompleteReceiver mDownloadCompleteReceiver;

    public ApkDownloadManager(Context context) {
        mContext = context;
    }

    public void download(String apkUrl) {

        //文件名
        mLocalApkFilename = apkUrl.substring(apkUrl.lastIndexOf("/") + 1, apkUrl.length());

        registerDownloadCompleteReceiver();

        mDownloadManager = (DownloadManager)mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkUrl));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, mLocalApkFilename);
        //os 3.0 or later
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }else{
            request.setShowRunningNotification(true);
        }

        mDownloadId = mDownloadManager.enqueue(request);

        //Trace.print("downloadId = " + downloadId);

    }

    public void installApk(File file){
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }


    public void registerDownloadCompleteReceiver(){
        mDownloadCompleteReceiver = new DownloadCompleteReceiver();
        mContext.registerReceiver(mDownloadCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public void unregisterDownloadCompleteReveiver() {
        if (mDownloadCompleteReceiver != null){
            mContext.unregisterReceiver(mDownloadCompleteReceiver);
            mDownloadCompleteReceiver = null;
        }

    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    class DownloadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (mDownloadId == downloadId) {
                try {
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + mLocalApkFilename);
                    installApk(file);
                }catch (Exception e){
                    //e.printStackTrace();
                    Trace.print(e.getMessage());
                }
                unregisterDownloadCompleteReveiver();
            }

        }
    }

    class DownloadChangeObserver extends ContentObserver {

        public DownloadChangeObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
        }
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /*
    public long download(String apkUrl) {

        //从链接路径截取文件名
        String filename = apkUrl.substring(apkUrl.lastIndexOf("/") + 1, apkUrl.length());

        //文件保存的目录
        String downloadDir = Environment.getExternalStorageDirectory() + "/Download/";

        //目录的文件对象
        File folder = new File(downloadDir);

        //如果目录不存在，则创建
        if (!folder.exists()){
            folder.mkdir();
        }

        //保存的完整路径
        String localApkUrl = downloadDir + filename;

        //文件对象
        File apkFile = new File(localApkUrl);

        //如果文件存在，则删除
        if (apkFile.exists()){
            apkFile.delete();
        }

        try {
            Trace.toast(mContext, "下载:" + apkUrl);
            URL url = new URL(apkUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            int responsedCode = conn.getResponseCode();
            if (responsedCode == 200) {

                InputStream is = conn.getInputStream();
                byte[] buf = new byte[1024];
                int len;
                OutputStream os = new FileOutputStream(localApkUrl);

			    BufferedInputStream bis = new BufferedInputStream(is);
                while((len = bis.read(buf)) != -1) {
                    Trace.print("" + len);
                    os.write(buf, 0, len);
                }

                os.close();
                is.close();
                Trace.toast(mContext, "下载完成");
            }else{
                Trace.toast(mContext, "服务器无响应");
            }

            //conn.disconnect();

        }catch (Exception e){
            e.printStackTrace();
        }

        return -1;
    }*/

}
