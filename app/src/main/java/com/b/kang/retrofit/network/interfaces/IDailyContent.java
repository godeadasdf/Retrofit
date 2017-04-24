package com.b.kang.retrofit.network.interfaces;

import com.b.kang.retrofit.model.DailyContent;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kang on 17-4-24.
 */
public interface IDailyContent {
    @GET("api/4/news/{id}")
    Observable<DailyContent> getNewsContent(@Path("id") long id);
}
