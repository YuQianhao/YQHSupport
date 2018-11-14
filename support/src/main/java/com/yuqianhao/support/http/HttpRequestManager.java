package com.yuqianhao.support.http;

/**
 * 网络请求方法的管理类，通过这个类的getHttpRequestService方法可以获取到{@link IHttpRequestAction}的实例
 * */
public class HttpRequestManager {
    private HttpRequestManager(){}

    private static final IHttpRequestAction HTTP_REQUEST_ACTION=new HttpRequestClientV0();

    /**
     * 获取网络请求接口IHttpRequestAction的实例
     * */
    public static final IHttpRequestAction getHttpRequestInterface(){
        return HTTP_REQUEST_ACTION;
    }

}
