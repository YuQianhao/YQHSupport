package com.yuqianhao.support.cache;

import android.content.Context;

import com.yuqianhao.support.cache.V2.MMKV_CacheWriteImpl;

public class CacheHelpManager {
    private CacheHelpManager(){}

    private static ICacheAction<String> cacheAction;
    private static ICacheAction<String> oldCacheAction;

    public static final ICacheAction<String> bindCacheHelperServiceV0(Context context){
        if(oldCacheAction==null){
            oldCacheAction=new CacheInterfaceImplV0(context);
        }
        return oldCacheAction;
    }

    public static final ICacheAction<String> bindCacheHelperService(){
        if(cacheAction==null){
            cacheAction=new MMKV_CacheWriteImpl();
        }
        return cacheAction;
    }


}
