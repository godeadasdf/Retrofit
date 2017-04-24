package com.b.kang.retrofit.model;

import com.google.gson.Gson;

/**
 * Created by kang on 17-4-21.
 */
public class BaseDailyItem {

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public long getId() {
        if (this instanceof DailyLatestDailyItem) {
           return  ((DailyLatestDailyItem)this).getId();
        }else if (this instanceof DailyLatestTopDailyItem){
            return  ((DailyLatestTopDailyItem)this).getId();
        }else {
            return -1;
        }
    }
}
