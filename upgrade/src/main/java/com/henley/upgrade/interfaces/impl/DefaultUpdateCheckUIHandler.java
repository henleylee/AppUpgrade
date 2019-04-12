package com.henley.upgrade.interfaces.impl;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.view.Window;

import com.henley.upgrade.R;
import com.henley.upgrade.UpdaterConfiguration;
import com.henley.upgrade.interfaces.UpdateCheckUIHandler;
import com.henley.upgrade.model.UpdateInfo;
import com.henley.upgrade.utils.UIHandleUtils;

/**
 * 默认的提示更新UI处理
 *
 * @author Henley
 * @date 2016/12/14 13:11
 */
public final class DefaultUpdateCheckUIHandler implements UpdateCheckUIHandler {

    private UpdaterConfiguration mConfig;
    private Context mContext;

    public DefaultUpdateCheckUIHandler(UpdaterConfiguration config) {
        this.mConfig = config;
        this.mContext = mConfig.getContext();
    }

    @Override
    public void hasUpdate() {
        UpdateInfo updateInfo = mConfig.getUpdateInfo();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(UIHandleUtils.getString(mContext, R.string.update_tips));
        builder.setMessage(updateInfo.getUpdateContent());
        if (!updateInfo.isForceUpdate()) {
            builder.setNegativeButton(UIHandleUtils.getString(mContext, R.string.update_ask_later), null);
            if (updateInfo.isIgnorable()) {
                builder.setNeutralButton(UIHandleUtils.getString(mContext, R.string.update_ignore_version), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mConfig.getIgnoreHandler().ignore();
                    }
                });
            }
        }
        builder.setPositiveButton(mContext.getString(R.string.update_immediate), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mConfig.getDownloader().download();
            }
        });
        AlertDialog dialog = builder.create();
        boolean forceUpdate = updateInfo.isForceUpdate();
        dialog.setCancelable(!forceUpdate);
        dialog.setCanceledOnTouchOutside(!forceUpdate);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        dialog.show();
    }

    @Override
    public void noUpdate() {
        UIHandleUtils.showToast(mContext, R.string.no_update);
    }

    @Override
    public void checkError(String error) {
        UIHandleUtils.showToast(mContext, R.string.check_error);
    }

}
