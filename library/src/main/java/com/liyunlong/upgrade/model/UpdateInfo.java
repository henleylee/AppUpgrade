package com.liyunlong.upgrade.model;

/**
 * 更新信息
 *
 * @author liyunlong
 * @date 2016/12/14 13:16
 */
public final class UpdateInfo {

    private boolean isIgnorable; // 是否可忽略该版本
    private boolean isForceUpdate; // 是否是强制更新
    private boolean isSilentInstall; // 是否静默安装
    private boolean isIncrementUpdate; // 是否是增量更新
    private int versionCode; // 版本号
    private String versionName; // 版本名
    private String updateContent; // 更新信息
    private int updateSize; // 更新大小
    private String updateTime; // 更新时间
    private TotalUpdateInfo totalUpdateInfo; // 全量更新信息
    private IncrementUpdateInfo incrementUpdateInfo; // 增量更新信息

    public boolean isIgnorable() {
        return isIgnorable;
    }

    public void setIgnorable(boolean ignorable) {
        isIgnorable = ignorable;
    }

    public boolean isForceUpdate() {
        return isForceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        isForceUpdate = forceUpdate;
    }

    public boolean isSilentInstall() {
        return isSilentInstall;
    }

    public void setSilentInstall(boolean silentInstall) {
        isSilentInstall = silentInstall;
    }

    public boolean isIncrementUpdate() {
        return isIncrementUpdate;
    }

    public void setIncrementUpdate(boolean incrementUpdate) {
        isIncrementUpdate = incrementUpdate;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public int getUpdateSize() {
        return updateSize;
    }

    public void setUpdateSize(int updateSize) {
        this.updateSize = updateSize;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public TotalUpdateInfo getTotalUpdateInfo() {
        return totalUpdateInfo;
    }

    public void setTotalUpdateInfo(TotalUpdateInfo totalUpdateInfo) {
        this.totalUpdateInfo = totalUpdateInfo;
    }

    public IncrementUpdateInfo getIncrementUpdateInfo() {
        return incrementUpdateInfo;
    }

    public void setIncrementUpdateInfo(IncrementUpdateInfo incrementUpdateInfo) {
        this.incrementUpdateInfo = incrementUpdateInfo;
    }
}
