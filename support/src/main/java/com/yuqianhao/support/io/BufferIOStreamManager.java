package com.yuqianhao.support.io;

/**
 * 本地文件IO流的管理类，通过这个类的方法getBufferIoStreamInterface可以获取到{@link IBufferIOStreamAction}接口的实例
 * */
public class BufferIOStreamManager {
    private BufferIOStreamManager(){}

    private static final IBufferIOStreamAction BUFFER_IO_STREAM_ACTION=new BufferIOStreamImplV0();

    /**
     * 获取{@link IBufferIOStreamAction}接口的实例
     * @return 返回IBufferIOStreamAction接口的实例
     * */
    public static final IBufferIOStreamAction getBufferIoStreamInterface(){
        return BUFFER_IO_STREAM_ACTION;
    }
}
