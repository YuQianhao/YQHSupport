package com.yuqianhao.support.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/6/8.
 */

public class TabLinearLayout extends LinearLayout implements View.OnClickListener{
    public static final String TAG="com.yuqianhao.view.TabLinearLayout";

    private SparseArray<View> mViewLists=new SparseArray<>();

    public interface OnScrollSelect{
        void onScrollSelect(int index);
    }

    private OnScrollSelect mOnScrollSelect=new OnScrollSelect() {
        @Override
        public void onScrollSelect(int index) {

        }
    };

    public void setOnScrollSelect(OnScrollSelect onScrollSelect){
        mOnScrollSelect=onScrollSelect;
    }

    public TabLinearLayout(Context context) {
        super(context);
    }

    public TabLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TabLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TabLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void addView(View child) {
        child.setTag(new Integer(mViewLists.size()));
        child.setOnClickListener(this);
        mViewLists.append(mViewLists.size(),child);
        super.addView(child);
    }



    @Override
    public void onClick(View v) {
        mOnScrollSelect.onScrollSelect(((Integer) v.getTag()).intValue());
    }
}
