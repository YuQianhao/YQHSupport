package com.yuqianhao.support.thread;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/** @author 于千皓  2018-10-15  我故意不写注释的 O(∩_∩)O */
public class ThreadActionImpl0_Queue implements IThreadAction{

    private static final Handler UI_THREAD_HANDLER=new Handler(Looper.getMainLooper());

    private static final Executor EXECUTOR=new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors()*2,
            1,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>()
    );

    @Override
    public void run(Runnable runnable) {
        EXECUTOR.execute(runnable);
    }

    @Override
    public void runUI(Runnable runnable) {
        UI_THREAD_HANDLER.post(runnable);
    }

    @Override
    public void waitThreadList(Runnable... runnables) throws InterruptedException {
        final CountDownLatch countDownLatch=new CountDownLatch(runnables.length);
        for(final Runnable runnable:runnables){
            run(new Runnable() {
                @Override
                public void run() {
                    runnable.run();
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
    }
}
