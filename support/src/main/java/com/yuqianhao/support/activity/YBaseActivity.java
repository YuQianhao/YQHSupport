package com.yuqianhao.support.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.yuqianhao.support.application.YApplication;
import com.yuqianhao.support.cache.CacheHelpManager;
import com.yuqianhao.support.cache.ICacheAction;
import com.yuqianhao.support.dialog.BasisDialog;
import com.yuqianhao.support.dialog.SelectDialog;
import com.yuqianhao.support.http.HttpRequestManager;
import com.yuqianhao.support.http.IHttpRequestAction;
import com.yuqianhao.support.http.IHttpRequestBody;
import com.yuqianhao.support.http.IHttpRequestResponse;
import com.yuqianhao.support.io.BufferIOStreamManager;
import com.yuqianhao.support.io.IBufferIOStreamAction;
import com.yuqianhao.support.log.YLog;
import com.yuqianhao.support.notif.INotifyService;
import com.yuqianhao.support.notif.NotifyServiceImplV0;
import com.yuqianhao.support.notif.NotifyServiceManager;
import com.yuqianhao.support.service.image.IImageLoader;
import com.yuqianhao.support.service.image.ImageLoaderManager;
import com.yuqianhao.support.thread.IThreadAction;
import com.yuqianhao.support.thread.ThreadManager;

import java.io.IOException;

public class YBaseActivity extends AppCompatActivity implements IYActivityInterface,
        NotifyServiceImplV0.OnMessageNotifyCallback {
    private static final IImageLoader IMAGE_LOADER = ImageLoaderManager.bindImageLoaderService();
    private static final IThreadAction THREAD_ACTION = ThreadManager.getThreadManagerInterface();
    private ICacheAction<String> CACHE_ACTION;


    private static final IHttpRequestAction HTTP_REQUEST_ACTION = HttpRequestManager.getHttpRequestInterface();
    private static final IBufferIOStreamAction BUFFER_IO_STREAM_ACTION = BufferIOStreamManager.getBufferIoStreamInterface();
    private final INotifyService notifyService = NotifyServiceManager.getInstance(this, this);
    private ProgressDialog progressDialog = null;

    protected int __$$stateBarColor = 0xffffffff;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getApplication() instanceof YApplication)) {
            CACHE_ACTION = CacheHelpManager.bindCacheHelperServiceV0(this);
        } else {
            CACHE_ACTION = CacheHelpManager.bindCacheHelperService();
        }
        StateBar stateBar = getClass().getAnnotation(StateBar.class);
        if (stateBar != null) {
            __$$stateBarColor = stateBar.color();
            if (stateBar.fontDark()) {
                setStateBarColorBright(__$$stateBarColor);
            } else {
                setStateBarColorDark(__$$stateBarColor);
            }
        }

    }

    @Override
    public void loadBitmapToImageView(String url, ImageView imageView) {
        IMAGE_LOADER.loadImageCache(this, url, imageView);
    }


    @Override
    public void loadBitmapToImageView(Uri uri, ImageView imageView) {
        IMAGE_LOADER.loadImageCache(this, uri, imageView);
    }

    @Override
    public void startThread(Runnable runnable) {
        THREAD_ACTION.run(runnable);
    }

    @Override
    public void startUIThread(Runnable runnable) {
        THREAD_ACTION.runUI(runnable);
    }

    @Override
    public void cachePut(String key, String value) {
        CACHE_ACTION.put(key, value);
    }

    @Override
    public String cacheGet(String key) {
        return CACHE_ACTION.get(key);
    }

    @Override
    public String cacheSet(String key, String value) {
        return CACHE_ACTION.set(key, value);
    }

    @Override
    public String cacheRemove(String key) {
        return CACHE_ACTION.remove(key);
    }

    @Override
    public BasisDialog createBasisDialog() {
        return new BasisDialog(this);
    }

    @Override
    public BasisDialog createBasisDialog(String title) {
        return new BasisDialog.Builder(this).title(title).build();
    }

    @Override
    public BasisDialog createBasisDialog(String title, String message) {
        return new BasisDialog.Builder(this).title(title).message(message).build();
    }


    @Override
    public void showBasisMessageDialog(String message) {
        final BasisDialog basisDialog = createBasisDialog();
        basisDialog.setContextMessage(message);
        basisDialog.setLeftButton("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basisDialog.dismiss();
            }
        });
        basisDialog.show();
    }

    @Override
    public SelectDialog createSelectDialog(String title, String... item) {
        SelectDialog selectDialog = new SelectDialog(this);
        selectDialog.setDialogTitle(title);
        for (String iter : item) {
            selectDialog.addItem(iter);
        }
        return selectDialog;
    }

    @Override
    public void post(IHttpRequestBody requestBody, IHttpRequestResponse RequestResponse) {
        HTTP_REQUEST_ACTION.post(requestBody, RequestResponse);
    }

    @Override
    public void get(IHttpRequestBody requestBody, IHttpRequestResponse RequestResponse) {
        HTTP_REQUEST_ACTION.get(requestBody, RequestResponse);
    }

    @Override
    public void writeData(String path, String data) throws IOException {
        BUFFER_IO_STREAM_ACTION.writeFile(path, data);
    }

    @Override
    public String readData(String path) throws IOException {
        return BUFFER_IO_STREAM_ACTION.readFile(path);
    }

    @Override
    public void showToast(Object o) {
        if (getApplication() instanceof YApplication) {
            YApplication.getInstance().showToast(o);
        } else {
            Toast.makeText(this, o.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void logDebug(String msg) {
        YLog.debug(this, msg);
    }

    @Override
    public void logWarn(String msg) {
        YLog.warn(this, msg);
    }

    @Override
    public void logInfo(String msg) {
        YLog.info(this, msg);
    }

    @Override
    public void logError(String msg) {
        YLog.error(this, msg);
    }

    @Override
    public void logDebug(String msg, Exception e) {
        YLog.debug(this, msg, e);
    }

    @Override
    public void logWarn(String msg, Exception e) {
        YLog.warn(this, msg, e);
    }

    @Override
    public void logInfo(String msg, Exception e) {
        YLog.info(this, msg, e);
    }

    @Override
    public void logError(String msg, Exception e) {
        YLog.error(this, msg, e);
    }


    private final void showMessageView(String msg,
                                       int backgroundColor,
                                       boolean setStabarBright,
                                       int textColor,
                                       int stabarColor,
                                       boolean stabarBright) {

        NotifyServiceManager.setNotifyServiceActivity(notifyService, this);
        notifyService.showMessageView(msg, backgroundColor, setStabarBright, textColor, stabarColor, stabarBright);
    }

    @Override
    public void showSuccressNotifyMsg(String msg) {
        int stabarColor = 0xffffffff;
        boolean stabarBright = true;
        StateBar stateBar = getClass().getAnnotation(StateBar.class);
        if (stateBar != null) {
            stabarColor = stateBar.color();
            stabarBright = stateBar.fontDark();
        } else {
            YLog.warn(this, "showSuccressNotifyMsg ：We did not get the original StateBar annotation object for the Activity. Did we forget the annotation?");
        }
        showMessageView(msg,
                0xff2E8B57,
                true,
                Color.WHITE,
                stabarColor,
                stabarBright);
    }

    @Override
    public void showErrorNotifyMsg(String msg) {
        int stabarColor = 0xffffffff;
        boolean stabarBright = true;
        StateBar stateBar = getClass().getAnnotation(StateBar.class);
        if (stateBar != null) {
            stabarColor = stateBar.color();
            stabarBright = stateBar.fontDark();
        } else {
            YLog.warn(this, "showErrorNotifyMsg ：We did not get the original StateBar annotation object for the Activity. Did we forget the annotation?");
        }
        showMessageView(msg,
                0xffe34742,
                true,
                Color.WHITE,
                stabarColor,
                stabarBright);
    }

    @Override
    public void setStateBarColorBright(int color) {
        StatusBarCompat.setStatusBarColor(getWindow(), color, true);
    }

    @Override
    public void setStateBarColorDark(int color) {
        StatusBarCompat.setStatusBarColor(getWindow(), color, false);
    }

    @Override
    public void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        if (progressDialog.isShowing()) {
            progressDialog.setMessage(message);
        } else {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    @Override
    public void setProgressDialogText(String message) {
        showProgressDialog(message);
    }

    @Override
    public void closeProgressDialog() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }
        progressDialog.dismiss();
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onMessageNotifyClose() {

    }
}
