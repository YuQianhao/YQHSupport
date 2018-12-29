package com.yuqianhao.support.http;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 请求参数接口的实现类，这个类包装了请求参数为Json类型的请求体。
 * */
public class AbsHttpJsonRequestBody extends RequestParamsBuilder implements IHttpRequestBody{
    private JSONObject jsonObject;

    public AbsHttpJsonRequestBody(){
        jsonObject=new JSONObject();
    }

    @Override
    public String getRequestURL() {
        return null;
    }

    @Override
    public final RequestBody getRequestBody() {
        makeRequestParameter(jsonObject);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
    }

    @Override
    public final void makeRequestHeaders(Map<String, String> headers) {
        makeRequestHeaders(headers);
    }

    /**
     * 请求参数的创建方法，重载这个方法，向参数jsonObject添加请求的值
     * @param jsonObject 当请求被调用的时候，这个JsonObject会被当作参数上传
     * */
    protected void makeRequestParameter(JSONObject jsonObject){}
    /**
     * 请求Header的创建方法，重载这个方法，向参数requestHeaders添加自定义的Hreader
     * @param requestHeaders 当请求调用的时候，这个map里的值会被添加到请求Headers里一起上传
     * */
    protected void makeRequestHeader(Map<String,String> requestHeaders){}
}
