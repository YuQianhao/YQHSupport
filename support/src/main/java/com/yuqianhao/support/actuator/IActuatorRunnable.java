package com.yuqianhao.support.actuator;

import java.util.List;

/**
 * 执行事件接口，通常你不需要实现这个接口，而是在需要这个接口的位置使用{@link AbsActuatorRunnable}即可
 * */
public interface IActuatorRunnable {
    /**
     * 执行的eid
     * */
    int id();
    /**
     * 将要执行的代码写在这个方法中，其中返回值回最终通过{@link IActuatorCompleteListener#complete(List)}这个方法返回
     * */
    Object exec();
}
