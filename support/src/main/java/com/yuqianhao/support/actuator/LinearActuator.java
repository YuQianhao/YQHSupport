package com.yuqianhao.support.actuator;

import com.yuqianhao.support.thread.IThreadAction;
import com.yuqianhao.support.thread.ThreadManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 线性执行的执行器，这个执行器执行队列会放在子线程中执行，但是等待执行队列完成在父线程中执行，所有如果
 * 在Activity或者其他拥有UI主线程的位置使用最好在子线程中使用这个类，值得注意的是{@link IActuatorCompleteListener#complete(List)}
 * 这个方法依靠这个类的线程，也就是说如果这个类在子线程中执行，那这个方法也会在子线程中执行。
 * <B>注意：需要调用{@link #setCompleteListener(IActuatorCompleteListener)}这个方法注册执行监听器，否则执行完毕不会有回调<B/>
 * */
public class LinearActuator implements IActuator{
    private Map<Integer,Object> actuatorValueMap;
    private ArrayList<IActuatorRunnable> actuatorRunnableArrayList;
    private CountDownLatch countDownLatch;
    private boolean canRunning;
    private IThreadAction threadAction;
    private IActuatorCompleteListener actuatorComplete;

    public LinearActuator(){
        actuatorValueMap=new HashMap<>();
        actuatorRunnableArrayList=new ArrayList<>();
        threadAction= ThreadManager.getThreadManagerInterface();
    }

    public LinearActuator(IActuatorCompleteListener iActuatorComplete){
        this();
        actuatorComplete=iActuatorComplete;
    }

    @Override
    public void pushBack(IActuatorRunnable actuatorRunnable){
        if(!canRunning && !actuatorValueMap.containsKey(actuatorRunnable.id())){
            actuatorRunnableArrayList.add(actuatorRunnable);
        }
    }

    @Override
    public int size(){
        return actuatorRunnableArrayList.size();
    }

    @Override
    public void start() throws InterruptedException {
        if(!canRunning){
            canRunning=true;
            countDownLatch=new CountDownLatch(actuatorRunnableArrayList.size());
            for(final IActuatorRunnable actuatorRunnable:actuatorRunnableArrayList){
                threadAction.run(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (actuatorValueMap){
                            actuatorValueMap.put(actuatorRunnable.id(),actuatorRunnable.exec());
                        }
                        countDownLatch.countDown();
                    }
                });
            }
            countDownLatch.await();
            canRunning=false;
            if(actuatorComplete==null){return;}
            ArrayList<ActuatorValue> actuatorValues=new ArrayList<>();
            for(Map.Entry<Integer,Object> mapValue:actuatorValueMap.entrySet()){
                actuatorValues.add(new ActuatorValue(mapValue.getKey(),mapValue.getValue()));
            }
            actuatorComplete.complete(actuatorValues);
        }
    }

    @Override
    public boolean canRunning() {
        return canRunning;
    }

    @Override
    public void setCompleteListener(IActuatorCompleteListener completeListener) {
        this.actuatorComplete=completeListener;
    }

}
