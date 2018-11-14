package com.yuqianhao.support.actuator;

public abstract class AbsActuatorRunnable implements IActuatorRunnable{
    private int id;

    public AbsActuatorRunnable(int id){
        this.id=id;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public abstract Object exec();
}
