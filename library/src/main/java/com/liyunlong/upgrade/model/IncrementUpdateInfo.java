package com.liyunlong.upgrade.model;

/**
 * 增量更新信息
 *
 * @author liyunlong
 * @date 2017/4/11 16:53
 */
public class IncrementUpdateInfo {

    private String fullApkMD5; // 完整apk的MD5值
    private String patchUrl; // 增量包的下载地址

    public String getFullApkMD5() {
        return fullApkMD5;
    }

    public void setFullApkMD5(String fullApkMD5) {
        this.fullApkMD5 = fullApkMD5;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }
}
