package com.henley.upgrade.interfaces.impl;

import android.text.TextUtils;

import com.henley.upgrade.UpdaterConfiguration;
import com.henley.upgrade.callback.UpdateCheckCallback;
import com.henley.upgrade.interfaces.UpdateChecker;
import com.henley.upgrade.model.IncrementUpdateInfo;
import com.henley.upgrade.model.TotalUpdateInfo;
import com.henley.upgrade.model.UpdateInfo;

/**
 * 默认的更新检查实现
 *
 * @author Henley
 * @date 2016/12/14 14:35
 */
public final class DefaultUpdateChecker implements UpdateChecker {

    private UpdaterConfiguration mConfig;

    public DefaultUpdateChecker(UpdaterConfiguration config) {
        this.mConfig = config;
    }

    @Override
    public void check(UpdateCheckCallback callback) {
        UpdateInfo updateInfo = mConfig.getUpdateInfo();
        if (updateInfo == null) {
            callback.onCheckFail("The updateInfo is null.");
        } else {
            if (updateInfo.isIncrementUpdate()) { // 增量更新
                IncrementUpdateInfo incrementalInfo = updateInfo.getIncrementUpdateInfo();
                if (incrementalInfo == null) {
                    callback.onCheckFail("The IncrementUpdateInfo is null.");
                } else {
                    if (TextUtils.isEmpty(incrementalInfo.getPatchUrl()) || TextUtils.isEmpty(incrementalInfo.getFullApkMD5())) {
                        callback.onCheckFail("The patchUrl or fullApkMD5 is null.");
                    } else {
                        callback.onCheckSuccess();
                    }
                }
            } else { // 全量更新
                TotalUpdateInfo totalInfo = updateInfo.getTotalUpdateInfo();
                if (totalInfo == null) {
                    callback.onCheckFail("The TotalUpdateInfo is null.");
                } else {
                    if (TextUtils.isEmpty(totalInfo.getApkUrl())) {
                        callback.onCheckFail("");
                    } else {
                        callback.onCheckSuccess();
                    }
                }
            }
        }
    }
}
