package com.yuqianhao.support.cache;

import android.content.Context;

import com.yuqianhao.support.thread.IThreadAction;
import com.yuqianhao.support.thread.ThreadManager;

import java.util.HashMap;
import java.util.Map;

public class CacheInterfaceImplV0 implements ICacheAction<String>{

    private Map<String,String> keyMap=new HashMap<>();
    private IThreadAction thread= ThreadManager.getThreadManagerInterface();
    private _CacheWriterV0 cacheWriterV0;

    public CacheInterfaceImplV0(Context context){
        cacheWriterV0=new _CacheWriterV0(keyMap,thread,"/data/data/"+context.getPackageName());
        cacheWriterV0.initKeyValue();
    }

    @Override
    public String get(String key) {
        return keyMap.get(key);
    }

    @Override
    public boolean put(String key, String obj) {
        cacheWriterV0.write(key,obj);
        if(keyMap.containsKey(key)){
            keyMap.remove(key);
        }
        keyMap.put(key,obj);
        return true;
    }

    public boolean put(String key, ICacheModel obj) {
        return put(key,obj.onSerialization());
    }

    @Override
    public String set(String key, String obj) {
        String result=null;
        if(keyMap.containsKey(key)){
            result=keyMap.remove(key);
        }
        cacheWriterV0.write(key,obj);
        keyMap.put(key,obj);
        return result;
    }

    public String set(String key,ICacheModel obj){
        return set(key,obj.onSerialization());
    }

    @Override
    public String remove(String key) {
        cacheWriterV0.delete(key);
        return keyMap.remove(key);
    }


}
