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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by kang on 17-4-26.
 */
public class HistoryItemFragment extends BaseFragment {

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
    }
    
    
    private void initAdapter() {
        dailyManager = DailyManager.instance();
        currentDate = new Date();
        items = new ArrayList<>();
        adapter = new TopItemAdapter(items,getContext());
        Consumer<DailyHistory> consumer = new Consumer<DailyHistory>() {
            @Override
            public void accept(DailyHistory dailyHistory) throws Exception {
                setDataForList(dailyHistory);
            }
        };
        dailyManager.getDailyHistory(consumer, DateUtil.date(currentDate,DateUtil.PATTERN_ONE));
    }
    
    private void setDataForList(DailyHistory history){
        adapter.setNewData(history.stories);
    }
}
