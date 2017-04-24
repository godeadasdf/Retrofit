package com.b.kang.retrofit;

import android.content.ClipData;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.b.kang.retrofit.adapter.HomePagerAdapter;
import com.b.kang.retrofit.fragment.BaseFragment;
import com.b.kang.retrofit.fragment.daily.ItemFragment;
import com.b.kang.retrofit.fragment.daily.PagerFragment;
import com.b.kang.retrofit.util.FragmentStack;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;

    private FragmentStack fs = FragmentStack.instance();

    private final static String TAG = MainActivity.class.getSimpleName();

    private ViewPager pager;
    private HomePagerAdapter adapter;
    private List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    private void initFragment() {
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ItemFragment itemFragment = new ItemFragment();
        ft.replace(R.id.fragment_container, itemFragment);
        ft.commit();
        fs.push(itemFragment);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed()");
        if (fs.getSize() > 1) {
            fs.getTop().onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}
