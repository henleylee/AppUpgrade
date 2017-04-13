package com.liyunlong.upgrade.callback;

/**
 * 更新检查回调接口
 *
 * @author liyunlong
 * @date 2016/12/14 11:31
 */
public interface UpdateCheckCallback {

    void onCheckSuccess();

    void onCheckFail(String message);

}
