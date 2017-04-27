package com.b.kang.retrofit.util;

import android.util.Log;

import com.b.kang.retrofit.database.dao.GreenDaoManager;
import com.b.kang.retrofit.database.dao.ZhiHuItemDao;
import com.b.kang.retrofit.database.entity.ZhiHuItem;
import com.b.kang.retrofit.network.model.DailyLatestDailyItem;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.subscribers.StrictSubscriber;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kang on 17-4-27.
 */
public class EntityUtil {

    private final static String TAG = "EntityUtil";

    public static void saveDailyModel(List<DailyLatestDailyItem> items, final String date) {
        Flowable.just(items)
                .flatMap(new Function<List<DailyLatestDailyItem>, Publisher<DailyLatestDailyItem>>() {
                    @Override
                    public Publisher<DailyLatestDailyItem> apply(List<DailyLatestDailyItem> dailyLatestDailyItems) throws Exception {
                        return Flowable.fromIterable(dailyLatestDailyItems);
                    }//// TODO: 17-4-27 why map() function not launch 
                })./*map(new Function<DailyLatestDailyItem, ZhiHuItem>() {
            @Override
            public ZhiHuItem apply(DailyLatestDailyItem item) throws Exception {
                ZhiHuItem zhi = new ZhiHuItem();
                zhi.date = date;
                zhi.newsId = item.id;
                zhi.imageUrl = item.images[0];
                zhi.title = item.title;
                return zhi;
            }
        }).*/subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io()).
                subscribe(new Consumer<DailyLatestDailyItem>() {
                    @Override
                    public void accept(DailyLatestDailyItem item) {
                        Log.d(TAG, "onNext");
                        ZhiHuItem zhi = new ZhiHuItem();
                        zhi.date = date;
                        zhi.newsId = item.id;
                        zhi.imageUrl = item.images[0];
                        zhi.title = item.title;
                        GreenDaoManager.getInstance().getNewSession().insert(zhi);
                    }

                });
    }
}
