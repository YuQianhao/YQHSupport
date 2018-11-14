package com.yuqianhao.support.log;

public class YLogImpl_V0 implements ILogAction{
    private ILogForwarding logForwarding=null;
    private boolean canPrintConsole=true;
    private boolean canForwardingApplication=true;
    private String logcatFilterName="YLog";

    @Override
    public void registerLogForwarding(ILogForwarding iLogForwarding) {
        logForwarding=iLogForwarding;
        canPrintConsole=logForwarding.canPrintLog();
        canForwardingApplication=logForwarding.canForwarding();
        logcatFilterName=logForwarding.onLogCatFilterName();
    }

    @Override
    public void debug(Object o, String msg) {
        debug(o,msg,null);
    }

    @Override
    public void warn(Object o, String msg) {
        warn(o,msg,null);
    }

    @Override
    public void info(Object o, String msg) {
        info(o,msg,null);
    }

    @Override
    public void error(Object o, String msg) {
        error(o,msg,null);
    }

    @Override
    public void debug(Object o,String msg,Exception e) {
        if(canPrintConsole){
            android.util.Log.d("[ClassName]:"+o.getClass().getName()+",[Msg]:"+logcatFilterName,msg);
        }
        if(canForwardingApplication && logForwarding!=null){
            logForwarding.onLogProcessor(
                LogLevel.Debug.level,
                    o.getClass(),
                    msg,
                    e!=null?android.util.Log.getStackTraceString(e):""
            );
        }
    }

    @Override
    public void warn(Object o,String msg,Exception e) {
        if(canPrintConsole){
            android.util.Log.w("[ClassName]:"+o.getClass().getName()+",[Msg]:"+logcatFilterName,msg);
        }
        if(canForwardingApplication && logForwarding!=null){
            logForwarding.onLogProcessor(
                    LogLevel.Warning.level,
                    o.getClass(),
                    msg,
                    e!=null?android.util.Log.getStackTraceString(e):""
            );
        }
    }

    @Override
    public void info(Object o,String msg,Exception e) {
        if(canPrintConsole){
            android.util.Log.i("[ClassName]:"+o.getClass().getName()+",[Msg]:"+logcatFilterName,msg);
        }
        if(canForwardingApplication && logForwarding!=null){
            logForwarding.onLogProcessor(
                    LogLevel.Info.level,
                    o.getClass(),
                    msg,
                    e!=null?android.util.Log.getStackTraceString(e):""
            );
        }
    }

    @Override
    public void error(Object o,String msg,Exception e) {
        if(canPrintConsole){
            android.util.Log.e("[ClassName]:"+o.getClass().getName()+",[Msg]:"+logcatFilterName,msg);
        }
        if(canForwardingApplication && logForwarding!=null){
            logForwarding.onLogProcessor(
                    LogLevel.Error.level,
                    o.getClass(),
                    msg,
                    e!=null?android.util.Log.getStackTraceString(e):""
            );
        }
    }
}
