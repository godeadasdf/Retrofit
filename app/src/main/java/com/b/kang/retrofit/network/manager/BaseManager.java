package com.b.kang.retrofit.network.manager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by kang on 17-4-20.
 */
public class BaseManager {

    //// TODO: 17-4-27  need to add expection catcher for Retrofit neterror
    protected OkHttpClient client;

    protected BaseManager() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    protected String Tag() {
        return this.getClass().getSimpleName();
    }
}
