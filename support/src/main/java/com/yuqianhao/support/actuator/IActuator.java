package com.yuqianhao.support.actuator;

/**
 * 执行器接口
 * {@link LinearActuator}线性执行器
 * */
public interface IActuator {
    /**
     * 想执行器队列中添加一个执行事件
     * @param runnable 执行事件
     * */
    void pushBack(IActuatorRunnable runnable);
    /**
     * 获取执行队列的数量
     * */
    int size();
    /**
     * 启动队列
     * */
    void start() throws InterruptedException;
    /**
     * 判断队列是否正在执行
     * */
    boolean canRunning();
    /**
     * 注册队列执行完毕获取所有执行事件返回值的回调接口
     * @param completeListener 回调接口
     * */
    void setCompleteListener(IActuatorCompleteListener completeListener);
}
