package com.liyunlong.upgrade.interfaces.impl;

import com.liyunlong.upgrade.UpdaterConfiguration;
import com.liyunlong.upgrade.interfaces.IgnoreHandler;

/**
 * 忽略该版本处理
 *
 * @author liyunlong
 * @date 2017/4/11 17:28
 */
public class DefaultIgnoreHandler implements IgnoreHandler {

    private UpdaterConfiguration mConfig;

    public DefaultIgnoreHandler(UpdaterConfiguration config) {
        this.mConfig = config;
    }

    @Override
    public void ignore() {

    }

    @Override
    public boolean isIgnored() {
        return false;
    }
}
