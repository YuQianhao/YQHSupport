package com.yuqianhao.support.log;

public class YLog {
    private YLog(){}
    private static final ILogAction LOG_ACTION=new YLogImpl_V0();

    public static void registerLogProcessor(ILogForwarding iLogForwarding){
        LOG_ACTION.registerLogForwarding(iLogForwarding);
    }

    public static void debug(Object o,String msg){
        LOG_ACTION.debug(o,msg);
    }
    public static void warn(Object o,String msg){
        LOG_ACTION.warn(o,msg);
    }
    public static void info(Object o,String msg){
        LOG_ACTION.info(o,msg);
    }
    public static void error(Object o,String msg){
        LOG_ACTION.error(o,msg);
    }
    public static void debug(Object o,String msg,Exception e){
        LOG_ACTION.debug(o,msg,e);
    }
    public static void warn(Object o,String msg,Exception e){
        LOG_ACTION.warn(o,msg,e);
    }
    public static void info(Object o,String msg,Exception e){
        LOG_ACTION.info(o,msg,e);
    }
    public static void error(Object o,String msg,Exception e){
        LOG_ACTION.error(o,msg,e);
    }
}
