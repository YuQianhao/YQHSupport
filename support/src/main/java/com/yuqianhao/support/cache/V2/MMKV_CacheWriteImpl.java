package com.yuqianhao.support.cache.V2;

import com.yuqianhao.support.cache.ICacheAction;
import com.yuqianhao.support.cache.V1.ISharedStorageInterface;
import com.yuqianhao.support.cache.V1.SharedStorgeManager;

public class MMKV_CacheWriteImpl implements ICacheAction<String> {

    private static final ISharedStorageInterface SHARED_STORAGE_INTERFACE=SharedStorgeManager.getSharedStorageInterface("mmkv_default_yqhsupport_v2");

    public MMKV_CacheWriteImpl(){

    }

    @Override
    public String get(String key) {
        return SHARED_STORAGE_INTERFACE.getString(key);
    }

    @Override
    public boolean put(String key, String obj) {
        SHARED_STORAGE_INTERFACE.add(key,obj);
        return true;
    }

    @Override
    public String set(String key, String obj) {
        if(SHARED_STORAGE_INTERFACE.hasValue(key)){
            String oldValue=SHARED_STORAGE_INTERFACE.getString(key);
            SHARED_STORAGE_INTERFACE.removeValue(key);
            SHARED_STORAGE_INTERFACE.add(key,obj);
            return oldValue;
        }
        return null;
    }

    @Override
    public String remove(String key) {
        if(SHARED_STORAGE_INTERFACE.hasValue(key)){
            String value=SHARED_STORAGE_INTERFACE.getString(key);
            SHARED_STORAGE_INTERFACE.removeValue(key);
            return value;
        }
        return null;
    }
}
