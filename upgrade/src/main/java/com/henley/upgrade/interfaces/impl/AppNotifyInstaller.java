package com.henley.upgrade.interfaces.impl;

import com.henley.upgrade.UpdaterConfiguration;
import com.henley.upgrade.interfaces.AppInstaller;
import com.henley.upgrade.utils.InstallUtils;
import com.henley.upgrade.utils.UIHandleUtils;

/**
 * APP提示安装实现
 *
 * @author Henley
 * @date 2016/12/14 11:54
 */
public final class AppNotifyInstaller implements AppInstaller {

    private UpdaterConfiguration mConfig;

    public AppNotifyInstaller(UpdaterConfiguration config) {
        this.mConfig = config;
    }

    @Override
    public void install(final String filePath) {
        UIHandleUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                InstallUtils.notifyInstallApk(mConfig.getContext(), filePath);
            }
        });
    }
}
