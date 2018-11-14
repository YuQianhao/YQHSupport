package com.yuqianhao.support.http;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

/**
 * 将请求结果封装成Json来处理的类，实现了{@link IHttpRequestResponse}接口，
 * 可以在任何需要{@link IHttpRequestResponse}接口的地方使用这个类的实例。
 * 重载onUIResult或者onResult这两个方法中的任意一个即可处理返回结果，其中
 * onUIResult方法在UI线程中被调用，子线程和主线程调用顺序如下：
 *    {ProcessCode} -> onResult -> onUIResult
 * 即先调用子线程在调用主线程
 * */
public class AbsHttpJsonRequestResponse extends AbsHttpResultResponce implements IHttpRequestResponse {

    @Override
    public final void onResult(final int requestCode, Response response, final Exception e) throws IOException {
        if(e!=null){
            onResult(requestCode, (JSONObject) null,e);
            UI_THREAD_HANDLER.post(new Runnable() {
                @Override
                public void run() {
                    onUIResult(requestCode,null,e);
                }
            });
        }else{
            final JSONObject jsonObject=makeJsonObject(response.body().string());
            onResult(requestCode,jsonObject,null);
            UI_THREAD_HANDLER.post(new Runnable() {
                @Override
                public void run() {
                    onUIResult(requestCode,jsonObject,null);
                }
            });
        }
    }

    /**
     * 这个方法会在主线程中被调用
     * @param code 请求返回的请求码
     * @param body 请求返回值，如果请求失败了这个值为null
     * @param e 如果请求发生异常这个值不为null
     * */
    protected void onUIResult(int code, JSONObject body, Exception e){}

    /**
     * 这个方法会在子线程
     * @param code 请求返回的请求码
     * @param body 请求返回值，如果请求失败了这个值为null
     * @param e 如果请求发生异常这个值不为null
     * */
    protected void onResult(int code,JSONObject body,Exception e){}

}
