package com.yuqianhao.support.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferIOStreamImplV0 implements IBufferIOStreamAction{

    @Override
    public String readFile(String filePath) throws IOException {
        return readFile(new File(filePath));
    }

    @Override
    public String readFile(File file) throws IOException {
        int length= (int) file.length();
        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
        char[] bufferset=new char[length];
        bufferedReader.read(bufferset,0,length);
        bufferedReader.close();
        String str=new String(bufferset);
        return str;
    }

    @Override
    public boolean writeFile(String filePath, byte[] data) throws IOException {
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(filePath));
        bufferedOutputStream.write(data,0,data.length);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return true;
    }

    @Override
    public boolean writeFile(String filePath, String data) throws IOException {
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(filePath));
        bufferedWriter.write(data,0,data.length());
        bufferedWriter.flush();
        bufferedWriter.close();
        return true;
    }

    @Override
    public boolean writeFile(File file, byte[] data) throws IOException {
        return writeFile(file.getAbsolutePath(),data);
    }

    @Override
    public boolean writeFile(File file, String data) throws IOException {
        return writeFile(file.getAbsolutePath(),data);
    }
}
