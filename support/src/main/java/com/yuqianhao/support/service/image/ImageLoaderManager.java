package com.yuqianhao.support.service.image;

public class ImageLoaderManager {


    private ImageLoaderManager(){}

    private static final IImageLoader I_IMAGE_LOADER=YImageLoader.getInstance();

    public static final IImageLoader bindImageLoaderService(){
        return I_IMAGE_LOADER;
    }

}
