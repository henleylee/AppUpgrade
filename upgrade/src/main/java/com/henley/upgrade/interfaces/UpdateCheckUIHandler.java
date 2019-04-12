package com.henley.upgrade.interfaces;

/**
 * 更新提示UI处理接口
 *
 * @author Henley
 * @date 2016/12/14 11:35
 */
public interface UpdateCheckUIHandler {

    /**
     * 有更新时调用(用于有更新时的UI展示)
     */
    void hasUpdate();

    /**
     * 无更新时调用(用于无更新时的UI展示)
     */
    void noUpdate();

    /**
     * 更新检查失败时调用(用于更新检查失败时的UI展示)
     */
    void checkError(String error);
}
