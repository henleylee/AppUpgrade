package com.liyunlong.upgrade.net;

import android.content.Context;

import com.liyunlong.upgrade.callback.DownloadCallback;
import com.liyunlong.upgrade.model.UpdateInfo;
import com.liyunlong.upgrade.utils.FileUtils;
import com.liyunlong.upgrade.utils.UIHandleUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载任务
 *
 * @author liyunlong
 * @date 2017/4/11 18:32
 */
public class DownloadRunnable implements Runnable {

    private Context mContext;
    private DownloadCallback mCallback;
    private UpdateInfo mUpdateInfo;
    private boolean isCancel = false;

    public DownloadRunnable(Context context, DownloadCallback callback, UpdateInfo updateInfo) {
        this.mContext = context;
        this.mCallback = callback;
        this.mUpdateInfo = updateInfo;
    }

    public void setIsCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }

    private void sendProgress(int progress, int total) {
        if (mCallback != null) {
            mCallback.onProgress(progress, total);
        }
    }

    private void sendComplete(String path) {
        if (mCallback != null && !isCancel) {
            mCallback.onComplete(path);
        }
    }

    private void sendStart() {
        if (mCallback != null) {
            mCallback.onStart();
        }
    }

    private void sendError(String message) {
        if (mCallback != null) {
            mCallback.onError(message);
        }
    }

    private void download(String url, String path) throws Exception {
        sendStart();
        InputStream inputStream;
        FileOutputStream outputStream;
        URL httpUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
        connection.connect();
        int responseCode = connection.getResponseCode();
        int totalLength = connection.getContentLength();
        if (responseCode != 200) {
            sendError("The responseCode is " + responseCode + " .");
        } else if (totalLength <= 0) {
            sendError("The totalLength is " + totalLength + " .");
        } else {
            outputStream = new FileOutputStream(new File(path));
            inputStream = connection.getInputStream();
            byte[] buffer = new byte[8 * 1024];
            int lenth;
            int progress = 0;
            while ((lenth = inputStream.read(buffer, 0, buffer.length)) != -1 && !isCancel) {
                progress += lenth;
                outputStream.write(buffer, 0, lenth);
                sendProgress(progress, totalLength);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
    }

    @Override
    public void run() {
        boolean incrementUpdate = mUpdateInfo.isIncrementUpdate(); // 判断是否是增量更新
        String url = incrementUpdate ? mUpdateInfo.getIncrementUpdateInfo().getPatchUrl() : mUpdateInfo.getTotalUpdateInfo().getApkUrl();
        String fileSuffix = incrementUpdate ? FileUtils.SUFFIX_PATCH : FileUtils.SUFFIX_APK;
        String path = FileUtils.getFileSavePath(mContext, url, fileSuffix);
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        String error = "";
        boolean isSuccess = true;
        try {
            download(url, path);
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
            error = UIHandleUtils.getExceptionMsg(e);
        }

        if (isSuccess) {
            sendComplete(path);
        } else {
            sendError(error);
        }
    }
}
