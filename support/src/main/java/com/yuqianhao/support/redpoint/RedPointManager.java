package com.yuqianhao.support.redpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * 小红点的管理类
 */
public class RedPointManager {

    private RedPointManager() {
    }

    private static RedPointManager INSTANCE = new RedPointManager();

    public static final RedPointManager getInstance() {
        return INSTANCE;
    }


    private static List<RedPointBean> redPointList = new ArrayList<>();//已存有数据的集合
    private List<IRedPointCallback> iRedPointCallbackList = new ArrayList<>();

    /**
     * 将通知消息对象插入到队列中
     *
     * @param redPointBean 变参，可以有多个对象
     */
    public void putNotice(RedPointBean... redPointBean) {
        for (RedPointBean redPointBean1 : redPointBean) {
            redPointList.add(redPointBean1);
        }
    }

    /**
     * 将消息列表插入到消息队列中
     *
     * @param list 消息列表
     */
    public void putNotice(List<RedPointBean> list) {
        if (list != null) {
            redPointList.addAll(list);
        }
    }

    //点击小红点view
    public void readNotice(int type) {
        for (RedPointBean redPointBean : redPointList) {
            if (type == redPointBean.getType()) {
                redPointBean.setRead(1);//将未读的小红点设置为已读
            }
        }
        for (IRedPointCallback iRedPointCallback : iRedPointCallbackList) {
            iRedPointCallback.onResultRedPoint(redPointList);//通知界面刷新数据
        }
    }

    //A或者F注册绑定系小红点事件,并且获取需要显示的小红点的类型
    public void register(IRedPointCallback iRedPointCallback) {
        RedPointAnnotations annotation = iRedPointCallback.getClass().getAnnotation(RedPointAnnotations.class);//通过反射获取A或者F的注解
        if (annotation != null) {
            iRedPointCallbackList.add(iRedPointCallback);//将被注释的A或者F存进集合中
        }
        if (redPointList != null && redPointList.size() > 0) {
            iRedPointCallback.onResultRedPoint(redPointList);//通知界面,小红点的数据
        }
    }

    //A或者F解绑系解绑系小红点时间
    public void unRegister(IRedPointCallback iRedPointCallback) {
        if (iRedPointCallback != null) {
            iRedPointCallbackList.remove(iRedPointCallback);
        }

    }


}
