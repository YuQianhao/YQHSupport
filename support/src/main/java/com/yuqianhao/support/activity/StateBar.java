package com.yuqianhao.support.activity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StateBar {
    /**
     * 状态栏的颜色
     * */
    int color() default 0xffffffff;
    /**
     * 状态栏字体是否为深色
     * */
    boolean fontDark() default true;
}
