package com.yuqianhao.support.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuqianhao.support.R;

/**
 * Created by 于千皓 on 2017/5/10.
 * 支持Html以及可以随意添加额外控件的对话框
 */

public class BasisDialog{
    private Dialog mContextDialog;//Dialog实例
    private Context mContext;
    private View mRootView;//根布局
    private LayoutInflater mLayoutInflater;//布局实例化
    private DisplayMetrics mDisplayMetrics;
    /**基本控件，主动修改的控件*/
    private TextView mDialogTitle;
    private TextView mDialogContextMessage;
    private TextView mDialogButtonLeft;
    private TextView mDialogButtonRight;
    private TextView mDialogButtonMiddle;
    private LinearLayout mDialogViewLinearLayout;
    /**分割线*/
    private TextView mDialogDivisionLeft;
    private TextView mDialogDivisionMiddle;
    /**控件绑定的事件和资源*/
    private String mTitle;
    private String mContextMessage;
    private String mButtonLeft;
    private String mButtonRight;
    private String mButtonMiddle;
    private View.OnClickListener mButtonLeftListener;
    private View.OnClickListener mButtonMiddleListener;
    private View.OnClickListener mButtonRightListener;
    private Drawable mContextMessageDrawable;

    public BasisDialog(Activity context){
        mContext=context;
        mLayoutInflater= context.getLayoutInflater();
        mDisplayMetrics=new DisplayMetrics();
        WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        buildDialog();
        initResources();
    }

    public BasisDialog(Context context){
        mContext=context;
        mLayoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDisplayMetrics=new DisplayMetrics();
        WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        buildDialog();
        initResources();
    }

    public void setLeftDrawable(Drawable drawable){
        mContextMessageDrawable=drawable;
    }

    private void buildDialog(){
        mRootView=mLayoutInflater.inflate(R.layout.dialog_basisi,null,false);
        mDialogTitle= (TextView) mRootView.findViewById(R.id.basisdialog_title);
        mDialogContextMessage= (TextView) mRootView.findViewById(R.id.basisdialog_context);
        mDialogButtonLeft= (TextView) mRootView.findViewById(R.id.basisdialog_buttonleft);
        mDialogButtonMiddle= (TextView) mRootView.findViewById(R.id.basisdialog_buttonmiddle);
        mDialogButtonRight= (TextView) mRootView.findViewById(R.id.basisdialog_buttonright);
        mDialogViewLinearLayout= (LinearLayout) mRootView.findViewById(R.id.basisdialog_lineralayout);
        mDialogDivisionLeft= (TextView) mRootView.findViewById(R.id.basisdialog_lefttext);
        mDialogDivisionMiddle= (TextView) mRootView.findViewById(R.id.basisdialog_middletext);
        mContextDialog=new Dialog(mContext, R.style.basisDialogBackground);
        mContextDialog.setCanceledOnTouchOutside(false);
        mContextDialog.setContentView(mRootView);
        mContextDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
//                YWindow.startWindowBackgroundAlpha(mContext,0.35f,0.0065f,100,1,1,1f);
            }
        });
        Window window=mContextDialog.getWindow();
        window.setWindowAnimations(R.style.basisDialogAnimation);
        mRootView.setLayoutParams(new FrameLayout.LayoutParams((int) (mDisplayMetrics.widthPixels*0.75), ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void initResources(){
        mTitle=null;
        mContextMessage=null;
        mButtonLeft=null;
        mButtonRight=null;
        mButtonMiddle=null;
        mButtonLeftListener=null;
        mButtonMiddleListener=null;
        mButtonRightListener=null;
        mContextMessageDrawable=null;
    }


    /**
     *
     * 设置标题
     * */
    public BasisDialog setTitle(CharSequence charSequence){
        mTitle=charSequence.toString();
        return this;
    }

    /**
     * 设置消息内容
     * */
    public BasisDialog setContextMessage(CharSequence charSequence){
        mContextMessage=charSequence.toString();
        return this;
    }

    /**
     * 设置左按钮
     * */
    public BasisDialog setLeftButton(CharSequence title, View.OnClickListener onClickListener){
        mButtonLeft=title.toString();
        mButtonLeftListener=onClickListener;
        return this;
    }

    /**
     * 设置中间按钮
     * */
    public BasisDialog setMiddleButton(CharSequence title, View.OnClickListener onClickListener){
        mButtonMiddle=title.toString();
        mButtonMiddleListener=onClickListener;
        return this;
    }

    /**
     * 设置右按钮
     * */
    public BasisDialog setRightButton(CharSequence title, View.OnClickListener onClickListener){
        mButtonRight=title.toString();
        mButtonRightListener=onClickListener;
        return this;
    }

    /**
     *
     * 向布局中添加控件
     * */
    public BasisDialog addContextView(View view){
        mDialogViewLinearLayout.addView(view);
        return this;

    }

    public BasisDialog show(){
        deployment();
//        YWindow.startWindowBackgroundAlpha(mContext,1f,0.0065f,100,0,0,0);
        mContextDialog.show();
        return this;
    }


    public boolean isShowing(){
        return mContextDialog.isShowing();
    }

    public void dismiss(){
        if(isShowing()){
//            YWindow.startWindowBackgroundAlpha(mContext,0.35f,0.0065f,100,1,1,1f);
            mContextDialog.dismiss();
        }
    }

    public BasisDialog setCanceledOnTouchOutside(boolean canceled){
        mContextDialog.setCanceledOnTouchOutside(canceled);
        return this;
    }

    public BasisDialog setOnCancelListener(DialogInterface.OnCancelListener onCancelListener){
        mContextDialog.setOnCancelListener(onCancelListener);
        return this;
    }

    public BasisDialog setTitleTextSize(float size){
        mDialogTitle.setTextSize(size);
        return this;
    }

    public BasisDialog setContextTextSize(float size){
        mDialogContextMessage.setTextSize(size);
        return this;
    }

    public BasisDialog setButtonTextSize(float size){
        mDialogButtonRight.setTextSize(size);
        mDialogButtonLeft.setTextSize(size);
        mDialogButtonMiddle.setTextSize(size);
        return this;
    }

    /**布局*/
    private void deployment(){
        if(mContextMessage!=null){
            mDialogContextMessage.setText(Html.fromHtml(mContextMessage));
        }else{
            mDialogContextMessage.setVisibility(View.GONE);
        }
        if(mTitle==null || mTitle.isEmpty()){
            mDialogTitle.setVisibility(View.GONE);
        }else{
            mDialogTitle.setText(Html.fromHtml(mTitle));
        }
        if(mButtonLeft==null){
            mDialogButtonLeft.setVisibility(View.GONE);
            mDialogDivisionLeft.setVisibility(View.GONE);
        }
        if(mButtonMiddle==null){
            mDialogButtonMiddle.setVisibility(View.GONE);
            mDialogDivisionMiddle.setVisibility(View.GONE);
        }
        if(mButtonRight==null){
            mDialogButtonRight.setVisibility(View.GONE);
        }
        if(mButtonLeft!=null){
            mDialogButtonLeft.setText(Html.fromHtml(mButtonLeft));
            mDialogButtonLeft.setOnClickListener(mButtonLeftListener);
        }
        if(mButtonMiddle!=null){
            mDialogButtonMiddle.setText(Html.fromHtml(mButtonMiddle));
            mDialogButtonMiddle.setOnClickListener(mButtonMiddleListener);
        }
        if(mButtonRight!=null){
            mDialogButtonRight.setText(Html.fromHtml(mButtonRight));
            mDialogButtonRight.setOnClickListener(mButtonRightListener);
        }
        if(mButtonLeft!=null && mButtonMiddle==null && mButtonRight==null){
            mDialogButtonLeft.setBackgroundResource(R.drawable.basisdialog_buttondefault);
            mDialogDivisionLeft.setVisibility(View.GONE);
            mDialogDivisionMiddle.setVisibility(View.GONE);
        }
        if(mButtonLeft!=null && mButtonMiddle==null && mButtonRight!=null){
            mDialogButtonLeft.setBackgroundResource(R.drawable.basisdialog_button_left);
            mDialogButtonRight.setBackgroundResource(R.drawable.basisdialog_button_right);
            mDialogDivisionMiddle.setVisibility(View.GONE);
        }
        if(mButtonLeft==null && mButtonMiddle!=null && mButtonRight==null){
            mDialogButtonMiddle.setBackgroundResource(R.drawable.basisdialog_button_middle);
            mDialogDivisionLeft.setVisibility(View.GONE);
            mDialogDivisionMiddle.setVisibility(View.GONE);
        }
        if(mButtonLeft!=null && mButtonMiddle!=null && mButtonRight==null){
            mDialogButtonLeft.setBackgroundResource(R.drawable.basisdialog_button_left);
            mDialogButtonMiddle.setBackgroundResource(R.drawable.basisdialog_button_right);
            mDialogDivisionMiddle.setVisibility(View.GONE);
        }

        if(mButtonLeft==null && mButtonMiddle!=null && mButtonRight!=null){
            mDialogButtonMiddle.setBackgroundResource(R.drawable.basisdialog_button_left);
            mDialogButtonRight.setBackgroundResource(R.drawable.basisdialog_button_right);
            mDialogDivisionLeft.setVisibility(View.GONE);
        }
        if(mButtonLeft!=null && mButtonMiddle!=null && mButtonRight!=null){
            mDialogButtonLeft.setBackgroundResource(R.drawable.basisdialog_button_left);
            mDialogButtonMiddle.setBackgroundResource(R.drawable.basisdialog_button_middle);
            mDialogButtonRight.setBackgroundResource(R.drawable.basisdialog_button_right);
        }
        if(mContextMessageDrawable!=null){
            int w= (int) (mDisplayMetrics.widthPixels*0.85);
            mContextMessageDrawable.setBounds(0,0,w/10,w/10);
            mDialogContextMessage.setCompoundDrawables(mContextMessageDrawable,null,null,null);
        }
    }

    public static final class Builder{
        private BasisDialog basisDialog;
        public Builder(Activity activity){
            basisDialog=new BasisDialog(activity);
        }

        public Builder message(CharSequence charSequence){
            basisDialog.setContextMessage(charSequence);
            return this;
        }

        public Builder messageSize(int size){
            basisDialog.setContextTextSize(size);
            return this;
        }

        public Builder title(CharSequence charSequence){
            basisDialog.setTitle(charSequence);
            return this;
        }

        public Builder titleSize(int size){
            basisDialog.setTitleTextSize(size);
            return this;
        }

        public Builder leftButton(CharSequence title,View.OnClickListener listener){
            basisDialog.setLeftButton(title,listener);
            return this;
        }

        public Builder middleButton(CharSequence title,View.OnClickListener listener){
            basisDialog.setMiddleButton(title,listener);
            return this;
        }

        public Builder rightButton(CharSequence title,View.OnClickListener listener){
            basisDialog.setRightButton(title,listener);
            return this;
        }

        public BasisDialog build(){
            return basisDialog;
        }



    }

}
