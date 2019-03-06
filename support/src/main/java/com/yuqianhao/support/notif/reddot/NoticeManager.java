package com.yuqianhao.support.notif.reddot;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeManager implements INoticeInterface{

    /**
     * 消息索引
     * */
    private static final Map<Integer,List<Notice>> NOTICE_MAP=new HashMap<>();
    /**
     * 注册的消息接收器
     * */
    private static final Map<Integer,List<INoticeNotifyListener>> NOTICELISTENER_MAP=new HashMap<>();

    private NoticeManager(){}

    private static final NoticeManager INSTANCE=new NoticeManager();

    public static final INoticeInterface getNoticeInstance(){
        return INSTANCE;
    }


    @Override
    public void read(int... type) {

    }

    @Override
    public void unRead(int... type) {

    }


    @Override
    public void register(INoticeNotifyListener noticeNotifyListener) {

    }

    @Override
    public void unReigster(INoticeNotifyListener noticeNotifyListener) {

    }
}
