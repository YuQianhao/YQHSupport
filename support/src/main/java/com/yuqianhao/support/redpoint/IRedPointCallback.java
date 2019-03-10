package com.yuqianhao.support.redpoint;

import java.util.List;

public interface IRedPointCallback {

    void onAddRedPoint(List<RedPointBean> redPointBeanList);//添加小红点

    void onDeleteRedPonit(List<RedPointBean> redPointBeanList);//删除小红点

}
