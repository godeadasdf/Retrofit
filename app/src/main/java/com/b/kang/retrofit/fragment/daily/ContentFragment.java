package com.b.kang.retrofit.fragment.daily;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b.kang.retrofit.R;
import com.b.kang.retrofit.fragment.BaseFragment;
import com.b.kang.retrofit.model.DailyContent;
import com.b.kang.retrofit.network.manager.DailyManager;
import com.google.gson.Gson;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by kang on 17-4-24.
 */
public class ContentFragment extends BaseFragment {

    private long id;

    private DailyManager dailyManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_content, container, false);
        initArg();
        getContent();
        return view;
    }

    private void initArg() {
        Bundle bundle = getArguments();
        id = bundle.getLong("id");
    }

    private void getContent() {
        dailyManager = DailyManager.instance();
        Consumer<DailyContent> consumer = new Consumer<DailyContent>() {
            @Override
            public void accept(DailyContent dailyContent) throws Exception {
                Log.d(Tag(),new Gson().toJson(dailyContent));
            }
        };
        dailyManager.getNewsContent(consumer,id);
    }
}