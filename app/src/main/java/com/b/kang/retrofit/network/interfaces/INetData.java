package com.b.kang.retrofit.network.interfaces;

/**
 * Created by kang on 17-4-27.
 */
public interface INetData<T> {

    void onDataBack(T data);

    void onError();
}
