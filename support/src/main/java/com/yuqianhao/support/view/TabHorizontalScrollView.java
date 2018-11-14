package com.yuqianhao.support.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2017/6/8.
 */

public class TabHorizontalScrollView extends HorizontalScrollView {
    public static final String TAG="com.yuqianhao.view.TabHorizontalScrollView";

    private int mWidth=0;

    private static int mWindowWidth=0;

    private OnScrollChanged mOnScrollChanged=new OnScrollChanged() {
        @Override
        public void onScrollChanged(int scrollWidth) {

        }
    };

    public void setScrollChangedListener(OnScrollChanged onScrollChanged){
        mOnScrollChanged=onScrollChanged;
    }

    /**
     * 获取屏幕宽度
     * */
    public int getWindowWidth(){
        return mWindowWidth;
    }

    private void initWindowSize(){
        WindowManager manager= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics mDisplayMetrics=new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        mWindowWidth=mDisplayMetrics.widthPixels;
    }

    public interface OnScrollChanged{
        void onScrollChanged(/**送开手指时滑动的偏移量*/int scrollWidth);
    }

    public TabHorizontalScrollView(Context context) {
        super(context);
        initWindowSize();
    }

    public TabHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWindowSize();
    }

    public TabHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWindowSize();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TabHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initWindowSize();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        mWidth=l;
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_UP){
            mOnScrollChanged.onScrollChanged(mWidth);
        }
        return super.onTouchEvent(ev);
    }
}
