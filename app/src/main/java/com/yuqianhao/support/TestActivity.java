package com.yuqianhao.support;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.shengfeng.operations.model.oid.QualType;
import com.yuqianhao.support.activity.YActivity;
import com.yuqianhao.support.http.AbsHttpFormRequestBody;
import com.yuqianhao.support.http.AbsHttpObjectResponce;

import java.util.Map;

public class TestActivity extends YActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startThread(new Runnable() {
            @Override
            public void run() {
                post(new AbsHttpFormRequestBody(){
                    @Override
                    public String getRequestURL() {
                        return "http://www.oilgourd.cn/qualType/selectList.json";
                    }

                    @Override
                    protected void makeRequestParameter(Map<String, Object> parmaterMap) {
                        parmaterMap.put("type","1");
                    }
                },new AbsHttpObjectResponce<QualType>(){
                    @Override
                    protected void onUIResult(int code, QualType object, Exception e) {
                        object.getId();
                    }
                });
            }
        });
    }

}
