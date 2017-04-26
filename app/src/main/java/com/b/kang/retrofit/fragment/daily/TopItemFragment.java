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
import com.b.kang.retrofit.fragment.BaseFragment;
import com.b.kang.retrofit.model.BaseDailyItem;
import com.b.kang.retrofit.model.DailyLatestDetail;
import com.b.kang.retrofit.network.manager.DailyManager;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


/**
 * Created by kang on 17-4-20.
 */
public class TopItemFragment extends BaseFragment
        implements BaseQuickAdapter.OnItemClickListener {

    private DailyManager dailyManager;
    /*@BindView(R.id.daily_content)
    public TextView dailyContent;*/

    public RecyclerView dailyView;
    private TopItemAdapter dailyAdapter;

    private List<BaseDailyItem> items = new ArrayList<>();


    private Consumer<DailyLatestDetail> consumer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dailyManager = DailyManager.instance();
        initAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_daily_list, container, false);
       /* dailyContent = (TextView)view.findViewById(R.id.daily_content);*/
        //// TODO: 17-4-21 bind can't come into effect ... need to be solved
        //ButterKnife.bind(this, view);
        initView(view);
        return view;
    }
    //TODO for dynamic view, you'd better initialize in onCreateActivity
    //// TODO: 17-4-24 stated-fragment  

    private void initAdapter() {
        Log.d(Tag(), "initAdapter");
        dailyAdapter = new TopItemAdapter(items, getContext());
        consumer = new Consumer<DailyLatestDetail>() {
            @Override
            public void accept(DailyLatestDetail dailyLatestDetail) throws Exception {
                //dailyContent.setText(new Gson().toJson(dailyLatestDetail));
                List<BaseDailyItem> allItems = new ArrayList<>();
                allItems.addAll(dailyLatestDetail.top_stories);
                allItems.addAll(dailyLatestDetail.stories);
                setDataForDailyView(dailyLatestDetail.date, allItems);
                items = allItems;
            }
        };
        dailyManager.getDaily(consumer);
    }

    private void initView(View view) {
        Log.d(Tag(), "initView");
        dailyView = (RecyclerView) view.findViewById(R.id.daily_list);
        dailyView.setHasFixedSize(true);
        dailyView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dailyView.setAdapter(dailyAdapter);

    }

    private void setDataForDailyView(String Date, List<BaseDailyItem> items) {
        dailyAdapter.setNewData(items);
        dailyAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Log.d(Tag(), "ItemClick()");
        BaseDailyItem item = (BaseDailyItem) adapter.getItem(position);
        long id = item.getId();
        presentFragmentWithData(new ContentFragment(), assembleData("id", id));
    }
}
