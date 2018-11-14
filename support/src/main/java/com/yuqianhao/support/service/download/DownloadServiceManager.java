package com.yuqianhao.support.service.download;

public class DownloadServiceManager {
    private DownloadServiceManager(){}

    public static final void startDownloadTask(String downloadURL,
                                               String downloadFilePath,
                                               IDownloadIntercafe.IDownloadListener iDownloadListener){
        IDownloadIntercafe downloadIntercafe=new DownloadImplV0(downloadURL,downloadFilePath);
        downloadIntercafe.setDownloadCallback(iDownloadListener);
        downloadIntercafe.download();
    }

}
