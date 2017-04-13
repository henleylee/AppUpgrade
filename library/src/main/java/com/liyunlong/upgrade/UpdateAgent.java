package com.liyunlong.upgrade;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;


/**
 * 更新代理
 *
 * @author liyunlong
 * @date 2016/12/14 13:19
 */
public class UpdateAgent {

    private UpdaterConfiguration mConfig;
    private static UpdateAgent instance;

    public static UpdateAgent getInstance() {
        if (instance == null) {
            synchronized (UpdateAgent.class) {
                if (instance == null) {
                    instance = new UpdateAgent();
                }
            }
        }
        return instance;
    }

    public UpdateAgent init(UpdaterConfiguration config) {
        this.mConfig = config;
        return this;
    }

    public UpdateAgent check(Context context) {
        if (mConfig == null) {
            throw new RuntimeException("The UpdaterConfiguration is null,please invoke init method");
        }
        if (mConfig.getContext() == null) {
            mConfig.setContext(context);
        }
        if (mConfig.getUIHandler() == null) {
            mConfig.setUIHandler(new Handler(Looper.getMainLooper()));
        }
        mConfig.getUpdateChecker().check(mConfig.getUpdateCheckCallback());
        return this;
    }

}
