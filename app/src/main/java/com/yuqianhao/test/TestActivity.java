package com.yuqianhao.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class TestActivity extends YBaseActivity {

    @PresenterAutoCreate(params = TestActivity.class)
    private TestModule testModule;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(){
        Toast.makeText(this,"Hello World!",Toast.LENGTH_SHORT).show();
    }


}

class TestModule{
    public TestModule(TestActivity testActivity){
        testActivity.showToast();
    }
}
