package com.mul.app;

import android.content.Context;

import com.mul.base.BaseActivity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by TaiJL on 2016/4/19.
 * 框架内存储管理一些全局变量的Manager.
 */
public class MulAppManager {
    public static final String SPECIAL_IMEI = "000000000000000";
    private static Context mContext = null;
    private static final List<BaseActivity> ACTIVITY_STACK = new LinkedList<>();
    /**
     * 临时栈集合
     */
    private static List<BaseActivity> tempActivityStack = new LinkedList<>();
    private static final HashMap<String, Object> APP_DATA_MAP = new HashMap<>();
    public static String deviceid;  // 设备ID
    public static String osVersion; // 操作系统版本
    public static String mobileType;// 手机型号
    public static String version;   // app的versionName
    public static int versionCode;  // app的versionCode

    public static List<BaseActivity> getActivityList() {
        return ACTIVITY_STACK;
    }

    public static HashMap<String, Object> globalObjManager = new HashMap<>();

    /**
     * 创建时添加到栈内
     *
     * @param activity
     */
    public static void pushAct(BaseActivity activity) {
        synchronized (ACTIVITY_STACK) {
            ACTIVITY_STACK.add(activity);
        }
    }

    /**
     * 清除栈内的activity
     *
     * @param activity
     */
    public static void popAct(BaseActivity activity) {
        synchronized (ACTIVITY_STACK) {
            ACTIVITY_STACK.remove(activity);
        }
    }

    /**
     * 清除栈内的activity
     */
    public static void popAllAct() {
        List<BaseActivity> copy;
        synchronized (ACTIVITY_STACK) {
            copy = new LinkedList<>(ACTIVITY_STACK);
            for (BaseActivity activity : copy) {
                activity.finish();
            }
        }
    }

    /**
     * 创建时添加到栈内(临时)
     *
     * @param activity
     */
    public static void pushActTemp(BaseActivity activity) {
        synchronized (tempActivityStack) {
            tempActivityStack.add(activity);
        }
    }

    /**
     * 清除栈内的activity(临时)
     *
     * @param activity
     */
    public static void popActTemp(BaseActivity activity) {
        synchronized (tempActivityStack) {
            tempActivityStack.remove(activity);
        }
    }

    /**
     * 清除栈内的activity(临时)
     */
    public static void popAllActTemp() {
        List<BaseActivity> copy;
        synchronized (tempActivityStack) {
            copy = new LinkedList<>(tempActivityStack);
            for (BaseActivity activity : copy) {
                activity.finish();
            }
        }
    }

    /**
     * 获取到栈内的最后一个入栈的activity
     *
     * @return
     */
    public static BaseActivity getStackTopAct() {
        int size = ACTIVITY_STACK.size();
        if (size > 0) {
            return ACTIVITY_STACK.get(size - 1);
        } else {
            return null;
        }

    }

    /**
     * 清除栈内最后一个activity
     */
    public static void popStackTopAct() {
        int size = ACTIVITY_STACK.size();
        if (size > 0) {
            BaseActivity qstBaseActivity = ACTIVITY_STACK.get(size - 1);
            if (null != qstBaseActivity) {
                qstBaseActivity.finish();
            }
        }

    }

    public static BaseActivity getStackBottomAct() {
        int size = ACTIVITY_STACK.size();
        if (size > 0) {
            return ACTIVITY_STACK.get(0);
        } else {
            return null;
        }
    }

    public static HashMap<String, Object> getAppDataMap() {
        return APP_DATA_MAP;
    }

    /**
     * add by wxy
     *
     * @return Activity任务站
     */
    public static List<BaseActivity> getActivityStack() {
        return ACTIVITY_STACK;
    }
}
