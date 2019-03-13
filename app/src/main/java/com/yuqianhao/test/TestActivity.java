package com.yuqianhao.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.yuqianhao.support.activity.YMessageActivity;

public class TestActivity extends YMessageActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
