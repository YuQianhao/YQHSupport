package com.yuqianhao.test;

import com.yuqianhao.test.NotceType;

import java.util.ArrayList;
import java.util.List;

public class NoticeManager {
    private NoticeManager(){}

    public static final NoticeManager INSTANCE=new NoticeManager();

    public static final NoticeManager getInstance(){
        return INSTANCE;
    }

    //---------------------------------------------------

    private static final List<Notice> NOTICE_LIST=new ArrayList<>();


    private static final List<INoticeRegisterListener> NOTICE_REGISTER_LISTENERS=new ArrayList<>();

    static{
        NOTICE_LIST.add(new Notice(1,"", NoticeType.USERINFO,false));
        NOTICE_LIST.add(new Notice(2,"",NoticeType.USERINFO,false));
        NOTICE_LIST.add(new Notice(3,"",NoticeType.ORDER,false));
        NOTICE_LIST.add(new Notice(4,"",NoticeType.USERINFO,false));
        NOTICE_LIST.add(new Notice(5,"",NoticeType.ORDER,false));
    }

    public List<Notice> getNoticeList(){
        return NOTICE_LIST;
    }

    public void register(INoticeRegisterListener noticeRegisterListener){
        NotceType notceType=noticeRegisterListener.getClass().getAnnotation(NotceType.class);
        if(notceType!=null){
            NOTICE_REGISTER_LISTENERS.add(noticeRegisterListener);
            int[] typeArray=notceType.type();
            List<Notice> noticeList=new ArrayList<>();
            for(Notice notice:NOTICE_LIST){
                for(int type:typeArray){
                    if(notice.getType()==type){
                        noticeList.add(notice);
                    }
                }
            }
            noticeRegisterListener.onResult(noticeList);
        }
    }

    public void unRegister(INoticeRegisterListener noticeRegisterListener){
        NOTICE_REGISTER_LISTENERS.remove(noticeRegisterListener);
    }

    public void read(int ...types){
        List<Notice> removeNotice=new ArrayList<>();
        for(int type:types){
            for (Notice notice : NOTICE_LIST) {
                if(notice.getType()==type){
                    notice.setRead(true);
                    removeNotice.add(notice);
                    NOTICE_LIST.remove(notice);
                }
            }
        }
        for(INoticeRegisterListener noticeRegisterListener:NOTICE_REGISTER_LISTENERS){
            noticeRegisterListener.onRemoveNopticeCallback(removeNotice);
        }
    }

}
