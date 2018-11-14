package com.yuqianhao.support.http;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * 发送请求的参数包装接口，当调用{@link IHttpRequestAction}发送请求时需要的接口，
 * 该接口包装了请求的地址，请求的参数，请求的Header的值，其中{@link AbsHttpFormRequestBody}和{@link AbsHttpJsonRequestBody}
 * 封装了这个接口，通常你只需要创建一个这两个包装类的实例传给IHttpRequestAction即可，除非上传的参数既不是Form也不是Json才需要
 * 自己实现这个接口。
 * */
public interface IHttpRequestBody {
    /**
     * 获取请求地址
     * @return 请求地址
     * */
    String getRequestURL();
    /**
     * 获取请求的参数
     * @return 请求参数
     * */
    RequestBody getRequestBody();
    /**
     * 修改请求的Header的值
     * @param headers 请求Header值的Map
     * */
    void makeRequestHeaders(Map<String,String> headers);
}
