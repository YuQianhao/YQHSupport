package com.yuqianhao.support.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

public class Stdlib {
    private Stdlib(){}


    public static int px2dip(float pxvalue, Context context){
        final float dip=context.getResources().getDisplayMetrics().density;
        return (int) (pxvalue/dip+0.5f);
    }

    public static int dip2px(Activity activity, float dipvalue){
        final float px=activity.getResources().getDisplayMetrics().density;
        return (int) (dipvalue*px+0.5f);
    }

    /**
     * 向系统发送申请安装APP
     * @param activity Activity
     * @param file 要安装的app的文件
     * */
    public static void installApplication(Activity activity, File file){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri applicationPackage;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            applicationPackage= FileProvider.getUriForFile(activity,
                    activity.getPackageName()+".provider",
                    file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }else{
            applicationPackage=Uri.fromFile(file);
        }
        intent.setDataAndType(applicationPackage, "application/vnd.android.package-archive");
        activity.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
        activity.finish();
    }



}
