package com.yuqianhao.support.actuator;

/**
 * 执行器的结果包装类
 * */
public class ActuatorValue {
    private int id;
    private Object returnValue;

    public ActuatorValue(int id,Object returnValue){
        this.id=id;
        this.returnValue=returnValue;
    }

    public int getId(){
        return id;
    }

    public Object getValue() {
        return returnValue;
    }

    public <_Tx> _Tx castValue(){
        return (_Tx)returnValue;
    }
}
