package com.mul.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

public class MulApp extends Application {
    public static MulApp app;

    public static MulApp getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        setPackageInfo();
    }

    /**
     * 获取系统配置信息
     */
    private void setPackageInfo() {
        PackageManager manager = this.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
//            UAppManager.deviceid = getDeviceId(); // 使用的时候在获取
            MulAppManager.osVersion = Build.VERSION.RELEASE;
            MulAppManager.mobileType = Build.MODEL;
            if (null != info) {
                MulAppManager.version = info.versionName;
                MulAppManager.versionCode = info.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
