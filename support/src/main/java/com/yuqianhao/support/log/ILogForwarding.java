package com.yuqianhao.support.log;

/**
 * Log处理接口，实现该接口后使用YLog打印日志的时候同时会自动调用接口下面的方法，
 * 提供给Application一次处理日志的机会。
 * */
public interface ILogForwarding {
    /**
     * 日志处理器，所有使用TLo打印的日志打印完毕后都会最终传到这个方法交给Application处理
     * @param level Log级别，详见{@link LogLevel}
     * @param javaClassName 产生日志的类名称
     * @param logMsg 日志的内容
     * @param statckMsg 如果调用的YLog的时候同时传递了Exception的时候，Exception的内容会传递给这个参数
     * */
    void onLogProcessor(int level, Class javaClassName, String logMsg, String statckMsg);
    /**
     * 设置日志过滤名称，调用YLog的时候打印在LogCat上的过滤名称
     * @return 日志过滤名称
     * */
    String onLogCatFilterName();
    /**
     * 是否需要打印在Logcat中
     * @return 返回是否打印在Logcat中
     * */
    boolean canPrintLog();
    /**
     * 是否将YLog信息传递给onLogProcessor处理
     * @return
     * */
    boolean canForwarding();
}
