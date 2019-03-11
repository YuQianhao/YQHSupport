package com.yuqianhao.support.http;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;
import java.util.Map;

public class AbsHttpObjectRequestBody<_Tx> extends RequestParamsBuilder implements IHttpRequestBody{

    private static final Gson GSON_INSTANCE=new Gson();

    @Override
    public String getRequestURL() {
        return null;
    }

    @Override
    public final RequestBody getRequestBody() {
        _Tx obj=getRequestObjectBody();
        final String data=GSON_INSTANCE.toJson(obj,((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        RequestBody requestBody=new RequestBody() {
            @Override
            public MediaType contentType() {
                return MediaType.parse("content-Type:application/json;charset=utf-8");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeString(data,Charset.forName("UTF-8"));
            }
        };
        return requestBody;
    }

    @Override
    public void makeRequestHeaders(Map<String, String> headers) {

    }

    protected _Tx getRequestObjectBody(){
        return null;
    }
}
