package com.mul.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.mul.app.MulApp;
import com.mul.view.toast.MulToast;

/**
 * Toast工具类
 * Created by TaiJL on 2015/3/17.
 */
public class MulToastUtil {

    private static Handler handler = new Handler(Looper.getMainLooper());

    private static MulToast toast = null;

    private static Object synObj = new Object();

//    public static void showMessage(final String msg) {
//        showMessage(msg, Toast.LENGTH_SHORT);
//    }

    /**
     * 根据设置的文本显示
     *
     * @param msg
     */
    public static void showMessage(final Object msg) {
        showMessage(msg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示一个文本并且设置时长
     *
     * @param msg
     * @param len
     */
    public static void showMessage(final Object msg, final int len) {
        if (msg == null || msg.equals("")) {
            Log.w("toast w", "[MulToastUtil] response message is null.");
            return;
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (synObj) { //加上同步是为了每个toast只要有机会显示出来
                    if (toast != null) {
                        //toast.cancel();
                        toast.setTextContent(msg);
//                        toast.setDuration(len);
                    } else {
//                        toast = Toast.makeText(App.getInstance().getApplicationContext(), msg, len);
                        toast = MulToast.makeText(MulApp.getInstance().getApplicationContext(), msg);
                    }
                    toast.show();
                }
            }
        });
    }
//
//    /**
//     * 资源文件方式显示文本
//     *
//     * @param msg
//     * @param len
//     */
//    public static void showMessage(final int msg, final int len) {
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (synObj) {
//                    if (toast != null) {
//                        //toast.cancel();
//                        toast.setText(msg);
//                        toast.setDuration(len);
//                    } else {
////                        toast = Toast.makeText(App.getInstance().getApplicationContext(), msg, len);
//                    }
//                    toast.show();
//                }
//            }
//        });
//    }

    /**
     * @param errorCode 错误码
     * @param msg       错误信息
     *                  -1000 本地异常
     *                  -1001 数据体为空
     *                  -2000 没有网络
     *                  -3000 服务器的未知错误
     */
    public static void errorTip(int errorCode, String msg) {
        //showMessage("errorCode=:" + errorCode + "\nmsg=:" + msg);
        if (errorCode == 0) {
            showMessage(msg);
        } else if (errorCode == -1000) {

        } else if (errorCode == -1001) {

        } else if (errorCode == -2000) {

        } else if (errorCode == -3000) {

        }

    }
}
