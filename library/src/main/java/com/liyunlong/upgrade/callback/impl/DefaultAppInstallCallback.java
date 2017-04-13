package com.liyunlong.upgrade.callback.impl;

import com.liyunlong.upgrade.R;
import com.liyunlong.upgrade.UpdaterConfiguration;
import com.liyunlong.upgrade.callback.AppInstallCallback;
import com.liyunlong.upgrade.utils.UIHandleUtils;

/**
 * 默认的APP安装回调
 *
 * @author liyunlong
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

