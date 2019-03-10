package com.yuqianhao.test;

public class Notice {
    private int id;
    private String msg;
    private int type;
    private boolean read;

    public Notice(int id, String msg, int type, boolean read) {
        this.id = id;
        this.msg = msg;
        this.type = type;
        this.read = read;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Notice){
            return ((Notice)obj).id==id;
        }else{
            return false;
        }
    }
}
