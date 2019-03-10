package com.yuqianhao.support.redpoint;

/**
 *
 * */
public class RedPointBean {
    private int red_id;//红点的id
    private String red_desc;//红点描述
    private int red_type;//红点类型
    private int isRead;//是否已读(0未读,1已读)

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

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}
