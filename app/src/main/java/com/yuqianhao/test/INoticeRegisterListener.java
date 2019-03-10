package com.yuqianhao.test;

import java.util.List;

public interface INoticeRegisterListener {
    //首次注册
    void onResult(List<Notice> notices);
    //有红点被read的时候,通知回调的方法
    void onRemoveNopticeCallback(List<Notice> notices);
}
