package com.yuqianhao.support.redpoint;

/**
 *小红点的实体类
 * */
public class RedPointBean {
    private int red_id;//红点的id
    private String red_desc;//红点描述
    private int red_type;//红点类型
    private int red_read;//是否已读(0未读,1已读)

    public RedPointBean(int red_id, String red_desc, int red_type, int red_read) {
        this.red_id = red_id;
        this.red_desc = red_desc;
        this.red_type = red_type;
        this.red_read = red_read;
    }

    public int getRed_id() {
        return red_id;
    }

    public void setRed_id(int red_id) {
        this.red_id = red_id;
    }

    public String getRed_desc() {
        return red_desc;
    }

    public void setRed_desc(String red_desc) {
        this.red_desc = red_desc;
    }

    public int getRed_type() {
        return red_type;
    }

    public void setRed_type(int red_type) {
        this.red_type = red_type;
    }

    public int getRed_read() {
        return red_read;
    }

    public void setRed_read(int red_read) {
        this.red_read = red_read;
    }
}
