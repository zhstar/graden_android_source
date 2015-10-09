package cn.kokoi.android.garden.models.update;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.kokoi.android.garden.common.Trace;
import cn.kokoi.android.garden.models.util.XMLParser;

/**
 * Created by zkl on 2015/5/21.
 */
public class UpdateManager {

    private static UpdateManager manager;
    public static UpdateManager getInstance(Context context){
        if (manager == null){
            manager = new UpdateManager(context);
        }
        return manager;
    }

    //获取版本信息成功
    public static final int OBTAIN_VERSION_SUCCESS = 1;

    private Context mContext;
    private Handler mHandler;


    private UpdateInfo mUpdateInfo;


    /**
     *
     * @param context
     */
    public UpdateManager(Context context) {
        mContext = context;
    }

    public UpdateInfo getUpdateInfo() {
        return mUpdateInfo;
    }

    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    /**
     * 必须先执行
     * @param serverVersionXmlUrl
     */
    public void initWithUrl(final String serverVersionXmlUrl){

        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                try{
                    URL url = new URL(serverVersionXmlUrl);
                    HttpURLConnection conn = null;
                    try{
                        conn = (HttpURLConnection)url.openConnection();
                        conn.setConnectTimeout(5000);
                        conn.setRequestMethod("GET");
                        int responsedCode = conn.getResponseCode();
                        if (responsedCode == 200) {
                            is = conn.getInputStream();
                            try {
                                mUpdateInfo = UpdateInfoParser.getUpdateInfo(XMLParser.getParser(is));

                                if (mHandler != null){
                                    Message msg = new Message();
                                    msg.what = OBTAIN_VERSION_SUCCESS;
                                    mHandler.sendMessage(msg);
                                }else{

                                }

                            }catch (Exception e){}

                        }else{
                            Trace.print("获取版本信息失败!");
                        }
                    }catch (IOException e){
                        Log.d("Error", e.getMessage());
                        Trace.print(e.getLocalizedMessage());
                    }finally {
                        conn.disconnect();
                    }

                }catch (MalformedURLException e){
                    Log.d("Error", e.getMessage());
                }finally {

                }
            }
        }).start();

    }


    /**
     * 本地程序版本
     * @return
     */
    public String getLocalVersionName() {
        String ret = null;
        try{
            PackageManager packageManager = mContext.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
            ret = packageInfo.versionName;
        }catch (Exception e){}
        return ret;
    }

    /**
     * 服务器上是否存在新版本
     * @param serverInfo
     * @return
     */
    public Boolean doesExistNewVersion(UpdateInfo serverInfo) {

        String serverVerStr = serverInfo.getVersion();
        String[] serverVerIds = serverVerStr.split("\\.");

        int serverMainId = Integer.parseInt(serverVerIds[0]);
        int serverSubId = Integer.parseInt(serverVerIds[1]);

        String localVerStr = getLocalVersionName();
        String[] localVerIds = localVerStr.split("\\.");

        int localMainId = Integer.parseInt(localVerIds[0]);
        int localSubId = Integer.parseInt(localVerIds[1]);

        if (serverMainId > localMainId){
            return true;
        }else if(serverMainId == localMainId){
            if (serverSubId > localSubId){
                return true;
            }
        }
        return false;
    }


    public void showUpdateDialog() {
        if (mUpdateInfo == null){
            return;
        }
        AlertDialog.Builder builer = new AlertDialog.Builder(mContext);
        builer.setTitle("版本升级");
        builer.setMessage(mUpdateInfo.getDescription());
        //当点确定按钮时从服务器上下载 新的apk 然后安装
        builer.setPositiveButton("现在更新", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Trace.print(mUpdateInfo.getApkUrl());
                ApkDownloadManager.getInstance(mContext).download(mUpdateInfo.getApkUrl());
            }
        });
        builer.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                //do sth
            }
        });
        AlertDialog dialog = builer.create();
        dialog.show();
    }



}
