package com.henley.upgrade.interfaces.impl;

import android.content.Context;

import com.henley.upgrade.UpdaterConfiguration;
import com.henley.upgrade.interfaces.Downloader;
import com.henley.upgrade.net.DownloadRunnable;

import java.util.concurrent.Future;

/**
 * 默认的文件下载器实现
 *
 * @author Henley
 * @date 2016/12/14 13:14
 */
public final class DefaultDownloader implements Downloader {

    private Future<?> mTask;
    private Context mContext;
    private UpdaterConfiguration mConfig;
    private DownloadRunnable mDownloadRunnable;

    public DefaultDownloader(UpdaterConfiguration config) {
        this.mConfig = config;
        this.mContext = mConfig.getContext();
    }

    @Override
    public void download() {
        mDownloadRunnable = new DownloadRunnable(mContext, mConfig.getDownloadCallback(), mConfig.getUpdateInfo());
        mTask = mConfig.getExecutorService().submit(mDownloadRunnable);
    }

    @Override
    public void cancel() {
        if (mTask != null && !mTask.isDone()) {
            boolean isCancel = mTask.cancel(true);
            if (isCancel) {
                if (mConfig.getDownloadCallback() != null) {
                    mConfig.getDownloadCallback().onCancel();
                }
            }
        }
        if (mDownloadRunnable != null) {
            mDownloadRunnable.setIsCancel(true);
        }

    }

}
