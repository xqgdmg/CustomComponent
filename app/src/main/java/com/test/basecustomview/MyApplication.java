package com.test.basecustomview;

import android.app.Application;
import android.content.Context;

/**
 * Created by Chris on 2017/5/27.
 */
public class MyApplication extends Application{
    private static Context mContext;

     // 这个实例可以调用 getResources 等方法
    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        sInstance = this;
    }

    public static Context getContext(){
        return mContext;
    }

    public static synchronized MyApplication getInstance() {
        return sInstance;
    }
}
