package com.liyunlong.upgrade.interfaces.impl;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.format.Formatter;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liyunlong.upgrade.R;
import com.liyunlong.upgrade.UpdaterConfiguration;
import com.liyunlong.upgrade.interfaces.DownloadUIHandler;
import com.liyunlong.upgrade.model.UpdateInfo;
import com.liyunlong.upgrade.utils.FileUtils;
import com.liyunlong.upgrade.utils.InstallUtils;
import com.liyunlong.upgrade.utils.UIHandleUtils;

/**
 * 默认的文件下载UI处理实现
 *
 * @author liyunlong
 * @date 2016/12/14 13:10
 */
public final class DefaultDownloadUIHandler implements DownloadUIHandler {

    private UpdaterConfiguration mConfig;
    private AlertDialog mDialog;
    private ProgressBar mProgressBar;
    private TextView mProgressHint;
    private Context mContext;
    private boolean isBackground;

    public DefaultDownloadUIHandler(UpdaterConfiguration config) {
        this.mConfig = config;
        this.mContext = mConfig.getContext();
    }

    @Override
    public void downloadStart() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        if (!mConfig.getUpdateInfo().isForceUpdate()) {
            builder.setNegativeButton(UIHandleUtils.getString(mContext, R.string.download_cancle), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mConfig.getDownloader().cancel();
                }
            });
        }
        builder.setPositiveButton(UIHandleUtils.getString(mContext, R.string.download_background), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isBackground = true;
                mConfig.getDownloadCallback().setBackground(true);
            }
        });
        LinearLayout container = new LinearLayout(mContext);
        container.setOrientation(LinearLayout.VERTICAL);
        mProgressBar = new ProgressBar(mContext, null, android.R.attr.progressBarStyleHorizontal);
        mProgressHint = new TextView(mContext);
        container.addView(mProgressHint);
        container.addView(mProgressBar);
        int dp20 = UIHandleUtils.dp2px(mContext, 20);
        container.setPadding(dp20, dp20, dp20, dp20);
        builder.setView(container);
        mDialog = builder.create();
        mDialog.setCanceledOnTouchOutside(!mConfig.getUpdateInfo().isForceUpdate());
        mDialog.setCancelable(!mConfig.getUpdateInfo().isForceUpdate());
        mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mDialog.show();
    }

    @Override
    public void downloadProgress(int progress, int total) {
        if (!isBackground) {
            String progressFormat = String.format(UIHandleUtils.getString(mContext, R.string.downloading),
                    Formatter.formatFileSize(mContext, progress),
                    Formatter.formatFileSize(mContext, total));
            mProgressBar.setMax(total);
            mProgressHint.setText(progressFormat);
            mProgressBar.setProgress(progress);
        }
    }

    @Override
    public void downloadComplete(final String path) {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
        UIHandleUtils.showToast(mContext, "下载完成");
        final UpdateInfo updateInfo = mConfig.getUpdateInfo();
        if (updateInfo.isIncrementUpdate()) { // 增量更新
            mConfig.getExecutorService().execute(new Runnable() {
                @Override
                public void run() {
                    String newApkPath = FileUtils.getFileSavePath(mContext, "new", FileUtils.SUFFIX_APK);
                    String oldApkPath = FileUtils.getFileSavePath(mContext, "old", FileUtils.SUFFIX_APK);
                    String newApkMD5 = updateInfo.getIncrementUpdateInfo().getFullApkMD5();
                    boolean isPatchOk = InstallUtils.mergePatch(mContext, oldApkPath, newApkPath, path, newApkMD5);
                    if (isPatchOk) { // 检查MD5值通过
                        if (updateInfo.isSilentInstall()) { // 静默安装
                            mConfig.getSilentInstaller().install(newApkPath);
                            mConfig.getNotifyInstaller().install(newApkPath);
                        } else { // 提示安装
                            mConfig.getNotifyInstaller().install(newApkPath);
                        }
                    } else { // 检查MD5值不通过
                        mConfig.getInstallCallback().onInstallFail();
                    }

                }
            });
        } else { // 全量更新
            if (updateInfo.isSilentInstall()) { // 静默安装
                mConfig.getSilentInstaller().install(path);
            } else { // 提示安装
                mConfig.getNotifyInstaller().install(path);
            }
        }
    }

    @Override
    public void downloadError(String error) {
        UIHandleUtils.showToast(mContext, R.string.download_error);
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void downloadCancel() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
