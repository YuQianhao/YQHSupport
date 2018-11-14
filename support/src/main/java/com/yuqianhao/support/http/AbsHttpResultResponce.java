package com.yuqianhao.support.http;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 请求返回结果的包装类公共父类，这个类封装了一些需要在每一个处理请求结果的
 * 类中常用的方法。
 * */
public class AbsHttpResultResponce {
    protected static final Handler UI_THREAD_HANDLER=new Handler(Looper.getMainLooper());

    protected final JSONObject makeJsonObject(String str){
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
