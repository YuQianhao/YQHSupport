package com.yuqianhao.support.io;

import java.io.File;
import java.io.IOException;

/**
 * 输入输出流方法接口，通过这个接口的实例可以读取或者写出数据到本地文件
 * */
public interface IBufferIOStreamAction {
    /**
     * <B>同步<B/>读取文件中的内容
     * @param filePath 要读取文件的路径
     * @return 文件中的内容
     * */
    String readFile(String filePath) throws IOException;
    /**
     * <B>同步<B/>读取文件中的内容
     * @param file 要读取文件
     * @return 文件中的内容
     * */
    String readFile(File file) throws IOException;
    /**
     * <B>同步<B/>写出数据到文件
     * @param filePath 要写出的文件的路径
     * @param data 要写出的数据
     * @return 写出是否成功
     * */
    boolean writeFile(String filePath,byte[] data) throws IOException;
    /**
     * <B>同步<B/>写出数据到文件
     * @param filePath 要写出的文件的路径
     * @param data 要写出的数据
     * @return 写出是否成功
     * */
    boolean writeFile(String filePath,String data) throws IOException;
    /**
     * <B>同步<B/>写出数据到文件
     * @param file 要写出的文件
     * @param data 要写出的数据
     * @return 写出是否成功
     * */
    boolean writeFile(File file,byte[] data) throws IOException;
    /**
     * <B>同步<B/>写出数据到文件
     * @param file 要写出的文件
     * @param data 要写出的数据
     * @return 写出是否成功
     * */
    boolean writeFile(File file,String data) throws IOException;
}
