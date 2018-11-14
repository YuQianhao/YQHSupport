package com.yuqianhao.support.cache;

public interface ICacheAction<_Tx>{
    _Tx get(String key);
    boolean put(String key, _Tx obj);
    _Tx set(String key, _Tx obj);
    _Tx remove(String key);
}
