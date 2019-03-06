package com.yuqianhao.support.notif.reddot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注册红点通知的注解，可以使用这个注解注释在Activity或者Fragment的类声明中，例如：
 *
 * @RegisterNotice(typeArray={0},typeView={R.id.view})
 * public class MainActivity extends YActivity{}
 *
 * 当这个Activity开始调用onCreate方法开始其生命周期
 *
 *
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RegisterNotice {
    int[] typeArray();
    int[] typeView();
}
