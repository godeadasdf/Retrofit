package com.b.kang.retrofit.database.interfaces;

/**
 * Created by kang on 17-4-27.
 */
public interface IDbData<T> {

    void onLoadData(T data);
}
