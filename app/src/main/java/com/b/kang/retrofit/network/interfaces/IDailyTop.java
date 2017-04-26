package com.b.kang.retrofit.network.interfaces;

import com.b.kang.retrofit.network.model.DailyLatestDetail;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by kang on 17-4-20.
 */
public interface IDailyTop {

    @GET("api/4/news/latest")
    Observable<DailyLatestDetail> getDailyDetail();
}
