package com.henley.upgrade.interfaces;

import com.henley.upgrade.callback.UpdateCheckCallback;

/**
 * 更新检查接口
 *
 * @author Henley
 * @date 2016/12/14 11:35
 */
public interface UpdateChecker {

    /**
     * 更新检查
     */
    void check(UpdateCheckCallback callback);
}
