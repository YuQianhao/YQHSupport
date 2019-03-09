package com.yuqianhao.support.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yuqianhao.support.activity.YActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NotceType(type = NoticeType.USERINFO)
public class TestActivity extends YActivity implements INoticeRegisterListener {

    //个人信息
    @ReadPoint(types = NoticeType.USERINFO)
    private TextView readNotice;

    private Map<Integer,List<View>> readPointViewMap=new HashMap<>();
    //            type  =>  红点控件列表

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeReadPointListMap();
        NoticeManager.getInstance().register(this);
    }

    @Override
    protected void onDestroy() {
        NoticeManager.getInstance().unRegister(this);
        super.onDestroy();
    }

    @Override
    public void onResult(List<Notice> notices) {
//        Log.e("onResult","未读消息:"+notices);
        List<View> viewList=new ArrayList<>();
        for(Notice notice:notices){
            List<View> views=readPointViewMap.get(notice.getType());
            if(views!=null){
                viewList.addAll(views);
            }
        }
        for(View view:viewList){
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRemoveNopticeCallback(List<Notice> notices) {
        List<View> viewList=new ArrayList<>();
        for(Notice notice:notices){
            List<View> views=readPointViewMap.get(notice.getType());
            if(views!=null){
                viewList.addAll(views);
            }
        }
        for(View view:viewList){
            view.setVisibility(View.INVISIBLE);
        }
    }

    private void makeReadPointListMap(){
        try{
            Field[] fields=getClass().getDeclaredFields();
            for(Field field:fields){
                ReadPoint readPoint=field.getAnnotation(ReadPoint.class);
                if(readPoint!=null){
                    View view= (View) field.get(TestActivity.this);
                    int[] typeArray=readPoint.types();
                    for(int type:typeArray){
                        if(readPointViewMap.containsKey(type)){
                            List<View> views=readPointViewMap.get(type);
                            views.add(view);
                        }else{
                            List<View> views=new ArrayList<>();
                            views.add(view);
                            readPointViewMap.put(type,views);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
