package com.yuqianhao.support.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpRequestClientV0 implements IHttpRequestAction{

    private static final OkHttpClient OK_HTTP_CLIENT=new OkHttpClient.Builder()
            .cookieJar(new CookieJar() {

                private final List<Cookie> COOKIE_LIST=new ArrayList<>();

                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    COOKIE_LIST.addAll(cookies);
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    return COOKIE_LIST;
                }
            })
            .connectTimeout(10,TimeUnit.SECONDS)
            .readTimeout(25, TimeUnit.SECONDS)
            .build();

    @Override
    public void post(final IHttpRequestBody iHttpRequestBody, final IHttpRequestResponse iHttpRequestResponse) {
        Request.Builder requestBuilder=new Request.Builder()
                .url(iHttpRequestBody.getRequestURL())
                .post(iHttpRequestBody.getRequestBody());
        _makeRequestHeaderV0(iHttpRequestBody,requestBuilder);
        OK_HTTP_CLIENT.newCall(requestBuilder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                try {
                    iHttpRequestResponse.onResult(0,null,e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                iHttpRequestResponse.onResult(response.code(),response,null);
            }
        });
    }

    @Override
    public void get(IHttpRequestBody iHttpRequestBody, final IHttpRequestResponse iHttpRequestResponse) {
        Request.Builder builder=new Request.Builder();
        builder.url(iHttpRequestBody.getRequestURL());
        _makeRequestHeaderV0(iHttpRequestBody,builder);
        OK_HTTP_CLIENT.newCall(builder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                try {
                    iHttpRequestResponse.onResult(0,null,e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                iHttpRequestResponse.onResult(response.code(),response,null);
            }
        });
    }

    private void _makeRequestHeaderV0(IHttpRequestBody iHttpRequestBody,Request.Builder builder){
        Map<String,String> headerMap=new HashMap<>();
        iHttpRequestBody.makeRequestHeaders(headerMap);
        for(Map.Entry<String,String> item : headerMap.entrySet()){
            builder.addHeader(item.getKey(),item.getValue());
        }
    }


}
