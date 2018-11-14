package com.yuqianhao.support.activity;

import android.net.Uri;
import android.widget.ImageView;

import com.yuqianhao.support.dialog.BasisDialog;
import com.yuqianhao.support.dialog.SelectDialog;
import com.yuqianhao.support.http.IHttpRequestBody;
import com.yuqianhao.support.http.IHttpRequestResponse;

import java.io.IOException;

public interface IYActivityInterface {
    /**
     * 加载图片到ImageView
     * @param url 图片地址路径
     * @param imageView 要被加载的ImageView
     * */
    void loadBitmapToImageView(String url, ImageView imageView);
    /**
     * 加载图片到ImageView
     * @param uri 图片uri路径
     * @param imageView 要被加载的ImageView
     * */
    void loadBitmapToImageView(Uri uri,ImageView imageView);
    /**
     * 启动一个子线程
     * @param runnable 代码执行器
     * */
    void startThread(Runnable runnable);
    /**
     * 启动一个UI线程
     * @param runnable 代码执行器
     * */
    void startUIThread(Runnable runnable);
    /**
     * 向进程缓存中添加数据
     * @param key 缓存数据的KEY
     * @param value 缓存数据的Value
     * */
    void cachePut(String key,String value);
    /**
     * 从进程缓存中读取数据
     * @param key 缓存数据的Key
     * @return 获取到的缓存内容，如果没有缓存则为null
     * */
    String cacheGet(String key);
    /**
     * 修改进程缓存中的缓存数据
     * @param key 要修改的数据的Key
     * @param value 要修改的值
     * @return 如果修改成功则返回原始的值，否则返回null
     * */
    String cacheSet(String key,String value);
    /**
     * 移除进程缓存数据中的值
     * @param key 值的Key
     * @return 如果进程缓存中有这个值则返回删除了的值，否则返回null
     * */
    String cacheRemove(String key);
    /**
     * 创建一个基础对话框，详见{@link BasisDialog}
     * @return 创建好的对话框
     * */
    BasisDialog createBasisDialog();
    /**
     * 创建一个基础对话框，并设置标题，详见{@link BasisDialog}
     * @param title 对话框的标题
     * @return 创建好的对话框
     * */
    BasisDialog createBasisDialog(String title);
    /**
     * 创建一个基础对话框，并设置标题和消息，详见{@link BasisDialog}
     * @param title 对话框的标题
     * @param message 对话框的消息内容
     * @return 创建好的对话框
     * */
    BasisDialog createBasisDialog(String title,String message);
    /**
     * 展示一个基础对话框并设置消息，通常用作给用户一个简单的提示，这个对话框会带有
     * 一个Ok按钮，即关闭按钮，详见{@link BasisDialog}
     * @param message 要显示的消息
     * */
    void showBasisMessageDialog(String message);
    /**
     * 创建一个选择对话框，同时设置对话框的标题和内容，详见{@link SelectDialog}
     * @param title 对话框标题
     * @param item 变参，对话框的值列表
     * @return 返回创建好的对话框
     * */
    SelectDialog createSelectDialog(String title,String ...item);
    /**
     * 创建一个post请求，详见{@link com.yuqianhao.support.http.IHttpRequestAction}
     * @param requestBody 请求参数
     * @param RequestResponse 请求结果
     * */
    void post(IHttpRequestBody requestBody, IHttpRequestResponse RequestResponse);
    /**
     * 创建一个get请求，详见{@link com.yuqianhao.support.http.IHttpRequestAction}
     * @param requestBody 请求参数
     * @param RequestResponse 请求结果
     * */
    void get(IHttpRequestBody requestBody,IHttpRequestResponse RequestResponse);
    /**
     * 将数据写出到文件
     * @param path 要写出的文件路径
     * @param data 要写出的数据
     * */
    void writeData(String path,String data) throws IOException;
    /**
     * 从文件中读取数据
     * @param path 文件的路径
     * @return  读取到的数据
     * */
    String readData(String path) throws IOException;
    void logDebug(String msg);
    void logWarn(String msg);
    void logInfo(String msg);
    void logError(String msg);
    void logDebug(String msg, Exception e);
    void logWarn(String msg, Exception e);
    void logInfo(String msg, Exception e);
    void logError(String msg, Exception e);
    void showToast(String msg);
    void showSuccressNotifyMsg(String msg);
    void showErrorNotifyMsg(String msg);
    void setStateBarColorBright(int color);
    void setStateBarColorDark(int color);
    void showProgressDialog(String message);
    void setProgressDialogText(String message);
    void closeProgressDialog();
}
