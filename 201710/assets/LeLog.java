package com.hpplay.utils;

import android.util.Log;

/**
 * Use LeLog.d instead of UDLog
 * Use LeLog.i instead of MiniLog
 */
public class LeLog {

    private static final String LEBO_TAG = "lbplayer";

    private static final int LOG_DISABLE = 0;
    private static final int LOGV = 10;
    private static final int LOGD = 20;
    private static final int LOGI = 30;
    private static final int LOGW = 40;
    private static final int LOGE = 50;
    private static final int LOG_ENABLE = 100;

    private static int sLevel = 0;

    public static void enableTrace(boolean enable) {
        if (enable) {
            sLevel = LOG_ENABLE;
        } else {
            sLevel = LOG_DISABLE;
        }
    }

    public static void v(String tag, String msg) {
        if (sLevel < LOGV) {
            return;
        }
        msg = formatMessage(tag, msg);
        Log.v(LEBO_TAG, msg);
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (sLevel < LOGV) {
            return;
        }
        msg = formatMessage(tag, msg);
        Log.v(LEBO_TAG, msg, tr);
    }

    public static void d(String tag, String msg) {
        if (sLevel < LOGD) {
            return;
        }
        msg = formatMessage(tag, msg);
        Log.d(LEBO_TAG, msg);
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (sLevel < LOGD) {
            return;
        }
        msg = formatMessage(tag, msg);
        LeLog.d(LEBO_TAG, msg, tr);
    }

    public static void i(String tag, String msg) {
        if (sLevel < LOGI) {
            return;
        }
        msg = formatMessage(tag, msg);
        Log.i(LEBO_TAG, msg);
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (sLevel < LOGI) {
            return;
        }
        msg = formatMessage(tag, msg);
        Log.i(LEBO_TAG, msg, tr);
    }

    public static void w(String tag, String msg) {
        if (sLevel < LOGW) {
            return;
        }
        msg = formatMessage(tag, msg);
        Log.w(LEBO_TAG, msg);
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (sLevel < LOGW) {
            return;
        }
        msg = formatMessage(tag, msg);
        Log.w(LEBO_TAG, msg, tr);
    }

    public static void w(String tag, Throwable tr) {
        if (sLevel < LOGW) {
            return;
        }
        String msg = formatMessage(tag, null);
        Log.w(LEBO_TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (sLevel < LOGE) {
            return;
        }
        msg = formatMessage(tag, msg);
        Log.e(LEBO_TAG, msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (sLevel < LOGE) {
            return;
        }
        msg = formatMessage(tag, msg);
        Log.e(LEBO_TAG, msg, tr);
    }

    private static String formatMessage(String tag, String msg) {
        if (tag == null) {
            tag = "";
        }
        if (msg == null) {
            msg = "";
        }
        String ret = tag + ":" + msg;
        ret = "[" + Thread.currentThread().getName() + "]:" + ret;
        return ret;
    }
}
