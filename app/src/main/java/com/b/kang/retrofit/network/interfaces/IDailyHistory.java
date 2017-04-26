package com.b.kang.retrofit.network.interfaces;

import com.b.kang.retrofit.model.DailyHistory;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kang on 17-4-25.
 */
public interface IDailyHistory {

    @GET("api/4/news/before/{date}")
    Observable<DailyHistory> getDailyHistory(@Path("date") String date);
}
