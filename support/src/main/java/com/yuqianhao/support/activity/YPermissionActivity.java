package com.yuqianhao.support.activity;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.yuqianhao.support.permissions.PermissionsCallback;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现了注入式的权限申请，可以使用方法{@link #checkCallPermission(int, String[])}来调用一个需要权限的方法，详见
 * 方法内容。
 * */
public class YPermissionActivity extends YBaseActivity {
    private final Map<Integer,Method> __$PERMISSION_CALLBACK_METHOD_MAP=new HashMap<>();

    public YPermissionActivity(){
        super();
        initPermissionMap();
    }

    private final void initPermissionMap(){
        Method[] methods=getClass().getDeclaredMethods();
        for(Method method:methods){
            Annotation[] annotations=method.getDeclaredAnnotations();
            for(Annotation annotation:annotations){
                if(annotation instanceof PermissionsCallback){
                    PermissionsCallback permissionsCallback= (PermissionsCallback) annotation;
                    method.setAccessible(true);
                    __$PERMISSION_CALLBACK_METHOD_MAP.put(permissionsCallback.permissionsTarget(),method);
                }
            }
        }
    }

    /**
     * 检查并调用需要使用权限的方法
     * @param callBackMethodID 需要地爱用的方法ID，该方法必须使用 {@link PermissionsCallback} 修饰
     * @param permissionArray 需要的权限列表
     * */
    protected void checkCallPermission(int callBackMethodID,String[] permissionArray){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissionArray,callBackMethodID);
            }else{
                callPermissionFunction(callBackMethodID);
            }
        }else{
            callPermissionFunction(callBackMethodID);
        }
    }

    /**
     * 调用 {@link #callPermissionFunction(int)} 失败的回调方法，这个方法被地爱用的唯一条件是权限被拒绝
     * @param callBackMethodID 需要地爱用的方法ID，该方法必须使用 {@link PermissionsCallback} 修饰
     * @param permissionArray 被拒绝的权限数组
     * */
    protected void onCheckCallPermissionFailure(int callBackMethodID,String[] permissionArray){

    }

    @CallSuper
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        ArrayList<String> failurePermissionList=new ArrayList<>();
        for(int i=0;i<grantResults.length;i++){
            if(grantResults[i]!= PackageManager.PERMISSION_GRANTED){
                failurePermissionList.add(permissions[i]);
            }
        }
        if(failurePermissionList.size()==0){
            callPermissionFunction(requestCode);
        }else{
            String[] failurePermissionArray=new String[failurePermissionList.size()];
            failurePermissionList.toArray(failurePermissionArray);
            onCheckCallPermissionFailure(requestCode,failurePermissionArray);
        }
    }

    private final void callPermissionFunction(int id){
        try {
            Method method=__$PERMISSION_CALLBACK_METHOD_MAP.get(id);
            if(method!=null){
                method.invoke(this);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
