package com.yuqianhao.support.cache;

import com.yuqianhao.support.thread.IThreadAction;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class _CacheWriterV0 {
    private Map<String,String> keyMap;
    private IThreadAction thread;
    private String applicationPath;
    private static final String FILANAME="odc";

    public _CacheWriterV0(Map<String,String> map,IThreadAction thread,String path){
        keyMap=map;
        this.thread=thread;
        applicationPath=path;
        File file=new File(makeAbsPath());
        if(!file.exists() || !file.isDirectory()){
            file.mkdirs();
        }
    }

    private String makeFileName(String key){
        return "."+key;
    }

    private String makeAbsPath(){
        return applicationPath+"/"+FILANAME;
    }

    public void initKeyValue(){
        File[] fileList=new File(makeAbsPath()).listFiles();
        for(File file:fileList){
            String key=file.getName().substring(1);
            String value=null;
            try {
                BufferedInputStream bufferedInputStream=
                        new BufferedInputStream(
                                new FileInputStream(makeAbsPath()+"/"+
                                    makeFileName(key)));
                byte[] bytes=new byte[(int) file.length()];
                bufferedInputStream.read(bytes);
                bufferedInputStream.close();
                value=new String(bytes,"UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            keyMap.put(key,value);
        }
    }

    public void write(final String key,final String value){
        thread.run(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedOutputStream bufferedOutputStream=
                            new BufferedOutputStream(
                                    new FileOutputStream(
                                            makeAbsPath()+"/"+
                                                    makeFileName(key)));
                    bufferedOutputStream.write(value.getBytes("UTF-8"));
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void delete(final String key){
        thread.run(new Runnable() {
            @Override
            public void run() {
                File file=new File(makeAbsPath()+"/"+
                        makeFileName(key));
                if(file.exists()){
                    file.delete();
                }
            }
        });
    }

}
