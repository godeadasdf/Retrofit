package com.b.kang.retrofit.model;

import com.google.gson.Gson;

/**
 * Created by kang on 17-4-21.
 */
public class BaseDailyItem {

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
}
