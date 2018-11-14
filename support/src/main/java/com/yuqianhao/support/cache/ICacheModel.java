package com.yuqianhao.support.cache;

public interface ICacheModel {
    /**
     * 反序列化，当本地缓存被加载的时候调用的方法
     * @param obj 缓存时序列化成的对象，即onSerialization方法的返回值
     * */
    void onDeserialization(String obj);
    /**
     * 序列化对象，当缓存被调用的时候调用该方法获取要缓存的数据
     * @return 返回要存数的序列化的对象
     * */
    String onSerialization();
}
