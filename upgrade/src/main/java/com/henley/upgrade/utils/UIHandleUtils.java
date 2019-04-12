package com.henley.upgrade.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * UI处理工具类
 *
 * @author Henley
 * @date 2016/12/14 11:38
 */
public class UIHandleUtils {

    public static void runOnUIThread(Runnable runnable) {
        if (runnable != null) {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    public static void showToast(Context context, int id) {
        showToast(context, context.getResources().getString(id));
    }

    public static void showToast(final Context context, final String msg) {
        runOnUIThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static String getExceptionMsg(Exception e) {
        return e == null ? "" : (e.getMessage() == null ? "" : e.getMessage());
    }
}
