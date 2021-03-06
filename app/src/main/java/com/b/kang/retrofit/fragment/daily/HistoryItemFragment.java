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
import com.b.kang.retrofit.database.interfaces.IDbData;
import com.b.kang.retrofit.database.manager.ZhiHuDaoManager;
import com.b.kang.retrofit.fragment.BaseFragment;
import com.b.kang.retrofit.network.interfaces.INetData;
import com.b.kang.retrofit.network.model.BaseDailyItem;
import com.b.kang.retrofit.network.model.DailyHistory;
import com.b.kang.retrofit.network.model.DailyLatestDailyItem;
import com.b.kang.retrofit.network.manager.DailyManager;
import com.b.kang.retrofit.util.DateUtil;
import com.b.kang.retrofit.util.EntityUtil;
import com.b.kang.retrofit.util.NetState;
import com.b.kang.retrofit.util.NetUtil;
import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;

/**
 * Created by kang on 17-4-26.
 */
public class HistoryItemFragment extends BaseFragment
        implements BaseQuickAdapter.RequestLoadMoreListener,
        INetData<DailyHistory>, IDbData<List<ZhiHuItem>>,
        PullRefreshLayout.OnRefreshListener{

    private DailyManager dailyManager;
    private ZhiHuDaoManager daoManager;

    private TopItemAdapter adapter;
    private PullRefreshLayout refreshLayout;
    private RecyclerView list;

    private long lastMaxId = 0;

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
        refreshLayout = (PullRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        refreshLayout.setOnRefreshListener(this);
        adapter.setOnLoadMoreListener(this, list);
    }


    private void initAdapter() {
        dailyManager = DailyManager.instance();
        daoManager = ZhiHuDaoManager.instance();
        currentDate = new Date();
        items = new ArrayList<>();
        adapter = new TopItemAdapter(items, baseContext);
        adapter.setAutoLoadMoreSize(1);
        getData();
    }

    private void getData() {
        if (NetUtil.netState.getValue() != NetState.NONE.getValue()) {
            //with network load from network
            dailyManager.getDailyHistory(this, DateUtil.date(currentDate, DateUtil.PATTERN_ONE));
        } else {

            //without network load from database
            daoManager.getZhiHuList(this, lastMaxId);
        }
    }

    private void addDataForList(DailyHistory history) {
        adapter.addData(history.stories);
    }

    @Override
    public void onLoadMoreRequested() {
        currentDate = DateUtil.priorDay(currentDate);
        getData();
    }

    @Override
    public void onDataBack(DailyHistory dailyHistory) {
        daoManager.saveZhiHuList(dailyHistory.stories, dailyHistory.date);
        addDataForList(dailyHistory);
        if (refreshLayout.isShown()){
            baseHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(false);
                }
            },2000);
        }
        if (adapter.isLoading()) {
            adapter.loadMoreComplete();
        }
        adapter.setEnableLoadMore(true);
    }

    @Override
    public void onError() {
        Log.d(tag(), "Network Error");
    }

    @Override
    public void onLoadData(List<ZhiHuItem> data) {

        if (adapter.isLoading()) {
            if (data.size() == 0) {
                adapter.loadMoreFail();
            } else {
                adapter.loadMoreComplete();
            }
        }
        adapter.setEnableLoadMore(true);

        adapter.addData(data);
        lastMaxId = ((BaseDailyItem) adapter.getItem(items.size() - 1)).getId();
    }

    @Override
    public void onRefresh() {
        getData();
    }
}
