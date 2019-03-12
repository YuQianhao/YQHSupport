package com.yuqianhao.support.redpoint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 注解view
 *
 * type():界面里的view需要展示小红点的类型
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedPointView {

    int[] type();//这个表示控件view需要接收小红点的类型
}
