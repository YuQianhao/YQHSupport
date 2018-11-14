package com.yuqianhao.support.log;

public enum LogLevel {
    Info(0,"info"),Debug(1,"debug"),Warning(2,"warning"),Error(3,"error");
    public int level;
    public String string;
    LogLevel(int loglevel,String levelString){
        this.level=loglevel;
        this.string=levelString;
    }
}
