package com.b.kang.retrofit.fragment.daily;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b.kang.retrofit.R;
import com.b.kang.retrofit.adapter.TopItemAdapter;
import com.b.kang.retrofit.fragment.BaseFragment;
import com.b.kang.retrofit.model.DailyHistory;
import com.b.kang.retrofit.model.DailyLatestDailyItem;
import com.b.kang.retrofit.network.manager.DailyManager;
import com.b.kang.retrofit.util.DateUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by kang on 17-4-26.
 */
public class HistoryItemFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener{

    private DailyManager dailyManager;

    private TopItemAdapter adapter;
    private RecyclerView list;
    
    private Consumer<DailyHistory> consumer;

    private List<DailyLatestDailyItem> items;
    
    private Date currentDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_daily_list, container, false);
        initView();
        return rootView;
    }


    private void initView() {
        list = (RecyclerView) rootView.findViewById(R.id.daily_list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this,list);
    }
    
    
    private void initAdapter() {
        dailyManager = DailyManager.instance();
        currentDate = new Date();
        items = new ArrayList<>();
        adapter = new TopItemAdapter(items,getContext());
        adapter.setAutoLoadMoreSize(1);
        consumer = new Consumer<DailyHistory>() {
            @Override
            public void accept(DailyHistory dailyHistory) throws Exception {
                addDataForList(dailyHistory);
                if (adapter.isLoading()){
                    adapter.loadMoreComplete();
                }
                adapter.setEnableLoadMore(true);
            }
        };
        dailyManager.getDailyHistory(consumer, DateUtil.date(currentDate,DateUtil.PATTERN_ONE));
    }
    
    private void addDataForList(DailyHistory history){
        adapter.addData(history.stories);
    }

    @Override
    public void onLoadMoreRequested() {
        currentDate = DateUtil.priorDay(currentDate);
        dailyManager.getDailyHistory(consumer,DateUtil.date(currentDate,DateUtil.PATTERN_ONE));
    }
}
