package com.b.kang.retrofit.network.manager;

import android.util.Log;

import com.b.kang.retrofit.model.DailyContent;
import com.b.kang.retrofit.model.DailyHistory;
import com.b.kang.retrofit.model.DailyLatestDetail;
import com.b.kang.retrofit.network.interfaces.IDailyHistory;
import com.b.kang.retrofit.network.interfaces.IDailyTop;
import com.b.kang.retrofit.network.interfaces.IDailyContent;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kang on 17-4-20.
 */
public class DailyManager extends BaseManager {

    //// TODO: 17-4-26 find a way to catch exception when 404 happens

    private static class DailyManagerHolder {
        private static DailyManager manager = new DailyManager();
    }

    public static DailyManager instance() {
        return DailyManagerHolder.manager;
    }

    private IDailyTop iDailyTop;
    private IDailyContent iDailyContent;
    private IDailyHistory iDailyHistory;
    private Retrofit retrofit;
    private final static String DAILY_BASE_URL = "http://news-at.zhihu.com/";

    private DailyManager() {
        // super();  你父类不止一个构造函数时，显示的调用super才有意义
        retrofit = new Retrofit.Builder()
                .baseUrl(DAILY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        iDailyTop = retrofit.create(IDailyTop.class);
        iDailyContent = retrofit.create(IDailyContent.class);
        iDailyHistory = retrofit.create(IDailyHistory.class);
    }

    //get daily top item list
    public void getDaily(Consumer<DailyLatestDetail> consumer) {
        Log.d(Tag(),"getDaily");
        iDailyTop.getDailyDetail()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    //get daily news content
    public void getNewsContent(Consumer<DailyContent> consumer, long id) {
        Log.d(Tag(),"getNewsContent");
        iDailyContent.getNewsContent(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    //get daily prior item list
    public void getDailyHistory(Consumer<DailyHistory> consumer, String date) {
        Log.d(Tag(),"getDailyHistory");
        iDailyHistory.getDailyHistory(date)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }
}
