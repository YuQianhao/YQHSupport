package com.yuqianhao.support.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.CallSuper;
import android.support.v4.content.FileProvider;

import com.yuqianhao.support.utils.UriUtils;

import java.io.File;

/**
 * 实现了便捷加载班底图片或者从相机获取照片的Activity，详见{@link #loadBitmapForCamera(File)}和{@link #loadBitmapForStorage()}
 * ，其中重写{@link #onImageLoadResult(Uri, String)}将会获得调用上买那两个获取图片的方法的结果值。
 * */
public class YImageLoadActivity extends YPermissionActivity {

    private Uri $cameraResultURI=null;
    private static final int $REQUEST_CAMERA=1001;
    private static final int $REQUEST_STORAGE=2002;

    /**
     * 从相机获取一张图片，结果将通过方法 {@link #onImageLoadResult(Uri, String)} 返回，重写该方法即可得到路径
     * @param saveFile 要临时保存相机拍出来的照片的文件，要求该文件必须存在
     * */
    protected void loadBitmapForCamera(File saveFile){
        $cameraResultURI= FileProvider.getUriForFile(this,
                getPackageName() + ".provider",saveFile);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,$cameraResultURI);
        startActivityForResult(intent,$REQUEST_CAMERA);
    }

    /**
     * 想本地文件中获取一张图片，结果将通过方法 {@link #onImageLoadResult(Uri, String)} 返回，重写该方法即可得到路径
     * */
    protected void loadBitmapForStorage(){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, $REQUEST_STORAGE);
    }

    private final void onImageLoadResult(Uri uri){
        onImageLoadResult(uri, UriUtils.getPath(this,uri));
    }

    /**
     * 获取到的图片的路径
     * @param imageUri 图片的Uri路径
     * @param imagePath 图片的String路径
     * */
    protected void onImageLoadResult(Uri imageUri,String imagePath){}


    @CallSuper
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case $REQUEST_CAMERA:{
                if(resultCode== RESULT_OK && $cameraResultURI!=null){
                    onImageLoadResult($cameraResultURI);
                }
            }break;
            case $REQUEST_STORAGE:{
                if(resultCode==RESULT_OK && data.getData()!=null){
                    onImageLoadResult(data.getData());
                }
            }break;
        }
    }

}
