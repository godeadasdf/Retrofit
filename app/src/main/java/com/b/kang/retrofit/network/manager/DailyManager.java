package com.b.kang.retrofit.network.manager;

import com.b.kang.retrofit.model.DailyContent;
import com.b.kang.retrofit.model.DailyLatestDailyItem;
import com.b.kang.retrofit.model.DailyLatestDetail;
import com.b.kang.retrofit.network.interfaces.IDaily;
import com.b.kang.retrofit.network.interfaces.IDailyContent;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kang on 17-4-20.
 */
public class DailyManager extends BaseManager {

    private static class DailyManagerHolder {
        private static DailyManager manager = new DailyManager();
    }

    public static DailyManager instance() {
        return DailyManagerHolder.manager;
    }

    private IDaily iDaily;
    private IDailyContent iDailyContent;
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
        iDaily = retrofit.create(IDaily.class);
        iDailyContent = retrofit.create(IDailyContent.class);
    }

    //get daily item list
    public void getDaily(Consumer<DailyLatestDetail> consumer) {
        iDaily.getDailyDetail()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    //get daily news content
    public void getNewsContent(Consumer<DailyContent> consumer, long id) {
        iDailyContent.getNewsContent(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }
}
