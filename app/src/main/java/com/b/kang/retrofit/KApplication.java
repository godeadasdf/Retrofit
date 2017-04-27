package com.b.kang.retrofit;

import android.app.Application;
import android.content.Context;

import com.b.kang.retrofit.database.dao.GreenDaoManager;
import com.b.kang.retrofit.util.FragmentStack;
import com.b.kang.retrofit.util.NetState;
import com.b.kang.retrofit.util.NetUtil;

/**
 * Created by kang on 17-4-24.
 */
public class KApplication extends Application {

    private static Context mAppContext;
    private static GreenDaoManager greenDaoManager;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mAppContext = getApplicationContext();
        FragmentStack.instance().init();
        greenDaoManager = GreenDaoManager.getInstance();
        NetUtil.netState = NetUtil.getAPNType(mAppContext);
    }

    public static Context getmAppContext() {
        return mAppContext;
    }

    public static GreenDaoManager getGreenDaoManager() {
        return greenDaoManager;
    }
}
