package com.yuqianhao.support.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class BitmapBlurManager {
    private BitmapBlurManager(){}

    private static final IBitmapBlurAction BITMAP_BLUR_ACTION=new BitmapBlurImpl();

    public static final IBitmapBlurAction getBitmapBlurAction(){
        return BITMAP_BLUR_ACTION;
    }

    /**
     * 高斯模糊，将一张Bitmap转换像素为高斯模糊
     * @param context
     * @param bitmap Bitmap源
     * @param scale 缩放的比例，高性能的模糊方案首先将图片缩小一定的比例，然后进行高斯模糊，最后将图片方法，取值范围 0.0-1.0
     * @param radius 模糊的值，值越大模糊越大，取值为0-25
     * */
    public static Bitmap blur(Context context,
                              Bitmap bitmap,
                              @FloatRange(from = 0,to = 1) float scale,
                              @IntRange(from = 0, to = 25) int radius){
        return BITMAP_BLUR_ACTION.blur(context,bitmap,scale,radius);
    }

    public static Bitmap blur(Context context,Bitmap bitmap){
        return BITMAP_BLUR_ACTION.blur(context,bitmap,0.9f,15);
    }


}
