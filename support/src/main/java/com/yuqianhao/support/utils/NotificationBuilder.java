package com.yuqianhao.support.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IdRes;

/**
 * 创建通知栏的简化对象
 * */
public class NotificationBuilder {
    private Context context;
    private String V26ChannelID;
    private String V26ChannelName;
    private String ticker;
    private String contentText;
    private String contentTitle;
    private int smallIconID;
    private Bitmap largeIconBitmap;
    private Intent contentIntent;
    private Notification.Builder notificationBuilder;
    private NotificationChannel V26NotificationChannel;
    private NotificationManager notificationManager;

    /**
     * 创建通知栏Builder
     * @param context 需要显示这个通知的Context
     * */
    public NotificationBuilder(Context context){
        this.context=context;
        notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * 设置通知栏的标题
     * @param title 通知栏的标题
     * @return 返回通知栏对象本身
     * */
    public NotificationBuilder setTitle(String title){
        this.contentTitle=title;
        return this;
    }

    /**
     * 设置通知栏的内容
     * @param text 通知栏的内容
     * @return 返回通知栏对象本身
     * */
    public NotificationBuilder setText(String text){
        this.contentText=text;
        return this;
    }

    /**
     * 通知栏渠道的ID，当Android8.0+的时候需要
     * @param chennelID 通知栏的渠道ID
     * @return 返回通知栏对象本身
     * */
    public NotificationBuilder setChannelID(String chennelID){
        this.V26ChannelID=chennelID;
        return this;
    }

    /**
     * 通知栏渠道的名称，当Android8.0+的时候需要
     * @param channelName 通知栏的渠道名称
     * @return 返回通知栏对象本身
     * */
    public NotificationBuilder setChannelName(String channelName){
        this.V26ChannelName=channelName;
        return this;
    }

    /**
     * 通知栏悬挂式通知的内容
     * @param ticker 通知栏的悬挂内容
     * @return 返回通知栏对象本身
     * */
    public NotificationBuilder setTicker(String ticker){
        this.ticker=ticker;
        return this;
    }

    /**
     * 设置通知栏的小图标的ID
     * @param resID 通知栏小图标的ID
     * @return 返回通知栏对象本身
     * */
    public NotificationBuilder setSmallIcon(int resID){
        this.smallIconID=resID;
        return this;
    }

    /**
     * 设置通知栏的大图标
     * @param bitmap 通知栏大图标
     * @return 返回通知栏对象本身
     * */
    public NotificationBuilder setLargeIcon(Bitmap bitmap){
        this.largeIconBitmap=bitmap;
        return this;
    }

    /**
     * 设置通知栏的点击之后响应的Intent
     * @param intent 通知栏响应的Intent
     * @return 返回通知栏对象本身
     * */
    public NotificationBuilder setContentIntent(Intent intent){
        this.contentIntent=intent;
        return this;
    }

    private void build(){
        if(smallIconID<=0){
            throw new RuntimeException(getClass().getName()+":smallIconID不能为空，请使用setSmallIcon设置小图标");
        }
        PendingIntent pendingIntent=
                PendingIntent.getActivity(context,0,contentIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            V26NotificationChannel=new NotificationChannel(V26ChannelID,
                    V26ChannelName,
                    NotificationManager.IMPORTANCE_HIGH);
            V26NotificationChannel.enableLights(true);
            V26NotificationChannel.setLightColor(Color.GREEN);
            V26NotificationChannel.enableVibration(true);
            notificationBuilder=new Notification.Builder(context,V26ChannelID);
            notificationManager.createNotificationChannel(V26NotificationChannel);
        }else{
            notificationBuilder=new Notification.Builder(context);
        }
        notificationBuilder.setTicker(ticker)
                .setContentText(contentText)
                .setContentTitle(contentTitle)
                .setSmallIcon(smallIconID)
                .setContentIntent(pendingIntent);
        if(largeIconBitmap!=null){
            notificationBuilder.setLargeIcon(largeIconBitmap);
        }
    }

    /**
     * 将这个对象转换成系统{@link Notification.Builder}这个对象的实例
     * @return {@link Notification.Builder}对象的实例
     * */
    public Notification.Builder makeNotificationBuilder(){
        build();
        return notificationBuilder;
    }

    /**
     * 触发通知栏的通知
     * @param notifyID 通知的ID
     * */
    public void notify(int notifyID){
        build();
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
//            notificationManager.createNotificationChannel(V26NotificationChannel);
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationManager.notify(notifyID,notificationBuilder.build());
        }
    }


}
