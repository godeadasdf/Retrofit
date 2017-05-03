package com.b.kang.retrofit.database.manager;

import android.util.Log;

import com.b.kang.retrofit.database.dao.GreenDaoManager;
import com.b.kang.retrofit.database.dao.ZhiHuItemDao;
import com.b.kang.retrofit.database.entity.ZhiHuItem;
import com.b.kang.retrofit.database.interfaces.IDbData;
import com.b.kang.retrofit.network.model.DailyLatestDailyItem;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kang on 17-4-27.
 */
public class ZhiHuDaoManager extends BaseEntityManager {

    private ZhiHuDaoManager() {
    }

    private static class ZhiHuDaoManagerHolder {
        private static ZhiHuDaoManager zhiHuDaoManager = new ZhiHuDaoManager();
    }

    public static ZhiHuDaoManager instance() {
        return ZhiHuDaoManagerHolder.zhiHuDaoManager;
    }

    public void getZhiHuList(final IDbData<List<ZhiHuItem>> iDbData,final long id) {

        //// TODO: 17-4-27 make a Flowable and Subscrible version work
        Observable.create(new ObservableOnSubscribe<List<ZhiHuItem>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ZhiHuItem>> e) throws Exception {
                ZhiHuItemDao dao = greenDaoManager.getNewSession().getZhiHuItemDao();
                List<ZhiHuItem> list;
                if (id == 0) {
                    list = dao.queryBuilder().limit(20).list();
                }else {
                    list = dao.queryBuilder().limit(20).where(ZhiHuItemDao.Properties.Id.gt(id)).list();
                }
                e.onNext(list);
                e.onComplete();
            }
        }).
                subscribeOn(Schedulers.io()).
                unsubscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<List<ZhiHuItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ZhiHuItem> list) {
                        iDbData.onLoadData(list);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void saveZhiHuList(List<DailyLatestDailyItem> items, final String date) {
        Flowable.just(items)
                .flatMap(new Function<List<DailyLatestDailyItem>, Publisher<DailyLatestDailyItem>>() {
                    @Override
                    public Publisher<DailyLatestDailyItem> apply(List<DailyLatestDailyItem> dailyLatestDailyItems) throws Exception {
                        return Flowable.fromIterable(dailyLatestDailyItems);
                    }//// TODO: 17-4-27 why map() function not launch
                })
                .map(new Function<DailyLatestDailyItem, ZhiHuItem>() {
                    @Override
                    public ZhiHuItem apply(DailyLatestDailyItem item) throws Exception {
                        ZhiHuItem zhi = new ZhiHuItem();
                        zhi.date = date;
                        zhi.newsId = item.id;
                        zhi.imageUrl = item.images[0];
                        zhi.title = item.title;
                        return zhi;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<ZhiHuItem>() {
                    @Override
                    public void accept(ZhiHuItem item) {
                        GreenDaoManager.getInstance().getNewSession().insertOrReplace(item);
                    }

                });
    }
}

