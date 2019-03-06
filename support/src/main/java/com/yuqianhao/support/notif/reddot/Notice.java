package com.yuqianhao.support.notif.reddot;

import com.yuqianhao.support.activity.YBaseActivity;

public class Notice {
    private int id;
    private String msg;
    private boolean read;
    private int type;

    public Notice(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static final Notice create(int id,String msg,boolean read,int type){
        Notice notice=new Notice();
        notice.id=id;
        notice.msg=msg;
        notice.read=read;
        notice.type=type;
        return notice;
    }

}
