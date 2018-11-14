package com.yuqianhao.support.notif;

import android.app.Activity;

public class NotifyServiceManager {
    private NotifyServiceManager(){}

    private static final INotifyService NOTIFY_SERVICE=new NotifyServiceImplV0();

    /**
     * 将INotifyService绑定到参数1指定的Activity中
     * */
    public static final INotifyService getInstance(Activity activity){
        ((NotifyServiceImplV0)NOTIFY_SERVICE).setContext(activity);
        return NOTIFY_SERVICE;
    }

    /**
     * 将INotifyService绑定到参数1指定的Activity中并绑定消息关闭通知监听器
     * */
    public static final INotifyService getInstance(Activity activity,
                                                   NotifyServiceImplV0.OnMessageNotifyCallback onMessageNotifyCallback){
        NotifyServiceImplV0 instance= (NotifyServiceImplV0) getInstance(activity);
        instance.setOnMessageNotifyCallback(onMessageNotifyCallback);
        return instance;
    }

    public static final void setNotifyServiceActivity(INotifyService iNotifyService,Activity activity){
        if(iNotifyService!=null && activity!=null){
            ((NotifyServiceImplV0)iNotifyService).setContext(activity);
        }
    }

    public static final void clearNotifyStack(INotifyService notifyService){
        if(notifyService!=null){
            ((NotifyServiceImplV0)notifyService).clearNotifyNow();
        }
    }

}
