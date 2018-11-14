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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yuqianhao.support.R;

public class UpdateDialog implements View.OnClickListener{
    private Dialog mContextDialog;//Dialog实例
    private Context mContext;
    private View mRootView;//根布局
    private LayoutInflater mLayoutInflater;//布局实例化
    private DisplayMetrics mDisplayMetrics;
    /**基本控件，主动修改的控件*/
    private TextView mDialogTitle;
    private TextView mDialogVersionName;
    private TextView mDialogUpdateDate;
    private TextView mDialogUpdateMessage;
    private View mProgressLayout;
    private ProgressBar mProgressView;
    private View mCancelUpdateButton;
    private TextView mUpdateButton;
    private OnUpdateClickListener onUpdateClickListener=null;

    @Override
    public void onClick(View v) {
        if(onUpdateClickListener==null){return;}
        int i = v.getId();
        if (i == R.id.updatedialog_noupdate) {
            onUpdateClickListener.onCancelUpdateClick(this);
        } else if (i == R.id.updatedialog_update) {
            onUpdateClickListener.onUpdateNowClick(this,mCancelUpdateButton,mProgressLayout,mUpdateButton,mProgressView);
        }
    }

    public interface OnUpdateClickListener{
        void onCancelUpdateClick(UpdateDialog dialog);
        void onUpdateNowClick(UpdateDialog dialog, View cancleButtonLayout, View rootProgressLayout, TextView updateButton, ProgressBar progressBar);
    }

    public void setOnUpdateClickListener(OnUpdateClickListener onUpdateClickListener){
        this.onUpdateClickListener=onUpdateClickListener;
    }

    public UpdateDialog(Activity context){
        mContext=context;
        mLayoutInflater= context.getLayoutInflater();
        mDisplayMetrics=new DisplayMetrics();
        WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        buildDialog();
    }

    public UpdateDialog(Context context){
        mContext=context;
        mLayoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDisplayMetrics=new DisplayMetrics();
        WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        buildDialog();
    }


    private void buildDialog(){
        mRootView=mLayoutInflater.inflate(R.layout.dialog_update,null,false);
        mDialogTitle=  mRootView.findViewById(R.id.updatedialog_title);
        mDialogVersionName=mRootView.findViewById(R.id.updatedialog_versionnmame);
        mDialogUpdateDate=mRootView.findViewById(R.id.updatedialog_date);
        mDialogUpdateMessage=mRootView.findViewById(R.id.updatedialog_updatemsg);
        mProgressLayout=mRootView.findViewById(R.id.updatedialog_progress_layout);
        mProgressView=mRootView.findViewById(R.id.updatedialog_progress_progress);
        mProgressLayout.setVisibility(View.GONE);
        mCancelUpdateButton=mRootView.findViewById(R.id.updatedialog_noupdate);
        mCancelUpdateButton.setOnClickListener(this);
        mUpdateButton=mRootView.findViewById(R.id.updatedialog_update);
        mUpdateButton.setOnClickListener(this);
        mContextDialog=new Dialog(mContext, R.style.basisDialogBackground);
        mContextDialog.setCanceledOnTouchOutside(false);
        mContextDialog.setContentView(mRootView);
        mContextDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });
        Window window=mContextDialog.getWindow();
        window.setWindowAnimations(R.style.basisDialogAnimation);
        mRootView.setLayoutParams(new FrameLayout.LayoutParams((int) (mDisplayMetrics.widthPixels*0.85), ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void setDialogTitle(String title){
        mDialogTitle.setText(title);
    }

    public void setVersionName(String versionName){
        mDialogVersionName.setText(versionName);
    }

    public void setUpdateDate(String date){
        mDialogUpdateDate.setText(date);
    }

    public void setUpdateMessage(String message){
        mDialogUpdateMessage.setText(Html.fromHtml(message));
    }


    public boolean isShowing(){
        return mContextDialog.isShowing();
    }

    public void dismiss(){
        if(isShowing()){
            mContextDialog.dismiss();
        }
    }

    public void show(){
        mContextDialog.show();
    }


}
