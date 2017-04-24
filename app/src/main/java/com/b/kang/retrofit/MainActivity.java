package com.b.kang.retrofit;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.b.kang.retrofit.adapter.HomePagerAdapter;
import com.b.kang.retrofit.fragment.BaseFragment;
import com.b.kang.retrofit.fragment.daily.ItemFragment;
import com.b.kang.retrofit.fragment.daily.PagerFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;

    private ViewPager pager;
    private HomePagerAdapter adapter;
    private List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    private void initFragment(){
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, new ItemFragment());
        ft.commit();
    }

}
