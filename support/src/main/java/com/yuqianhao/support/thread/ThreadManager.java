package com.yuqianhao.support.thread;

/**
 * 线程管理器，通过这个类可以获得多线程接口{@link IThreadAction}的实例
 * */
public class ThreadManager {
    private ThreadManager(){}

    private static final IThreadAction THREAD_ACTION=new ThreadActionImpl0_Queue();

    /**
     * 获取多线程接口
     * @return 多线程接口IThreadAction的实例
     * */
    public static final IThreadAction getThreadManagerInterface(){
        return THREAD_ACTION;
    }

}
