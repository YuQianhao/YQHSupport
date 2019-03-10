package com.yuqianhao.test;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//小红点Type
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotceType {

    //表示,这个Fragment或者Activitry需要接收的小红点的类型数组
    int[] type();
}
