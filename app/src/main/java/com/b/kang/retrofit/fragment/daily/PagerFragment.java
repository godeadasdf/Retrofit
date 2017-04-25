package com.b.kang.retrofit.fragment.daily;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private HomePagerAdapter adapter;
    private List<BaseFragment> fragments;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAdapter();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_pager, container, false);
        initPager(view);
        return view;
    }

    public void initAdapter(){
        Log.d(Tag(),"initAdapter");
        fragments = new ArrayList<>();
        fragments.add(new ItemFragment());
       /* fragments.add(new ItemFragment());
        fragments.add(new ItemFragment());*/
        //// TODO: 17-4-25 to know more about the difference between getChildFragmentManager and getFragmentManager 
        adapter = new HomePagerAdapter(getChildFragmentManager(), fragments);
    }


    private void initPager(View view) {
        Log.d(Tag(), "initPager");
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
        pager.setOffscreenPageLimit(2); //set preload page num
        //pager.setCurrentItem(0);
        pager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //// TODO: 17-4-24 need add reaction for bottom navigator style change
        Log.d(Tag(),"position:" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}