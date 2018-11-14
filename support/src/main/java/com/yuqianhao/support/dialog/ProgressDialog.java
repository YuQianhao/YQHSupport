package com.yuqianhao.support.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuqianhao.support.R;
import com.yuqianhao.support.animation.DefaultProgressDrawable;

public class ProgressDialog {
    private Dialog dialog;
    private LayoutInflater layoutInflater;
    private ImageView loaddingImageView;
    private TextView loaddingTextView;
    private Activity activity;
    private DisplayMetrics mDisplayMetrics;
    private DefaultProgressDrawable defaultProgressDrawable;

    public ProgressDialog(Activity activity){
        this.activity=activity;
        layoutInflater=LayoutInflater.from(activity);
        mDisplayMetrics=new DisplayMetrics();
        WindowManager windowManager= (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        defaultProgressDrawable=new DefaultProgressDrawable();
        initDialog();
    }

    private void initDialog() {
        View rootView=layoutInflater.inflate(R.layout.dialog_progress,null,false);
        loaddingImageView=rootView.findViewById(R.id.dialog_progress_imageview);
        loaddingImageView.setImageDrawable(defaultProgressDrawable);
        loaddingTextView=rootView.findViewById(R.id.dialog_progress_textview);
        dialog=new Dialog(activity, R.style.progressDialogBackground);
        dialog.setContentView(rootView);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        Window window=dialog.getWindow();
        window.setWindowAnimations(R.style.basisDialogAnimation);
        rootView.setLayoutParams(new FrameLayout.LayoutParams((int) (mDisplayMetrics.widthPixels*0.85), ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void show(){
        if(!dialog.isShowing()){
            defaultProgressDrawable.start();
            dialog.show();
        }
    }

    public void close(){
        if(dialog.isShowing()){
            defaultProgressDrawable.stop();
            dialog.dismiss();
        }
    }

    public void setProgressMessage(String msg){
        loaddingTextView.setText(Html.fromHtml(msg));
    }

}
