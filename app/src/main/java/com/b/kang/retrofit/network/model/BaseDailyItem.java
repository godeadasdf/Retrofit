package com.b.kang.retrofit.network.model;

import android.util.Log;

import com.b.kang.retrofit.database.entity.ZhiHuItem;
import com.google.gson.Gson;

/**
 * Created by kang on 17-4-21.
 */
public class BaseDailyItem {

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public Long getId() {
        if (this instanceof DailyLatestDailyItem) {
            return ((DailyLatestDailyItem) this).id;
        } else if (this instanceof DailyLatestTopDailyItem) {
            return ((DailyLatestTopDailyItem) this).id;
        } else if (this instanceof ZhiHuItem) {
            return ((ZhiHuItem) this).id;
        } else {
            return -1L;
        }
    }
}
