package com.yuqianhao.support.cache;

import com.yuqianhao.support.cache.V2.MMKV_CacheWriteImpl;

public class CacheHelpManager {
    private CacheHelpManager(){}

    private static ICacheAction<String> cacheAction;

    public static final ICacheAction<String> bindCacheHelperService(){
        if(cacheAction==null){
            cacheAction=new MMKV_CacheWriteImpl();
        }
        return cacheAction;
    }


}
