package com.yuqianhao.support.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 申请权限成功之后根据不同的注解调用不同的方法执行，被修饰的方法必须是无参数的
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PermissionsCallback {
    /**
     * 权限申请的标签
     * */
    int permissionsTarget();
}
