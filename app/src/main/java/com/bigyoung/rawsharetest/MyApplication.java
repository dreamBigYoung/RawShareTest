package com.bigyoung.rawsharetest;

import android.app.Application;

import org.xutils.x;

/**
 * Created by BigYoung on 2018/8/19.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
