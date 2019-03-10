package com.yuqianhao.support.redpoint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedPointForAF {

    int[] typeAF();//这个表示activity或者fragment接收小红的数组类型
}
