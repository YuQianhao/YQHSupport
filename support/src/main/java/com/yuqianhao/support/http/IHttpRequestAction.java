package com.yuqianhao.support.http;

/**
 * 发送请求接口
 * */
public interface IHttpRequestAction {
    /**
     * 发送一个post请求
     * @param iHttpRequestBody 请求参数
     * @param iHttpRequestResponse 请求返回值
     * */
    void post(IHttpRequestBody iHttpRequestBody,IHttpRequestResponse iHttpRequestResponse);
    /**
     * 发送一个get请求
     * @param iHttpRequestBody 请求参数，<B>当请求为Get的时候，IHttpRequestBody的getRequestBody不会被调用，需要将
     *                         请求参数合并到请求的URL中<B/>
     * @param iHttpRequestResponse 请求返回值
     * */
    void get(IHttpRequestBody iHttpRequestBody,IHttpRequestResponse iHttpRequestResponse);
}
