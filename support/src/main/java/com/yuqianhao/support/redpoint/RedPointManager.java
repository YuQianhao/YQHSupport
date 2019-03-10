package com.yuqianhao.support.redpoint;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class RedPointManager {

    private RedPointManager() {
    }

    private RedPointManager INSTANCE = new RedPointManager();

    public RedPointManager getINSTANCE() {
        return INSTANCE;
    }

    private List<RedPointBean> redPointList = new ArrayList<>();//已存有数据的集合
    private List<IRedPointCallback> iRedPointCallbackList = new ArrayList<>();


    //A或者F注册系小红点事件,并且获取需要显示的小红点的类型
    public void register(IRedPointCallback iRedPointCallback) {
        RedPointForAF annotation = iRedPointCallback.getClass().getAnnotation(RedPointForAF.class);//通过反射获取A或者F的注解
        if (annotation != null) {
            iRedPointCallbackList.add(iRedPointCallback);//将被注释的A或者F存进集合中
            int[] typeAF = annotation.typeAF();
            List<RedPointBean> noticeList = new ArrayList<>();
            for (int item_af : typeAF) {
                for (RedPointBean redPointBean : redPointList) {
                    if (redPointBean.getRed_type() == item_af) {//筛选redPointList中在这个A或者F中需要展示的小红点的类型
                        noticeList.add(redPointBean);
                    }
                }
            }
        }
    }




}
