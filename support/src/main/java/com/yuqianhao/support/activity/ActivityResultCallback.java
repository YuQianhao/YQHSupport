package com.yuqianhao.support.activity;

import android.content.Intent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 调用 {@link android.app.Activity#startActivityForResult(Intent, int)}启动新的Activity，
 * 当新的Activity返回时，如果原始Activity中有使用这个注解修饰的方法则调用，相当于{@link android.app.Activity#onActivityResult(int, int, Intent)}
 * 被修饰的方法必须接受两个参数： int, Intent
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ActivityResultCallback {
    /**
     * 启动子Activity的启动码
     * */
    int activityCode();
}
