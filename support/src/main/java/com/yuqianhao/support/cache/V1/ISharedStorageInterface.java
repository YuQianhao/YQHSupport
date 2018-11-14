package com.yuqianhao.support.cache.V1;

import java.util.Set;

public interface ISharedStorageInterface {
    void add(String key,boolean value);
    void add(String key,int value);
    void add(String key,float value);
    void add(String key,double value);
    void add(String key,long value);
    void add(String key,String value);
    void add(String key,byte[] bytes);
    void add(String key, Set<String> value);
    boolean getBoolean(String key);
    int getInt(String key);
    float getFloat(String key);
    double getDouble(String key);
    long getLong(String key);
    String getString(String key);
    byte[] getByteArray(String key);
    Set<String> getStringList(String key);
    boolean hasValue(String key);
    void removeValue(String ...key);
}
