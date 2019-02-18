package com.yuqianhao.support.refresh;

/**
 * 消息接口接口，实现了该接口可以使用{@link RegisterMessage}注解来注册要接收的消息类型，
 * 当使用{@link MessageManager}来发送消息的时候会调用每一个注册了对应消息ID的类的{@link #onMessage(int, Object)}
 * 方法。
 * */
public interface IMessageInterface {
    /**
     * @param msgID 消息类型
     * @param data 消息附加数据，可能为null
     * */
    void onMessage(int msgID,Object data);
}
