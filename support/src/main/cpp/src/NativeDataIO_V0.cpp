//
// Created by YuQianhao on 2018/6/4.
//
#include "../include/com_yuqianhao_support_io_NativeDataIO_V0.h"
#include <cstdio>
#include <string>
#include <iostream>
#include<sys/file.h>

using namespace std;

static void releaseStringV0(JNIEnv*,const char*,jstring);

jint Java_com_yuqianhao_support_io_NativeDataIO_1V0_writeV0
        (JNIEnv *env, jclass, jstring jpath, jstring jdata){
    const char* path=env->GetStringUTFChars(jpath,NULL);
    const char* data=env->GetStringUTFChars(jdata,NULL);
    int length=env->GetStringLength(jdata);
    FILE* file=::fopen(path,"wb");
    if(file==nullptr){
        releaseStringV0(env,path,jpath);
        releaseStringV0(env,data,jdata);
        return -1;
    }
    flockfile(file);
    int v0result=::fwrite(data,sizeof(char),length,file);
    ::fflush(file);
    ::fclose(file);
    releaseStringV0(env,path,jpath);
    releaseStringV0(env,data,jdata);
    return v0result;
}


jstring Java_com_yuqianhao_support_io_NativeDataIO_1V0_readV0
        (JNIEnv *env, jclass, jstring jpath){
    const char* path=env->GetStringUTFChars(jpath,NULL);
    FILE* file=::fopen(path,"rb");
    if(file==nullptr){
        releaseStringV0(env,path,jpath);
        return nullptr;
    }
    flockfile(file);
    ::fseek(file,0,SEEK_END);
    int size=::ftell(file);
    ::rewind(file);
    char* buffer=new char[size+1]{0};
    ::fread(buffer,sizeof(char),size,file);
    ::fclose(file);
    jstring str=env->NewStringUTF(buffer);
    delete[] buffer;
    releaseStringV0(env,path,jpath);
    return str;
}

void releaseStringV0(JNIEnv* env,const char* cstr,jstring jstr){
    env->ReleaseStringUTFChars(jstr,cstr);
}
