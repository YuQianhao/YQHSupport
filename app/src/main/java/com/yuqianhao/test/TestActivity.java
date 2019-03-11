package com.yuqianhao.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import com.yuqianhao.support.activity.YActivity;
import com.yuqianhao.support.activity.YMessageActivity;
import com.yuqianhao.support.redpoint.RedPointForAF;
import com.yuqianhao.support.redpoint.RedPointForView;
import com.yuqianhao.support.redpoint.RedPointManager;
import com.yuqianhao.support.redpoint.RedPointType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RedPointForAF(typeAF = {RedPointType.UserInfo, RedPointType.NoticeInfo, RedPointType.OrderUpdata})
public class TestActivity extends YMessageActivity {

    @RedPointForView(typeView = RedPointType.UserInfo)
    private TextView text_1;
    @RedPointForView(typeView = RedPointType.NoticeInfo)
    private TextView text_2;
    @RedPointForView(typeView = RedPointType.OrderUpdata)
    private TextView text_3;
    @RedPointForView(typeView = {RedPointType.UserInfo, RedPointType.NoticeInfo, RedPointType.OrderUpdata})
    private TextView text_4;
    @RedPointForView(typeView = {RedPointType.UserInfo, RedPointType.NoticeInfo})
    private TextView text_5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_1 = findViewById(R.id.text_1);
        text_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedPointManager.getInstance().readNotice(RedPointType.UserInfo);

            }
        });
        text_2 = findViewById(R.id.text_2);
        text_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedPointManager.getInstance().readNotice(RedPointType.NoticeInfo);
            }
        });
        text_3 = findViewById(R.id.text_3);
        text_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedPointManager.getInstance().readNotice(RedPointType.OrderUpdata);
            }
        });
        text_4 = findViewById(R.id.text_4);
        text_5 = findViewById(R.id.text_5);
    }


}
