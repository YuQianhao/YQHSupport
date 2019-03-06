package com.yuqianhao.support.notif.reddot;

public interface INoticeInterface {
    /**
     * 将未读通知更改状态为已读
     * */
    void read(int ...type);
    /**
     * 将已读通知更改状态为未读
     * */
    void unRead(int ...type);
    /**
     * 注册通知状态改变器，首次注册的时候系统将会回调给页面和页面有关的未读通知
     * */
    void register(INoticeNotifyListener noticeNotifyListener);
    /**
     * 取消注册通知状态改变器
     * */
    void unReigster(INoticeNotifyListener noticeNotifyListener);
}
