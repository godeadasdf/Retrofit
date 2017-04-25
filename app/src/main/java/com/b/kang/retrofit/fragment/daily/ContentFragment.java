package com.b.kang.retrofit.fragment.daily;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

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
        rootView = inflater.inflate(R.layout.fragment_daily_content, container, false);
        initArg();
        getContent();
        return rootView;
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
                Log.d(Tag(), new Gson().toJson(dailyContent));
                initWebView(dailyContent.body);
            }
        };
        dailyManager.getNewsContent(consumer, id);
    }


    private void initWebView(String body) {
        WebView webView = (WebView)rootView.findViewById(R.id.webview);
        webView.loadData(body, "text/html; charset=UTF-8", null);
    }

}