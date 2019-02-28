package com.yuqianhao.support.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yuqianhao.support.R;
import com.yuqianhao.support.drawable.BitmapBlurManager;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class MessageDialog {
    private Dialog mContextDialog;
    private Activity mContext;
    private View mActivityCacheRootView;
    private Bitmap mActivityBlurDrawable;
    private TextView mDefaultTextView;
    private String mDefaultMessage;
    private List<TextView> mButtonList;

    public MessageDialog(Activity activity){
        mContext=activity;
        mActivityCacheRootView=activity.getWindow().getDecorView();
        Bitmap tmpCacheDrawable=null;
        if(mActivityCacheRootView.getWidth()!=0){
            mActivityCacheRootView.setDrawingCacheEnabled(true);
            mActivityCacheRootView.buildDrawingCache();
            tmpCacheDrawable=Bitmap.createBitmap(mActivityCacheRootView.getDrawingCache());
            mActivityBlurDrawable=BitmapBlurManager.blur(activity,tmpCacheDrawable);
            mActivityCacheRootView.setDrawingCacheEnabled(false);
        }else{
            int width=mActivityCacheRootView.getMeasuredWidth();
            int height=mActivityCacheRootView.getMeasuredHeight();
            tmpCacheDrawable=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
            Canvas canvas=new Canvas(tmpCacheDrawable);
            mActivityCacheRootView.draw(canvas);
        }
        if(tmpCacheDrawable!=null){
            tmpCacheDrawable.recycle();
        }
        mButtonList=new ArrayList<>();
    }

    public void setMessage(CharSequence charSequence){
        mDefaultMessage=charSequence.toString();
    }

    public void addButton(String btnText,View.OnClickListener v){
        TextView textView=new TextView(mContext);
        LinearLayout.LayoutParams layoutParams=
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(R.drawable.default_rectbutton);
        textView.setTextColor(0xFF037BFF);
        textView.setText(btnText);
        textView.setOnClickListener(v);
        mButtonList.add(textView);
    }

    private void buildLayout(){
        LinearLayout linearLayout= new LinearLayout(mContext);
        FrameLayout.LayoutParams layoutParams=
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT);
        layoutParams.gravity=Gravity.CENTER;
        mDefaultTextView=new TextView(mContext);
        linearLayout.addView(mDefaultTextView);
        mContextDialog=new Dialog(mContext,R.style.basisDialogBackground);
        mContextDialog.setCanceledOnTouchOutside(false);
        mContextDialog.setContentView(linearLayout);
        mContextDialog.getWindow().setWindowAnimations(R.style.basisDialogAnimation);
        linearLayout.setLayoutParams(layoutParams);
        for(TextView btn:mButtonList){
            linearLayout.addView(btn);
        }
        linearLayout.setBackground(new BitmapDrawable(mActivityBlurDrawable));
    }

    public boolean isShowing(){
        return mContextDialog!=null && mContextDialog.isShowing();
    }

    public void close(){
        if(isShowing()){
            mContextDialog.dismiss();
        }
    }

    public void show(){
        if(!isShowing()){
            buildLayout();
            mContextDialog.show();
        }
    }

}
