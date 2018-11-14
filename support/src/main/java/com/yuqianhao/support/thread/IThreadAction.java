package com.yuqianhao.support.thread;

/**
 * 多线程接口
 * */
public interface IThreadAction {
    /**
     * 将执行器投放到子线程中异步执行
     * @param runnable 需要投放到子线程中的执行器
     * */
    void run(Runnable runnable);
    /**
     * 将执行器投放到主线程中使用
     * @param runnable 需要投放到子线程中的执行器
     * */
    void runUI(Runnable runnable);

    /**
     * 同时执行多个子线程，并且在寄主线程等待所有子线程执行完毕
     * @param runnables 变参，需要并列执行的子线程执行器
     * */
    void waitThreadList(Runnable ...runnables) throws InterruptedException;
}
