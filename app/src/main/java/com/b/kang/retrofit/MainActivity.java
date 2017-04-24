package com.b.kang.retrofit;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.b.kang.retrofit.adapter.HomePagerAdapter;
import com.b.kang.retrofit.fragment.BaseFragment;
import com.b.kang.retrofit.fragment.DailyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements ViewPager.OnPageChangeListener {

    private FragmentManager fm;

    private ViewPager pager;
    private HomePagerAdapter adapter;
    private List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPager();
    }

    private void initPager(){
        fm = getSupportFragmentManager();
        pager = (ViewPager)findViewById(R.id.pager);
        fragments.add(new DailyFragment());
        fragments.add(new DailyFragment());
        fragments.add(new DailyFragment());
        adapter = new HomePagerAdapter(fm,fragments);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
        pager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //// TODO: 17-4-24 need add reaction for bottom navigator style change 
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
