package com.yuqianhao.support.http;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class AbsHttpObjectResponce<_Tx> extends AbsHttpJsonRequestResponse{

    private static final Gson GSON_INSTANCE=new Gson();

    private final Type getType(){
        return ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    protected final void onUIResult(int code, JSONObject body, Exception e) {
        onUIResult(code,(_Tx) GSON_INSTANCE.fromJson(body.toString(),getType()),e);
    }

    @Override
    protected final void onResult(int code, JSONObject body, Exception e) {
        onResult(code,(_Tx) GSON_INSTANCE.fromJson(body.toString(),getType()),e);
    }

    protected void onUIResult(int code, _Tx object, Exception e){}
    protected void onResult(int code,_Tx object,Exception e){}

}
