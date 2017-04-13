package com.liyunlong.upgrade.interfaces.impl;

import android.os.Looper;

import com.liyunlong.upgrade.UpdaterConfiguration;
import com.liyunlong.upgrade.interfaces.AppInstaller;
import com.liyunlong.upgrade.utils.InstallUtils;

/**
 * APP静默安装实现
 *
 * @author liyunlong
 * @date 2016/12/14 11:55
 */
public final class AppSilentInstaller implements AppInstaller {

    private UpdaterConfiguration mConfig;

    public AppSilentInstaller(UpdaterConfiguration config) {
        this.mConfig = config;
    }

    @Override
    public void install(final String filePath) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            installInner(filePath);
        } else {
            mConfig.getExecutorService().execute(new Runnable() {
                @Override
                public void run() {
                    installInner(filePath);
                }
            });
        }
    }

    private void installInner(String apkFilePath) {
        final boolean isInstallOk = InstallUtils.silentInstall(apkFilePath);
        if (isInstallOk) {
            mConfig.getInstallCallback().onInstallSuccess();
        } else {
            mConfig.getInstallCallback().onInstallFail();
        }
    }
}
