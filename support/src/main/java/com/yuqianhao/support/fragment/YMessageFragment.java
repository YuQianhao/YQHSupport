package com.yuqianhao.support.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yuqianhao.support.redpoint.IRedPointCallback;
import com.yuqianhao.support.redpoint.RedPointBean;
import com.yuqianhao.support.redpoint.RedPointManager;
import com.yuqianhao.support.redpoint.RedPointView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YMessageFragment extends Fragment implements IRedPointCallback {

    private Map<Integer, List<View>> viewMap = new HashMap<>();

    @Override
    public void onStart() {
        super.onStart();
        collectionRedPointView();
        RedPointManager.getInstance().register(this);
    }


    @Override
    public void onResultRedPoint(List<RedPointBean> redPointBeanList) {
        //从viewMap中循环出需要展示的view
        List<View> showViews = new ArrayList<>();//需要展示的小红点
        List<View> hindViews = new ArrayList<>();//需要隐藏的小红点
        for (RedPointBean redPointBean : redPointBeanList) {
            if (redPointBean.getRead() == 0) {//未读状态小红点
                List<View> views = viewMap.get(redPointBean.getType());
                if (views != null) {
                    showViews.addAll(views);
                }
            } else {//已读小红点
                List<View> views = viewMap.get(redPointBean.getType());
                if (views != null) {
                    hindViews.addAll(views);
                }
            }
        }
        ///!!!!必须先隐藏后展示,同一个view可能有多种状态会出问题
        // 隐藏
        for (View hindView : hindViews) {
            hindView.setVisibility(View.INVISIBLE);
        }
        //展示
        for (View showView : showViews) {
            showView.setVisibility(View.VISIBLE);
        }
    }

    //收集所有被注释小红点的控件
    public void collectionRedPointView() {
        Field[] fields = getClass().getDeclaredFields();//反射类的所有属性
        if (fields != null) {
            for (Field itemField : fields) {
                itemField.setAccessible(true);
                RedPointView annotation = itemField.getAnnotation(RedPointView.class);//获取每个属性的注解
                if (annotation != null) {
                    int[] typeViews = annotation.type();//获取属性被注解的值
                    try {
                        View view = (View) itemField.get(this);//反射类获取view
                        for (int type : typeViews) {//循环typeViews
                            if (viewMap.containsKey(type)) {//包括此类型的小红点,直接添加view
                                List<View> views = viewMap.get(type);
                                views.add(view);
                            } else {//添加一个新的类型
                                List<View> views = new ArrayList<>();
                                views.add(view);
                                viewMap.put(type, views);
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RedPointManager.getInstance().unRegister(this);
    }
}
