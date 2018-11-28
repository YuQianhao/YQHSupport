package com.yuqianhao.support.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yuqianhao.support.R;
import com.yuqianhao.support.utils.Stdlib;

import java.util.ArrayList;

/**
 * 选择对话框
 */

public class SelectDialog implements View.OnClickListener{
    private Dialog dialog;
    private View rootView;
    private Activity context;
    private TextView dialogTitleView;
    private ScrollView scrollView;
    private LinearLayout scrollLayout;
    private TextView exitView;
    private OnSelectDialogListener onSelectDialogListener;
    private ArrayList<TextView> itemViewArray;

    @Override
    public void onClick(View v) {
        if(onSelectDialogListener==null){
            return;
        }
        if(v.getId()== R.id.dialog_select_exit){
            onSelectDialogListener.onSelectItem(this,-1,"取消");
        }else{
            onSelectDialogListener.onSelectItem(this,v.getId(),itemViewArray.get(v.getId()).getText().toString());
        }
    }

    public interface OnSelectDialogListener{
        void onSelectItem(SelectDialog dialog, int i, String text);
    }


    public SelectDialog(Activity context){
        this.context=context;
        itemViewArray=new ArrayList<>();
        initDialog();
    }

    private void initDialog(){
        dialog=new Dialog(context, R.style.selectDialogBackground);
        Window window=dialog.getWindow();
        window.setWindowAnimations(R.style.selectDialogAnimation);
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0,0,0,0);
        WindowManager.LayoutParams lp=window.getAttributes();
        lp.width=WindowManager.LayoutParams.MATCH_PARENT;
        lp.height=WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        rootView=((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.dialog_select,null,false);
        exitView=rootView.findViewById(R.id.dialog_select_exit);
        exitView.setOnClickListener(this);
        scrollLayout=rootView.findViewById(R.id.dialog_select_layout);
        scrollView=rootView.findViewById(R.id.dialog_select_scroll);
        dialogTitleView=rootView.findViewById(R.id.dialog_select_title);
    }

    public void setOnSelectDialogListener(OnSelectDialogListener onSelectDialogListener){
        this.onSelectDialogListener=onSelectDialogListener;
    }

    public void setDialogTitle(String text){
        dialogTitleView.setText(Html.fromHtml(text));
    }

    public void setDialogTitle(Spanned spinner){
        dialogTitleView.setText(spinner);
    }

    private void buildDialog(){
        LinearLayout.LayoutParams lp= (LinearLayout.LayoutParams) scrollView.getLayoutParams();
        FrameLayout.LayoutParams layoutLayoutParams= (FrameLayout.LayoutParams) scrollLayout.getLayoutParams();
        if(itemViewArray.size()<4){
            lp.weight=-1;
            layoutLayoutParams.width= lp.width;
            lp.height= Stdlib.dip2px(context,itemViewArray.size()*45);
            layoutLayoutParams.height=lp.height;
            scrollView.setLayoutParams(lp);
            scrollLayout.setLayoutParams(layoutLayoutParams);
        }
        dialog.setContentView(rootView);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        rootView.setLayoutParams(new FrameLayout.LayoutParams(-1,-2));
    }

    public void dismiss(){
        dialog.dismiss();
    }

    public void show(){
        buildDialog();
        dialog.show();
    }

    public boolean isShowing(){
        return dialog.isShowing();
    }


    public void addItem(String text,int color){
        TextView textView=new TextView(context);
        textView.setText(text);
        textView.setTextColor(color);
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,Stdlib.dip2px(context,45));
        textView.setLayoutParams(lp);
        textView.setBackgroundResource(R.drawable.default_rectbutton);
        textView.setId(itemViewArray.size());
        textView.setOnClickListener(this);
        itemViewArray.add(textView);
        scrollLayout.addView(textView);
    }

    public void addItem(String text){
        //0xFF1679FC
        addItem(text, 0xFF037BFF);
    }

    public void clearAllItem(){
        itemViewArray.clear();
        scrollLayout.removeAllViews();
    }



}
