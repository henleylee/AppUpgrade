package com.liyunlong.upgrade;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.liyunlong.upgrade.callback.AppInstallCallback;
import com.liyunlong.upgrade.callback.DownloadCallback;
import com.liyunlong.upgrade.callback.UpdateCheckCallback;
import com.liyunlong.upgrade.callback.impl.DefaultAppInstallCallback;
import com.liyunlong.upgrade.callback.impl.DefaultDownloadCallback;
import com.liyunlong.upgrade.callback.impl.DefaultUpdateCheckCallback;
import com.liyunlong.upgrade.interfaces.AppInstaller;
import com.liyunlong.upgrade.interfaces.DownloadUIHandler;
import com.liyunlong.upgrade.interfaces.Downloader;
import com.liyunlong.upgrade.interfaces.IgnoreHandler;
import com.liyunlong.upgrade.interfaces.UpdateCheckUIHandler;
import com.liyunlong.upgrade.interfaces.UpdateChecker;
import com.liyunlong.upgrade.interfaces.impl.AppNotifyInstaller;
import com.liyunlong.upgrade.interfaces.impl.AppSilentInstaller;
import com.liyunlong.upgrade.interfaces.impl.DefaultDownloadUIHandler;
import com.liyunlong.upgrade.interfaces.impl.DefaultDownloader;
import com.liyunlong.upgrade.interfaces.impl.DefaultIgnoreHandler;
import com.liyunlong.upgrade.interfaces.impl.DefaultUpdateCheckUIHandler;
import com.liyunlong.upgrade.interfaces.impl.DefaultUpdateChecker;
import com.liyunlong.upgrade.model.UpdateInfo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 更新配置
 *
 * @author liyunlong
 * @date 2016/12/14 13:18
 */
public final class UpdaterConfiguration {

    private Context mContext;
    private Handler mUIHandler;
    private UpdateInfo mUpdateInfo;
    private Downloader mDownloader;
    private IgnoreHandler mIgnoreHandler;
    private UpdateChecker mUpdateChecker;
    private AppInstaller mSilentInstaller;
    private AppInstaller mNotifyInstaller;
    private ExecutorService mExecutorService;
    private DownloadCallback mDownloadCallback;
    private DownloadUIHandler mDownloadUIHandler;
    private UpdateCheckUIHandler mUpdateUIHandler;
    private UpdateCheckCallback mUpdateCheckCallback;
    private AppInstallCallback mInstallCallback;

    public UpdaterConfiguration setContext(Context context) {
        if (!(context instanceof Activity)) {
            throw new RuntimeException("The context must be instance of activity.");
        }
        this.mContext = context;
        return this;
    }

    public UpdaterConfiguration setUIHandler(Handler handler){
        if (handler.getLooper() != Looper.getMainLooper()) {
            throw new RuntimeException("The handler must be a ui handler.");
        }
        this.mUIHandler = handler;
        return this;
    }

    public UpdaterConfiguration setUpdateInfo(UpdateInfo info) {
        this.mUpdateInfo = info;
        return this;
    }

    public UpdaterConfiguration setIgnoreHandler(IgnoreHandler ignoreHandler) {
        this.mIgnoreHandler = ignoreHandler;
        return this;
    }

    public UpdaterConfiguration setUpdateChecker(UpdateChecker updateChecker) {
        this.mUpdateChecker = updateChecker;
        return this;
    }

    public UpdaterConfiguration setUpdateCheckCallback(UpdateCheckCallback callback) {
        this.mUpdateCheckCallback = callback;
        return this;
    }

    public UpdaterConfiguration setUpdateUIHandler(UpdateCheckUIHandler updateUIHandler) {
        this.mUpdateUIHandler = updateUIHandler;
        return this;
    }

    public UpdaterConfiguration setDownloader(Downloader downloader) {
        this.mDownloader = downloader;
        return this;
    }

    public UpdaterConfiguration setDownloadCallback(DownloadCallback callback) {
        this.mDownloadCallback = callback;
        return this;
    }

    public UpdaterConfiguration setDownloadUIHandler(DownloadUIHandler downloadUIHandler) {
        this.mDownloadUIHandler = downloadUIHandler;
        return this;
    }

    public UpdaterConfiguration setExecutorService(ExecutorService executorService) {
        this.mExecutorService = executorService;
        return this;
    }

    public UpdaterConfiguration setSilentInstaller(AppSilentInstaller installer) {
        this.mSilentInstaller = installer;
        return this;
    }

    public UpdaterConfiguration setNotifyInstaller(AppNotifyInstaller installer) {
        this.mNotifyInstaller = installer;
        return this;
    }

    public UpdaterConfiguration setIntallCallback(AppInstallCallback callback) {
        this.mInstallCallback = callback;
        return this;
    }

    public Context getContext() {
        return mContext;
    }

    public Handler getUIHandler() {
        return mUIHandler;
    }

    public UpdateChecker getUpdateChecker() {
        if (mUpdateChecker == null) {
            mUpdateChecker = new DefaultUpdateChecker(this);
        }
        return mUpdateChecker;
    }

    public IgnoreHandler getIgnoreHandler() {
        if (mIgnoreHandler == null) {
            mIgnoreHandler = new DefaultIgnoreHandler(this);
        }
        return mIgnoreHandler;
    }

    public UpdateCheckUIHandler getUpdateUIHandler() {
        if (mUpdateUIHandler == null) {
            mUpdateUIHandler = new DefaultUpdateCheckUIHandler(this);
        }
        return mUpdateUIHandler;
    }

    public Downloader getDownloader() {
        if (mDownloader == null) {
            mDownloader = new DefaultDownloader(this);
        }
        return mDownloader;
    }

    public DownloadUIHandler getDownloadUIHandler() {
        if (mDownloadUIHandler == null) {
            mDownloadUIHandler = new DefaultDownloadUIHandler(this);
        }
        return mDownloadUIHandler;
    }

    public UpdateCheckCallback getUpdateCheckCallback() {
        if (mUpdateCheckCallback == null) {
            mUpdateCheckCallback = new DefaultUpdateCheckCallback(this);
        }
        return mUpdateCheckCallback;
    }

    public DownloadCallback getDownloadCallback() {
        if (mDownloadCallback == null) {
            mDownloadCallback = new DefaultDownloadCallback(this);
        }
        return mDownloadCallback;
    }

    public ExecutorService getExecutorService() {
        if (mExecutorService == null) {
            mExecutorService = Executors.newCachedThreadPool();
        }
        return mExecutorService;
    }

    public AppInstaller getNotifyInstaller() {
        if (mNotifyInstaller == null) {
            mNotifyInstaller = new AppNotifyInstaller(this);
        }
        return mNotifyInstaller;
    }

    public AppInstaller getSilentInstaller() {
        if (mSilentInstaller == null) {
            mSilentInstaller = new AppSilentInstaller(this);
        }
        return mSilentInstaller;
    }

    public AppInstallCallback getInstallCallback() {
        if (mInstallCallback == null) {
            mInstallCallback = new DefaultAppInstallCallback(this);
        }
        return mInstallCallback;
    }

    public UpdateInfo getUpdateInfo() {
        if (mUpdateInfo == null) {
            throw new RuntimeException("The update info is null,please invoke UpdaterConfiguration.updateInfo method.");
        }
        return mUpdateInfo;
    }
}
