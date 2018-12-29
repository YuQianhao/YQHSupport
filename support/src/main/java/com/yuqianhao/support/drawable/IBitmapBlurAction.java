package com.yuqianhao.support.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;

public interface IBitmapBlurAction {
    Bitmap blur(Context context,
                Bitmap bitmap,
                @FloatRange(from = 0,to = 1) float bitmap_scale,
                @IntRange(from = 0, to = 25) int blur_radius);
}
