package com.yuqianhao.support.service.image;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class YImageLoader implements IImageLoader{
    private static final RequestOptions REQUEST_OPTIONS_CACHE=new RequestOptions().
            diskCacheStrategy(DiskCacheStrategy.ALL).
            skipMemoryCache(true);

    private static final RequestOptions REQUEST_OPTIONS=new RequestOptions().
            diskCacheStrategy(DiskCacheStrategy.NONE).
            skipMemoryCache(true);

    private static final YImageLoader INSTANCE=new YImageLoader();

    private YImageLoader(){}

    public static final YImageLoader getInstance(){
        return INSTANCE;
    }

    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context.getApplicationContext()).load(url).apply(REQUEST_OPTIONS).into(imageView);
    }

    @Override
    public void loadImageCache(Context context, String url, ImageView imageView) {
        Glide.with(context.getApplicationContext()).load(url).apply(REQUEST_OPTIONS_CACHE).into(imageView);
    }

    @Override
    public void loadImage(Context context, Uri uri, ImageView imageView) {
        Glide.with(context.getApplicationContext()).load(uri).apply(REQUEST_OPTIONS).into(imageView);
    }

    @Override
    public void loadImageCache(Context context, Uri uri, ImageView imageView) {
        Glide.with(context.getApplicationContext()).load(uri).apply(REQUEST_OPTIONS_CACHE).into(imageView);
    }


}
