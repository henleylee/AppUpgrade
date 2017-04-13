package com.liyunlong.upgrade.callback.impl;

import com.liyunlong.upgrade.UpdaterConfiguration;
import com.liyunlong.upgrade.callback.DownloadCallback;
import com.liyunlong.upgrade.utils.UIHandleUtils;

/**
 * 默认的文件下载回调实现
 *
 * @author liyunlong
 * @date 2016/12/14 13:24
 */
public final class DefaultDownloadCallback implements DownloadCallback {

    private boolean isBackground;
    private UpdaterConfiguration mConfig;

    public DefaultDownloadCallback(UpdaterConfiguration config) {
        this.mConfig = config;
    }

    @Override
    public void onStart() {
        UIHandleUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (mConfig.getDownloadUIHandler() != null) {
                    mConfig.getDownloadUIHandler().downloadStart();
                }
            }
        });
    }

    @Override
    public void onProgress(final int progress, final int total) {
        if (!isBackground) {
            UIHandleUtils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    if (mConfig.getDownloadUIHandler() != null) {
                        mConfig.getDownloadUIHandler().downloadProgress(progress, total);
                    }
                }
            });
        }
    }

    @Override
    public void onComplete(final String path) {
        UIHandleUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (mConfig.getDownloadUIHandler() != null) {
                    mConfig.getDownloadUIHandler().downloadComplete(path);
                }
            }
        });
    }

    @Override
    public void onError(final String error) {
        UIHandleUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (mConfig.getDownloadUIHandler() != null) {
                    mConfig.getDownloadUIHandler().downloadError(error);
                }
            }
        });
    }

    @Override
    public void onCancel() {
        UIHandleUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (mConfig.getDownloadUIHandler() != null) {
                    mConfig.getDownloadUIHandler().downloadCancel();
                }
            }
        });
    }

    @Override
    public void setBackground(boolean background) {
        this.isBackground = background;
    }
}

