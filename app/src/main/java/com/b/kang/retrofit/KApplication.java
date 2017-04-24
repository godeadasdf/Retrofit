package com.b.kang.retrofit;

import android.app.Application;

import com.b.kang.retrofit.util.FragmentStack;

/**
 * Created by kang on 17-4-24.
 */
public class KApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FragmentStack.instance().init();
    }
}
