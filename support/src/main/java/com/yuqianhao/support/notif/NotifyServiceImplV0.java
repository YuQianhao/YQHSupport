package com.yuqianhao.support.notif;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.yuqianhao.support.utils.Stdlib;


public class NotifyServiceImplV0 implements INotifyService {

    public interface OnMessageNotifyCallback{
        void onMessageNotifyClose();
    }

    private Activity activity;
    private WindowManager windowManager;
    private int lastStabarColor;//上一次状态栏的颜色
    private boolean laststabarBright;//上一次状态栏是否为亮色
    private boolean isShowing=false;//提示框是否正在显示
    private TextView notifyTextView;//提示框的View
    private static final int WHAT_DISTORYDIALOG=1;//申请销毁正在展示的提示框
    private OnMessageNotifyCallback onMessageNotifyCallback=null;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case WHAT_DISTORYDIALOG:{
                    isShowing=false;
                    if(notifyTextView!=null){
                        windowManager.removeView(notifyTextView);
                        notifyTextView=null;
                    }
                    setStabar(msg.arg1,msg.arg2==1);
                    if(onMessageNotifyCallback!=null){
                        onMessageNotifyCallback.onMessageNotifyClose();
                    }
                }break;
            }
        }
    };

    public void setOnMessageNotifyCallback(OnMessageNotifyCallback onMessageNotifyCallback){
        this.onMessageNotifyCallback=onMessageNotifyCallback;
    }

    public NotifyServiceImplV0(Activity activity){
        this.activity=activity;
        this.windowManager=activity.getWindowManager();
        notifyTextView=null;
    }

    public NotifyServiceImplV0(){
        notifyTextView=null;
    }

    public void setContext(Activity activity){
        this.activity=activity;
        this.windowManager=activity.getWindowManager();
    }


    /**
     * 显示一个提示的View
     * @param msg 要显示的内容
     * @param backgroundColor 提示框的背景颜色
     * @param textColor 提示框的文字颜色
     * @param stabarBright 当前状态栏的背景颜色
     * @param stabarColor 当前状态栏是否为亮色
     * */
    @Override
    public void showMessageView(String msg,
                                int backgroundColor,
                                boolean setStabarBright,
                                int textColor,
                                int stabarColor,
                                boolean stabarBright) {
        if(activity.isFinishing()){
            Log.w("INotifyService",
                    "activity " +activity.getPackageName()+
                            activity.getClass().getName()+" "+activity.hashCode()+" isFinishing");
            return;
        }
        if(notifyTextView==null){
            notifyTextView=new TextView(activity);
        }
        makeTextView(msg,backgroundColor,textColor);
        addViewToWindow(notifyTextView);
        lastStabarColor=stabarColor;
        laststabarBright=stabarBright;
        setStabar(backgroundColor,setStabarBright);
        if(isShowing){
            Message message=handler.obtainMessage();
            message.what=WHAT_DISTORYDIALOG;
            message.arg1=stabarColor;
            message.arg2=stabarBright?1:0;
            handler.sendMessageDelayed(message,1500);
        }
    }

    private void setStabar(int backgroundColor, boolean setStabarBright) {
        StatusBarCompat.setStatusBarColor(activity,backgroundColor,setStabarBright);
    }

    private void addViewToWindow(View view) {
        if(!isShowing){
            WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
            layoutParams.gravity=Gravity.TOP;
            layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height= Stdlib.dip2px(activity,51);
            layoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            layoutParams.type=WindowManager.LayoutParams.TYPE_APPLICATION;
            windowManager.addView(view,layoutParams);
            isShowing=true;
        }
    }

    private void makeTextView(String msg, int backgroundColor, int textColor) {
        notifyTextView.setText(msg);
        notifyTextView.setBackgroundColor(backgroundColor);
        notifyTextView.setTextColor(textColor);
        notifyTextView.setGravity(Gravity.CENTER);
    }

    public void clearNotifyNow(){
        if(isShowing){
            handler.removeMessages(WHAT_DISTORYDIALOG);
            isShowing=false;
            if(notifyTextView!=null){
                windowManager.removeView(notifyTextView);
                notifyTextView=null;
            }
            setStabar(lastStabarColor,laststabarBright);
            if(onMessageNotifyCallback!=null){
                onMessageNotifyCallback.onMessageNotifyClose();
            }
        }
    }
}
