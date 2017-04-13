package com.liyunlong.upgrade.callback;

/**
 * 文件下载回调
 *
 * @author liyunlong
 * @date 2016/12/14 11:30
 */
public interface DownloadCallback {

    void onStart();

    void onProgress(int progress, int total);

    void onComplete(String path);

    void onError(String error);

    void onCancel();

    void setBackground(boolean background);
}
