package com.yuqianhao.support.service.image;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

public interface IImageLoader {
    void loadImage(Context context, String url, ImageView imageView);
    void loadImageCache(Context context, String url, ImageView imageView);
    void loadImage(Context context, Uri uri, ImageView imageView);
    void loadImageCache(Context context, Uri uri, ImageView imageView);
}
