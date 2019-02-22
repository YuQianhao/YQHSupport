package com.yuqianhao.support.application;

import android.app.ActivityManager;
import android.app.Application;
import com.tencent.mmkv.MMKV;
import com.yuqianhao.support.log.ILogForwarding;
import com.yuqianhao.support.notif.YToast;

import java.util.List;

public class YApplication extends Application implements Thread.UncaughtExceptionHandler,ILogForwarding {

    private static YApplication application=null;

    private String PACKAGENAME;



    protected boolean canOpenSetDefaultUncaughtExceptionHandler(){
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        PACKAGENAME=getPackageName();
        MMKV.initialize(this);
        if(canOpenSetDefaultUncaughtExceptionHandler()){
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
        application=this;
        ActivityManager activityManager= (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos=activityManager.getRunningAppProcesses();
        for(ActivityManager.RunningAppProcessInfo runningAppProcessInfo:appProcessInfos){
            onStart(runningAppProcessInfo.processName);
        }
    }


    public static final YApplication getInstance(){
        return application;
    }


    protected void onStart(String processName){
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

    }

    @Override
    public void onLogProcessor(int level, Class javaClassName, String logMsg, String statckMsg) {

    }

    @Override
    public String onLogCatFilterName() {
        return PACKAGENAME;
    }

    @Override
    public boolean canPrintLog() {
        return true;
    }

    @Override
    public boolean canForwarding() {
        return true;
    }

    public final void showToast(Object o){
        YToast.showToast(this,o.toString());
    }

}
