package com.yuqianhao.support.notif;

import android.content.Context;
import android.widget.Toast;

public class ToastImpl implements IToast {
    private static Toast instance;

    @Override
    public void showToast(Context context, String msg) {
        if(instance==null){
            instance=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else{
            instance.setText(msg);
        }
        instance.show();
    }
}
