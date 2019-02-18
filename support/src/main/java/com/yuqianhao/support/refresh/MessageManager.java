package com.yuqianhao.support.refresh;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    public static class Message{
        public int type;
        public Object data;
    }

    private MessageManager(){}

    private static final MessageManager INSTANCE=new MessageManager();

    private List<IMessageInterface> messageInterfaceList=new ArrayList<>();

    public static final MessageManager getInstance(){
        return INSTANCE;
    }

    public void add(IMessageInterface messageInterface){
        messageInterfaceList.add(messageInterface);
    }

    public void remove(IMessageInterface messageInterface){
        messageInterfaceList.remove(messageInterface);
    }

    public void clear(){
        messageInterfaceList.clear();
    }

    public void sendMessage(Message message){
        for(IMessageInterface messageInterface:messageInterfaceList){
            Annotation annotation=messageInterface.getClass().getAnnotation(RegisterMessage.class);
            if(annotation!=null){
                int[] typeArray=((RegisterMessage) annotation).msgType();
                for(int type:typeArray){
                    if(type==message.type){
                        messageInterface.onMessage(message.type,message.data);
                    }
                }
            }
        }
    }

    public void sendMessage(Message ...msgArray){
        for(Message msg:msgArray){
            sendMessage(msg);
        }
    }

    public void sendMessage(int type){
        sendMessage(type,null);
    }

    public void sendMessage(int type,Object data){
        Message message=new Message();
        message.data=null;
        message.type=type;
        sendMessage(message);
    }


}
