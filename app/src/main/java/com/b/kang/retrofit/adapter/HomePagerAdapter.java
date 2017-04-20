package com.b.kang.retrofit.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by kang on 17-4-20.
 */
public class HomePagerAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
