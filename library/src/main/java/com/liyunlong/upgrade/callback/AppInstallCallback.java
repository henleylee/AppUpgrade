package com.liyunlong.upgrade.callback;

/**
 * APP安装回调
 *
 * @author liyunlong
 * @date 2016/12/14 11:29
 */
public interface AppInstallCallback {

    void onInstallSuccess();

    void onInstallFail();
}
