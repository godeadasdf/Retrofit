package com.b.kang.retrofit.fragment.daily;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.b.kang.retrofit.R;
import com.b.kang.retrofit.fragment.BaseFragment;
import com.b.kang.retrofit.network.interfaces.INetData;
import com.b.kang.retrofit.network.model.DailyContent;
import com.b.kang.retrofit.network.manager.DailyManager;
import com.google.gson.Gson;

/**
 * Created by kang on 17-4-24.
 */
public class ContentFragment extends BaseFragment
    implements INetData<DailyContent>{

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
        dailyManager.getNewsContent(this, id);
    }


    private void initWebView(String body) {
        WebView webView = (WebView)rootView.findViewById(R.id.webview);
        webView.loadData(body, "text/html; charset=UTF-8", null);
    }

    @Override
    public void onDataBack(DailyContent dailyContent) {
        Log.d(tag(), new Gson().toJson(dailyContent));
        initWebView(dailyContent.body);
    }

    @Override
    public void onError() {
        Log.d(tag(),"NetError");
    }
}