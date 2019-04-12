package com.henley.upgrade.callback;

/**
 * APP安装回调
 *
 * @author Henley
 * @date 2016/12/14 11:29
 */
public interface AppInstallCallback {

    void onInstallSuccess();

    void onInstallFail();
}
