package com.b.kang.retrofit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.b.kang.retrofit.fragment.BaseFragment;

import java.util.List;

/**
 * Created by kang on 17-4-20.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private FragmentManager fragmetnmanager;  //创建FragmentManager
    private List<BaseFragment> listfragment; //创建一个List<Fragment>

    public HomePagerAdapter(FragmentManager fm,List<BaseFragment> list) {
        super(fm);
        this.fragmetnmanager=fm;
        this.listfragment=list;
    }

    @Override
    public Fragment getItem(int arg0) {
        return listfragment.get(arg0); //返回第几个fragment
    }

    @Override
    public int getCount() {
        return listfragment.size(); //总共有多少个fragment
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container,
                position);
        fragmetnmanager.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        Fragment fragment = listfragment.get(position);
        fragmetnmanager.beginTransaction().hide(fragment).commit();
    }
}
