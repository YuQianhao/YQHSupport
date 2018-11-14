package com.yuqianhao.support.cache.V1;

/**
 * 共享存储策略管理器，可以通过这个管理器的静态方法获取到共享存储策略接口的实例
 * 可以通过下面两个静态方法获取默认的进程共享或者不共享的接口实例：
 * {@link #getDefaultInterface()}获                          取一个默认的不进程共享的存储策略类
 * {@link #getDefaultProcessStorageInterface()}             获取一个默认的进程共享策略类
 * 也可以通过下面两个静态方法获取指定名称的进程共享或者不共享的存储实例
 * {@link #getSharedStorageInterface(String)}               获取一个指定名称的不共享内存的实例
 * {@link #getProcessSharedStorageInterface(String)}        获取一个指定名称的共享内存的实例
 *
 * */
public class SharedStorgeManager {
    private SharedStorgeManager(){}

    private static final ISharedStorageInterface I_SHARED_STORAGE_INTERFACE=new SharedStorgeImpl(false,"yqh_support_default");
    private static final ISharedStorageInterface I_PROCESS_SHARED_STORAGE_INTERFACE=new SharedStorgeImpl(true,"yqh_support_processDefault");

    /**
     * 获取默认的不共享内存的存储策略借口
     * @return {@link ISharedStorageInterface}
     * */
    public static final ISharedStorageInterface getDefaultInterface(){
        return I_SHARED_STORAGE_INTERFACE;
    }

    /**
     * 获取默认的共享内存的存储策略借口
     * @return {@link ISharedStorageInterface}
     * */
    public static final ISharedStorageInterface getDefaultProcessStorageInterface(){
        return I_PROCESS_SHARED_STORAGE_INTERFACE;
    }

    /**
     * 获取指定名称的不共享内存的存储策略借口
     * @return {@link ISharedStorageInterface}
     * */
    public static final ISharedStorageInterface getSharedStorageInterface(String name){
        return new SharedStorgeImpl(false,name);
    }

    /**
     * 获取指定名称的共享内存的存储策略借口
     * @return {@link ISharedStorageInterface}
     * */
    public static final ISharedStorageInterface getProcessSharedStorageInterface(String name){
        return new SharedStorgeImpl(true,name);
    }


}
