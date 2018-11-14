package com.yuqianhao.support.log;

/**
 * Log日志的操作接口
 * */
public interface ILogAction {
    /**
     * 注册日志处理器
     * @param iLogForwarding 要被注册的Log处理器
     * */
    void registerLogForwarding(ILogForwarding iLogForwarding);
    void debug(Object o, String msg);
    void warn(Object o, String msg);
    void info(Object o, String msg);
    void error(Object o, String msg);
    void debug(Object o, String msg, Exception e);
    void warn(Object o, String msg, Exception e);
    void info(Object o, String msg, Exception e);
    void error(Object o, String msg, Exception e);
}
