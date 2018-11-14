package com.yuqianhao.support.notif;

public interface INotifyService {
    /**
     * 显示一个提示的View
     * @param msg 要显示的内容
     * @param backgroundColor 提示框的背景颜色
     * @param textColor 提示框的文字颜色
     * @param stabarBright 当前状态栏的背景颜色
     * @param stabarColor 当前状态栏是否为亮色
     * */
    void showMessageView(String msg,
                         int backgroundColor,
                         boolean setStabarBright,
                         int textColor,
                         int stabarColor,
                         boolean stabarBright);


}
