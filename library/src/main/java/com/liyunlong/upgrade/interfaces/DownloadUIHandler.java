package com.liyunlong.upgrade.interfaces;

/**
 * 文件下载UI处理接口
 *
 * @author liyunlong
 * @date 2016/12/14 11:34
 */
public interface DownloadUIHandler {

    /**
     * 开始下载时调用(用于开始下载的UI展示)
     */
    void downloadStart();

    /**
     * 下载进度更新时调用(用于下载进度更新时的UI展示)
     */
    void downloadProgress(int progress, int total);

    /**
     * 下载完成时时调用(用于下载完成时时的UI展示)
     */
    void downloadComplete(String path);

    /**
     * 下载失败时调用(用于下载失败时的UI展示)
     */
    void downloadError(String error);

    /**
     * 下载取消时调用(用于下载取消时的UI展示)
     */
    void downloadCancel();
}
