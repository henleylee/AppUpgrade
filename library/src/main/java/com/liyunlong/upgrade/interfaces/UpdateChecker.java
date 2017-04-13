package com.liyunlong.upgrade.interfaces;

import com.liyunlong.upgrade.callback.UpdateCheckCallback;

/**
 * 更新检查接口
 *
 * @author liyunlong
 * @date 2016/12/14 11:35
 */
public interface UpdateChecker {

    /**
     * 更新检查
     */
    void check(UpdateCheckCallback callback);
}
