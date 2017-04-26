package com.b.kang.retrofit;

import android.app.Application;
import android.content.Context;

import com.b.kang.retrofit.util.FragmentStack;

/**
 * Created by kang on 17-4-24.
 */
public class KApplication extends Application {

    private static Context mAppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        this.mAppContext = getApplicationContext();
        FragmentStack.instance().init();
    }

    public static Context getmAppContext(){
        return mAppContext;
    }
}
