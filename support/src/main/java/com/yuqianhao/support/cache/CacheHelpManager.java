package com.yuqianhao.support.cache;

import android.content.Context;

public class CacheHelpManager {
    private CacheHelpManager(){}

    private static ICacheAction<String> cacheAction;
    private static CacheInterfaceImplV0 V0INSTANCE;

    public static final ICacheAction<String> bindCacheHelperService(Context context){
        if(cacheAction==null){
            cacheAction=new CacheInterfaceImplV0(context);
        }
        return cacheAction;
    }


    public static final CacheInterfaceImplV0 bindCacheHelperServiceV0(Context context){
        if(V0INSTANCE==null){
            V0INSTANCE=new CacheInterfaceImplV0(context);
        }
        return V0INSTANCE;
    }

}
