package com.yuqianhao.support.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    private FileUtils(){}

    /**
     * 创建路径上的目录
     * @param path 路径绝对地址，如果这个路径不存在则创建这个路径上所有的目录
     * */
    public static final void mkdir(String path){
        File file=new File(path);
        if(!file.exists() || !file.isDirectory()){
            file.mkdirs();
        }
    }

    /**
     * 检查一个目录是否存在，如果不存在则创建这个目录
     * @param path 目录的绝对路径
     * */
    public static final void checkDirectory(String path){
        mkdir(path);
    }

    /**
     * 删除文件
     * @param path 要删除的文件名称，如果这个文件存在则删除
     * */
    public static final void removeFile(String path){
        File file=new File(path);
        if(file.exists()){
            file.delete();
        }
    }

    /**
     * 删除目录以及目录下所有的文件
     * @param path 目录的路径，如果这个目录存在则删除这个目录
     * */
    public static final void removeDirectory(String path){
        File file=new File(path);
        if(file.isDirectory()){
            File[] files=file.listFiles();
            for(File tmp:files){
                removeDirectory(tmp.getAbsolutePath());
            }
        }else{
            file.delete();
        }
    }

    /**
     * 创建文件
     * @param path 文件的绝对路径，如果这个文件不存在则创建
     * */
    public static final void createFile(String path) throws IOException {
        File file=new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
    }

}
