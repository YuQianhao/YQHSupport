package com.yuqianhao.support.service.download;

import java.io.File;

public interface IDownloadIntercafe {
    /**
     * 开始下载
     * @return 创建下载任务成功则返回true，否则则返回false
     * @return 2.0 返回值无意义，如果不能下载直接抛出Exception
     * */
    boolean download();

    /**
     * 取消下载
     * */
    void cancel();

    /**
     * 是否有任务正在下载
     * */
    boolean canDownload();

    /**
     * 设置下载监听器，用此方法设置的监听器会在UI线程中被回调
     * @param downloadListener 下载状态监听器
     * */
    void setDownloadCallback(IDownloadListener downloadListener);

    /**
     * 设置下载监听器，用此方法设置的监听器会在子线程中被回调
     * @param downloadListener 下载状态监听器
     * */
    void setDownloadCallbackOnThread(IDownloadListener downloadListener);

    /**
     * 下载监听回调接口
     * */
    interface IDownloadListener{
        /**
         * 下载过程中会频繁调用此函数来报告进度
         * @param value 当前已经下载完成的长度
         * @param maxValue 要下载的文件的总长度
         * @param proportion 下载完成的进度百分比
         * */
        void onProgressNotify(int value, int maxValue, double proportion);
        /**
         * 下载过程中被错误中断，如果被错误中断不会调用onComplete方法
         * @param errorCode 错误编码
         * */
        void onError(int errorCode);
        /**
         * 下载完成回调接口，注意，如果用户主动取消了下载任务视为下载完成
         * @param file 下载完成的文件
         * */
        void onComplete(File file);
        /**
         * 开始下载回调接口
         * */
        void onStart();
    }
}
