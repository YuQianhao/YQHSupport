package com.yuqianhao.support.http;

import java.io.IOException;

import okhttp3.Response;

/**
 * 请求结束后请求结果将会传给这个接口的实例，该接口被调用的时候会在<B>子线程<B/>中被调用，
 * 如果没有特殊需要无需自己实现这个接口，通常应该使用{@link AbsHttpStringRequestResponse}
 * 等实现了该接口的类，这个类处理了子线程和主线程以及分割了返回结果
 * {@link AbsHttpStringRequestResponse}
 * {@link AbsHttpJsonRequestResponse}
 * */
public interface IHttpRequestResponse {
    /**
     * 请求结果回调方法
     * @param requestCode 请求返回码
     * @param response 请求结果，当请求失败的时候这个值为null
     * @param e 请求出错的时候这个Exception会被赋值，请求成功的时候为null
     * */
    void onResult(int requestCode, Response response ,Exception e) throws IOException;
}
