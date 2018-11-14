package com.yuqianhao.support.notif;

import android.content.Context;

/**
 * 显示Toast的动作接口
 * */
public interface IToast {
    /**
     * 显示一个Toast
     * @param context 上下文
     * @param msg 要显示的字符串
     * */
    void showToast(Context context,String msg);

}
