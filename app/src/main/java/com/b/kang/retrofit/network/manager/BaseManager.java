package com.b.kang.retrofit.network.manager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by kang on 17-4-20.
 */
public class BaseManager {

    protected   OkHttpClient client;

    protected BaseManager() {
        client = new OkHttpClient.Builder().build();
    }
}
