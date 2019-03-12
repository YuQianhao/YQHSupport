package com.yuqianhao.support.redpoint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解activity或者fragment
 *
 * type():activity或者fragment注解需要展示的小红点的类型
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedPointAnnotations {
    int[] type();//这个表示activity或者fragment接收小红的数组类型
}
