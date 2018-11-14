package com.yuqianhao.support.service.download;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.yuqianhao.support.thread.ThreadManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadImplV0 implements IDownloadIntercafe,Handler.Callback{
    /**UI回调监听器*/
    private IDownloadListener uiCallback;
    /**子线程回调监听器*/
    private IDownloadListener threadCallback;
    /**下载地址*/
    private String downloadPath;
    /**消息循环*/
    private Handler uiThreadHandler=new Handler(Looper.getMainLooper(),this);
    /**下载的文件保存的位置*/
    private String filePath;
    private static final OkHttpClient okHttpClient=new OkHttpClient();

    /**发送更新进度的消息*/
    private static final int WHAT_UPDATE_PROGRESS=0;
    /**发送开始下载的消息*/
    private static final int WHAT_DOWNLOAD_START=1;
    /**发送下载结束消息*/
    private static final int WHAT_DOWMLOAD_COMPLETE=2;
    /**发送下载失败消息*/
    private static final int WHAT_DOWNLOAD_ERROR=3;
    /**如果下载过程中抛出IOException则传递该ErrorCode*/
    private static final int ERROR_CODE_EXCEPTION=-1;
    /**是否正在下载*/
    private boolean candownload=false;
    /**是否可以继续下载*/
    private boolean downloading=true;

    public DownloadImplV0(String path,String filepath){
        this.downloadPath=path;
        this.filePath=filepath;
    }

    public void setDownloadPath(String downloadPath){
        this.downloadPath=downloadPath;
    }

    public void setFilePath(String filePath){
        this.filePath=filePath;
    }

    @Override
    public boolean download() {
        if(downloadPath==null){
            throw new NullPointerException("downloadPath dont is NULL!");
        }
        if(filePath==null){
            throw new NullPointerException("filePath dont is NULL!");
        }
        ThreadManager.getThreadManagerInterface().run(new Runnable() {
            @Override
            public void run() {
                Request request=new Request.Builder().url(downloadPath).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message startMsg=uiThreadHandler.obtainMessage();
                        startMsg.what=WHAT_DOWNLOAD_START;
                        uiThreadHandler.sendMessage(startMsg);
                        Message errortMsg=uiThreadHandler.obtainMessage();
                        errortMsg.what=WHAT_DOWNLOAD_ERROR;
                        errortMsg.arg1=ERROR_CODE_EXCEPTION;
                        uiThreadHandler.sendMessage(errortMsg);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message startMsg=uiThreadHandler.obtainMessage();
                        startMsg.what=WHAT_DOWNLOAD_START;
                        uiThreadHandler.sendMessage(startMsg);
                        if(response.code()!=200){
                            Message errortMsg=uiThreadHandler.obtainMessage();
                            errortMsg.what=WHAT_DOWNLOAD_ERROR;
                            errortMsg.arg1=response.code();
                            uiThreadHandler.sendMessage(errortMsg);
                            return;
                        }
                        BufferedInputStream bufferedInputStream=new BufferedInputStream(response.body().byteStream());
                        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(filePath));
                        int maxValue= (int) response.body().contentLength();
                        byte[] readDataV0=new byte[4096];
                        int readlength=0;
                        int readTmpLen;
                        while((readTmpLen=bufferedInputStream.read(readDataV0))!=-1) {
                            bufferedOutputStream.write(readDataV0,0,readTmpLen);
                            readlength += readTmpLen;
                            Message msg_update = uiThreadHandler.obtainMessage();
                            msg_update.what = WHAT_UPDATE_PROGRESS;
                            msg_update.arg1 = readlength;
                            msg_update.arg2 = maxValue;
                            msg_update.obj = Double.valueOf((double)readlength / (double)maxValue);
                            uiThreadHandler.sendMessage(msg_update);
                        }
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                        bufferedInputStream.close();
                        Message completeMsg=uiThreadHandler.obtainMessage();
                        completeMsg.what=WHAT_DOWMLOAD_COMPLETE;
                        completeMsg.obj=new File(filePath);
                        uiThreadHandler.sendMessage(completeMsg);
                    }
                });
            }
        });
        return true;
    }

    @Override
    public void cancel() {
        downloading=false;
    }

    @Override
    public boolean canDownload() {
        return candownload;
    }

    @Override
    public void setDownloadCallback(IDownloadListener downloadListener) {
        this.uiCallback=downloadListener;
    }

    @Override
    public void setDownloadCallbackOnThread(IDownloadListener downloadListener) {
        this.threadCallback=downloadListener;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch(msg.what){
            case WHAT_UPDATE_PROGRESS:{
                int v=msg.arg1;
                int m= msg.arg2;
                double p=(Double) msg.obj;
                if(uiCallback!=null){
                    uiCallback.onProgressNotify(v,m,p);
                }
                if(threadCallback!=null){
                    threadCallback.onProgressNotify(v,m,p);
                }
            }break;
            case WHAT_DOWNLOAD_START:{
                candownload=true;
                if(uiCallback!=null){
                    uiCallback.onStart();
                }
                if(threadCallback!=null){
                    threadCallback.onStart();
                }
            }break;
            case WHAT_DOWMLOAD_COMPLETE:{
                candownload=false;
                if(uiCallback!=null){
                    uiCallback.onComplete((File) msg.obj);
                }
                if(threadCallback!=null){
                    threadCallback.onComplete((File) msg.obj);
                }
            }break;
            case WHAT_DOWNLOAD_ERROR:{
                candownload=false;
                if(uiCallback!=null){
                    uiCallback.onError(msg.arg1);
                }
                if(threadCallback!=null){
                    threadCallback.onError(msg.arg1);
                }
            }break;
        }
        return true;
    }

}
