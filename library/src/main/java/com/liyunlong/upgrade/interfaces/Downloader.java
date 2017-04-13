package com.liyunlong.upgrade.interfaces;

/**
 * 文件下载接口
 *
 * @author liyunlong
 * @date 2016/12/14 11:34
 */
public interface Downloader {

    /**
     * 开始下载
     */
    void download();

    /**
     * 取消下载
     */
    void cancel();
}
