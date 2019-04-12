package com.henley.upgrade.callback.impl;

import com.henley.upgrade.R;
import com.henley.upgrade.UpdaterConfiguration;
import com.henley.upgrade.callback.AppInstallCallback;
import com.henley.upgrade.utils.UIHandleUtils;

/**
 * 默认的APP安装回调
 *
 * @author Henley
 * @date 2016/12/14 13:24
 */
public class DefaultAppInstallCallback implements AppInstallCallback {

    private UpdaterConfiguration mConfig;

    public DefaultAppInstallCallback(UpdaterConfiguration config) {
        this.mConfig = config;
    }

    @Override
    public void onInstallSuccess() {
        UIHandleUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                UIHandleUtils.showToast(mConfig.getContext(), "安装成功");
            }
        });
    }

    @Override
    public void onInstallFail() {
        UIHandleUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                UIHandleUtils.showToast(mConfig.getContext(), R.string.install_error);
            }
        });
    }
}

