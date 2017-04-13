package com.liyunlong.upgrade.interfaces;

/**
 * 忽略该版本处理接口
 *
 * @author liyunlong
 * @date 2017/4/11 17:27
 */
public interface IgnoreHandler {

    /**
     * 忽略该版本
     */
    void ignore();

    /**
     * 是否忽略该版本
     */
    boolean isIgnored();
}
