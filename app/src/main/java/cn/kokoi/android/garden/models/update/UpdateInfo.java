package cn.kokoi.android.garden.models.update;

/*
<?xml version="1.0" encoding="utf-8"?>
<info>
    <version>0.1</version>
    <apkurl>http://www.kokoi.cn/assist/mobiles/android/update/garden/pep/garden_v0.1.apk</apkurl>
    <description>检测到最新版本，请及时更新！</description>
</info>
 */
/**
 * Created by zkl on 2015/5/21.
 */
public class UpdateInfo {

    //版本号
    private String mVersion;
    //服务器端 apk 文件的路径
    private String mApkUrl;
    //描述信息
    private String mDescription;

    /**
     * 服务端 apk 文件的版本号 如: 0.2
     * @return
     */
    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }

    /**
     * 服务端 apk 文件的链接
     * @return
     */
    public String getApkUrl() {
        return mApkUrl;
    }

    public void setApkUrl(String apkUrl) {
        mApkUrl = apkUrl;
    }

    /**
     * 描述信息
     * @return
     */
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "mVersion='" + mVersion + '\'' +
                ", mApkUrl='" + mApkUrl + '\'' +
                ", mDescription='" + mDescription + '\'' +
                '}';
    }
}
