package com.yuqianhao.support.notif;

import android.content.Context;

public class YToast {
    private YToast(){}

    private static final IToast TOAST=new ToastImpl();

    public static final void showToast(Context context,String msg){
        TOAST.showToast(context, msg);
    }

}
