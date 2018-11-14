package com.yuqianhao.support.cache.V1;

import com.tencent.mmkv.MMKV;

import java.util.Set;

/**
 * 共享存储方案，内部使用了Tencent开源的MMKV实现，github：https://github.com/Tencent/MMKV
 * */
public class SharedStorgeImpl implements ISharedStorageInterface{

    private MMKV tencentMemoryKeyValue;

    public SharedStorgeImpl(boolean processShared,String name){
        tencentMemoryKeyValue=MMKV.mmkvWithID(name,processShared?MMKV.MULTI_PROCESS_MODE:MMKV.SINGLE_PROCESS_MODE);
    }

    @Override
    public void add(String key, boolean value) {
        tencentMemoryKeyValue.encode(key,value);
    }

    @Override
    public void add(String key, int value) {
        tencentMemoryKeyValue.encode(key,value);
    }

    @Override
    public void add(String key, float value) {
        tencentMemoryKeyValue.encode(key,value);
    }

    @Override
    public void add(String key, double value) {
        tencentMemoryKeyValue.encode(key,value);
    }

    @Override
    public void add(String key, long value) {
        tencentMemoryKeyValue.encode(key,value);
    }

    @Override
    public void add(String key, String value) {
        tencentMemoryKeyValue.encode(key,value);
    }

    @Override
    public void add(String key, byte[] bytes) {
        tencentMemoryKeyValue.encode(key,bytes);
    }

    @Override
    public void add(String key, Set<String> value) {
        tencentMemoryKeyValue.encode(key,value);
    }

    @Override
    public boolean getBoolean(String key) {
        return tencentMemoryKeyValue.decodeBool(key);
    }

    @Override
    public int getInt(String key) {
        return tencentMemoryKeyValue.decodeInt(key);
    }

    @Override
    public float getFloat(String key) {
        return tencentMemoryKeyValue.decodeFloat(key);
    }

    @Override
    public double getDouble(String key) {
        return tencentMemoryKeyValue.decodeDouble(key);
    }

    @Override
    public long getLong(String key) {
        return tencentMemoryKeyValue.decodeLong(key);
    }

    @Override
    public String getString(String key) {
        return tencentMemoryKeyValue.decodeString(key);
    }

    @Override
    public byte[] getByteArray(String key) {
        return tencentMemoryKeyValue.decodeBytes(key);
    }

    @Override
    public Set<String> getStringList(String key) {
        return tencentMemoryKeyValue.decodeStringSet(key);
    }

    @Override
    public boolean hasValue(String key) {
        return tencentMemoryKeyValue.containsKey(key);
    }

    @Override
    public void removeValue(String... key) {
        if(key.length==1){
            tencentMemoryKeyValue.removeValueForKey(key[0]);
        }else{
            tencentMemoryKeyValue.removeValuesForKeys(key);
        }
    }
}
