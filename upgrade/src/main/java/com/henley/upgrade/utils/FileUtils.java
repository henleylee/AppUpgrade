package com.henley.upgrade.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 文件工具类
 *
 * @author Henley
 * @date 2016/12/14 13:26
 */
public class FileUtils {

    public static final String SUFFIX_APK = ".apk";
    public static final String SUFFIX_PATCH = ".patch";
    /**
     * 获取文件保存地址
     *
     * @param fileUrl    文件下载地址
     * @param fileSuffix 文件后缀
     * @return 文件保存路径
     */
    public static String getFileSavePath(Context context, String fileUrl, String fileSuffix) {
        String fileDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // /sdcard/Android/data/<application package>/cache
            fileDir = context.getExternalFilesDir("").getAbsolutePath();
        } else {
            // /data/data/<application package>/file
            fileDir = context.getFilesDir().getAbsolutePath();
        }
        String MD5 = MD5Utils.get32BitsMD5(fileUrl);
        return fileDir + File.separator + MD5 + fileSuffix;

    }
}
