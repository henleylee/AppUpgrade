package com.henley.upgrade.interfaces.impl;

import com.henley.upgrade.UpdaterConfiguration;
import com.henley.upgrade.interfaces.IgnoreHandler;

/**
 * 忽略该版本处理
 *
 * @author Henley
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
