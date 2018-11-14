package com.yuqianhao.support.cache;

import android.content.Context;

public class CacheHelpManager {
    private CacheHelpManager(){}

    private static ICacheAction<String> cacheAction;

    public static final ICacheAction<String> bindCacheHelperService(Context context){
        if(cacheAction==null){
            cacheAction=new CacheInterfaceImplV0(context);
        }
        return cacheAction;
    }


}
