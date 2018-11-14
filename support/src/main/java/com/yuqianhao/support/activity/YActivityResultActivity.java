package com.yuqianhao.support.activity;

import android.content.Intent;
import android.support.annotation.CallSuper;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 能够将子Activity的返回值和上一个Activity的方法绑定的类，相当于在{@link android.app.Activity#onActivityResult(int, int, Intent)}
 * 中处理每一个子Activity的返回值，其中处理的方法需要使用{@link ActivityResultCallback}修饰
 *
 * */
public class YActivityResultActivity extends YImageLoadActivity{
    private final Map<Integer,Method> __$ACTIVITY_RESULT_MAP=new HashMap<>();

    public YActivityResultActivity(){
        super();
        initActivityResultMap();
    }

    private final void initActivityResultMap(){
        Method[] methods=getClass().getDeclaredMethods();
        for(Method method:methods){
            Annotation[] annotations=method.getDeclaredAnnotations();
            for(Annotation annotation:annotations){
                if(annotation instanceof ActivityResultCallback){
                    ActivityResultCallback activityResultCallback=(ActivityResultCallback) annotation;
                    method.setAccessible(true);
                    __$ACTIVITY_RESULT_MAP.put(activityResultCallback.activityCode(),method);
                }
            }
        }
    }

    @CallSuper
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Method method=__$ACTIVITY_RESULT_MAP.get(requestCode);
        if(method!=null){
            try {
                method.invoke(this,resultCode,data);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
