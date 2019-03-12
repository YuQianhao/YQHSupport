package com.yuqianhao.support.redpoint;

/**
 *小红点的实体类
 * */
public class RedPointBean {
    private int id;//红点的id
    private String desc;//红点描述
    private int type;//红点类型
    private int read;//是否已读(0未读,1已读)

    public RedPointBean(int id, String desc, int type, int read) {
        this.id = id;
        this.desc = desc;
        this.type = type;
        this.read = read;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }
}
