package com.liyunlong.upgrade.callback.impl;

import com.liyunlong.upgrade.UpdaterConfiguration;
import com.liyunlong.upgrade.callback.UpdateCheckCallback;
import com.liyunlong.upgrade.interfaces.UpdateCheckUIHandler;
import com.liyunlong.upgrade.utils.AppInfoUtils;
import com.liyunlong.upgrade.utils.UIHandleUtils;

/**
 * 默认的更新检测回调实现
 *
 * @author liyunlong
 * @date 2016/12/14 13:23
 */
public final class DefaultUpdateCheckCallback implements UpdateCheckCallback {

    private UpdaterConfiguration mConfig;

    public DefaultUpdateCheckCallback(UpdaterConfiguration config) {
        this.mConfig = config;
    }

    @Override
    public void onCheckSuccess() {
        UIHandleUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                int currentVersionCode = AppInfoUtils.getApkVersionCode(mConfig.getContext()); // 当前APP版本
                int updateVersionCode = mConfig.getUpdateInfo().getVersionCode();
                boolean hasUpdate = currentVersionCode < updateVersionCode; // 判断当前APP版本是否小于需要更新的APP版本
                if (!hasUpdate && currentVersionCode == updateVersionCode) { // 如果没有更新并且当前APP版本等于需要更新的APP版本
                    boolean forceUpdate = mConfig.getUpdateInfo().isForceUpdate(); // 判断是否是强制更新
                    if (forceUpdate) {
                        hasUpdate = mConfig.getIgnoreHandler().isIgnored(); // 判断是否忽略了该版本
                    }
                }
                UpdateCheckUIHandler updateUIHandler = mConfig.getUpdateUIHandler();
                if (updateUIHandler != null) {
                    if (hasUpdate) {
                        updateUIHandler.hasUpdate();
                    } else {
                        updateUIHandler.noUpdate();
                    }
                }
            }
        });

    }

    @Override
    public void onCheckFail(String message) {
        if (mConfig.getUpdateUIHandler() != null) {
            mConfig.getUpdateUIHandler().checkError(message);
        }
    }
}
