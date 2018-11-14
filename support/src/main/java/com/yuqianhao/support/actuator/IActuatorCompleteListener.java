package com.yuqianhao.support.actuator;

import java.util.List;

/**
 * 执行器执行完毕调用的接口
 * */
public interface IActuatorCompleteListener {
    /**
     * 当执行器执行队列中的所有事件执行完毕则回将所有的结果传给这个方法
     * @param actuatorValues 执行器的返回结果集，可以使用id区分
     * */
    void complete(List<ActuatorValue> actuatorValues);
}
