package com.yuqianhao.support.http;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * 请求参数接口的实现类，这个类包装了请求参数为Form类型的请求体。
 * */
public class AbsHttpFormRequestBody extends RequestParamsBuilder implements IHttpRequestBody{
    private Map<String,Object> requestBody;

    public AbsHttpFormRequestBody(){
        requestBody=new HashMap<>();
    }

    @Override
    public String getRequestURL() {
        return null;
    }

    @Override
    public final RequestBody getRequestBody() {
        makeRequestParameter(requestBody);
        FormBody.Builder formBodyBuilder=new FormBody.Builder();
        for(Map.Entry<String,Object> iter:requestBody.entrySet()){
            formBodyBuilder.add(iter.getKey(),iter.getValue().toString());
        }
        return formBodyBuilder.build();
    }

    @Override
    public final void makeRequestHeaders(Map<String, String> headers) {
        makeRequestHeader(headers);
    }

    /**
     * 请求参数的创建方法，重载这个方法，向参数parmaterMap添加请求的值
     * @param parmaterMap 当请求被调用的时候，这个map里的键值对会转换成Form请求表单
     * */
    protected void makeRequestParameter(Map<String,Object> parmaterMap){}
    /**
     * 请求Header的创建方法，重载这个方法，向参数requestHeaders添加自定义的Hreader
     * @param requestHeaders 当请求调用的时候，这个map里的值会被添加到请求Headers里一起上传
     * */
    protected void makeRequestHeader(Map<String,String> requestHeaders){}


}
