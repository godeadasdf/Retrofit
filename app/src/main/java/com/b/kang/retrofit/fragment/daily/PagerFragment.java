package com.b.kang.retrofit.fragment.daily;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;

import com.b.kang.retrofit.R;
import com.b.kang.retrofit.adapter.HomePagerAdapter;
import com.b.kang.retrofit.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kang on 17-4-24.
 */
public class PagerFragment extends BaseFragment
        implements ViewPager.OnPageChangeListener {

    private ViewPager pager;
    private TabLayout table;


    private HomePagerAdapter adapter;

    private List<BaseFragment> fragments;
    private List<String> title_strings;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTableTitle();
        initAdapter();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_pager, container, false);
        initPager(view);
        return view;
    }

    public void initAdapter() {
        Log.d(tag(), "initAdapter");
        fragments = new ArrayList<>();
        fragments.add(new TopItemFragment());
        fragments.add(new HistoryItemFragment());
        //// TODO: 17-4-25 to know more about the difference between getChildFragmentManager and getFragmentManager
        adapter = new HomePagerAdapter(getChildFragmentManager(), fragments, title_strings);
    }


    private void initPager(View view) {
        Log.d(tag(), "initPager");
        table = (TabLayout) view.findViewById(R.id.table);
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
        pager.setOffscreenPageLimit(2); //set preload page num
        //pager.setCurrentItem(0);
        pager.addOnPageChangeListener(this);
        table.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < 2; i++) {
            table.addTab(table.newTab().setText(title_strings.get(i)));
        }
        table.setupWithViewPager(pager);
    }

    private void initTableTitle() {
        title_strings = new ArrayList<>();
        title_strings.add("Top News");
        title_strings.add("ALL News");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //// TODO: 17-4-24 need add reaction for bottom navigator style change
        Log.d(tag(), "position:" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}