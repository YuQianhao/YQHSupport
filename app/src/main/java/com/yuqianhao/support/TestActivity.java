package com.yuqianhao.support;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.yuqianhao.support.activity.YActivity;
import com.yuqianhao.support.http.AbsHttpFormRequestBody;
import com.yuqianhao.support.http.AbsHttpJsonRequestResponse;
import com.yuqianhao.support.http.HttpRequestManager;
import com.yuqianhao.support.http.IHttpRequestAction;
import com.yuqianhao.support.io.BufferIOStreamManager;
import com.yuqianhao.support.io.IBufferIOStreamAction;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TestActivity extends YActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showSuccressNotifyMsg("Hello World!");
    }
}
