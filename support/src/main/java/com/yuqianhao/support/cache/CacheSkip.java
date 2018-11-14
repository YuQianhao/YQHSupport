package com.yuqianhao.support.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 当自动缓存开始调用时如果有i一个成员变量被
 * 使用CacheSkip修饰时将自动跳过
 * */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheSkip {
}
