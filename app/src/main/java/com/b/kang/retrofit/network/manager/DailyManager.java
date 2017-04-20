package com.b.kang.retrofit.network.manager;

/**
 * Created by kang on 17-4-20.
 */
public class DailyManager {

    private static class DailyManagerHolder {
        private static DailyManager manager = new DailyManager();
    }

    public static DailyManager instance() {
        return DailyManagerHolder.manager;
    }

    private DailyManager() {
        super();
        retrofic
    }

}
