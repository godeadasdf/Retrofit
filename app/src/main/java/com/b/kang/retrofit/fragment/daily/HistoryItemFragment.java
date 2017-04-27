package com.b.kang.retrofit.fragment.daily;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b.kang.retrofit.R;
import com.b.kang.retrofit.adapter.TopItemAdapter;
import com.b.kang.retrofit.database.dao.ZhiHuItemDao;
import com.b.kang.retrofit.database.entity.ZhiHuItem;
import com.b.kang.retrofit.fragment.BaseFragment;
import com.b.kang.retrofit.network.interfaces.INetData;
import com.b.kang.retrofit.network.model.DailyHistory;
import com.b.kang.retrofit.network.model.DailyLatestDailyItem;
import com.b.kang.retrofit.network.manager.DailyManager;
import com.b.kang.retrofit.util.DateUtil;
import com.b.kang.retrofit.util.EntityUtil;
import com.b.kang.retrofit.util.NetUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;

/**
 * Created by kang on 17-4-26.
 */
public class HistoryItemFragment extends BaseFragment
        implements BaseQuickAdapter.RequestLoadMoreListener,INetData<DailyHistory> {

    private DailyManager dailyManager;

    private TopItemAdapter adapter;
    private RecyclerView list;

    private Observer<DailyHistory> consumer;

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
        list.setLayoutManager(new LinearLayoutManager(baseContext));
        list.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, list);
    }


    private void initAdapter() {
        dailyManager = DailyManager.instance();
        currentDate = new Date();
        items = new ArrayList<>();
        adapter = new TopItemAdapter(items, baseContext);
        adapter.setAutoLoadMoreSize(1);
        if (NetUtil.isNetworkConnected(baseContext)) {
            //with network load from network
            dailyManager.getDailyHistory(this, DateUtil.date(currentDate, DateUtil.PATTERN_ONE));
        } else {
            //without network load from database
            ZhiHuItemDao dao = greenDaoManager.getNewSession().getZhiHuItemDao();
            List<ZhiHuItem> list = dao.queryBuilder().limit(20).list();
            adapter.addData(list);
        }
    }

    private void addDataForList(DailyHistory history) {
        adapter.addData(history.stories);
    }

    @Override
    public void onLoadMoreRequested() {
        currentDate = DateUtil.priorDay(currentDate);
        dailyManager.getDailyHistory(this, DateUtil.date(currentDate, DateUtil.PATTERN_ONE));
    }

    @Override
    public void onDataBack(DailyHistory dailyHistory) {
        EntityUtil.saveDailyModel(dailyHistory.stories, dailyHistory.date);
        addDataForList(dailyHistory);
        if (adapter.isLoading()) {
            adapter.loadMoreComplete();
        }
        adapter.setEnableLoadMore(true);
    }

    @Override
    public void onError() {
        Log.d(tag(),"Network Error");
    }
}
