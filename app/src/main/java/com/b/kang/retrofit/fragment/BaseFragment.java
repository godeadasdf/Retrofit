package com.b.kang.retrofit.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.b.kang.retrofit.R;
import com.b.kang.retrofit.util.FragmentStack;

/**
 * Created by kang on 17-4-20.
 */
public class BaseFragment extends Fragment {

    protected FragmentManager baseFramentManager;

    protected View rootView;

    public BaseFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseFramentManager = getActivity().getSupportFragmentManager();
    }

    private void replaceFragment(BaseFragment fragment) {
        FragmentTransaction ft = baseFramentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    public void presentFragment(BaseFragment fragment) {
        replaceFragment(fragment);
        FragmentStack.instance().push(fragment);
    }

    public void presentFragmentWithData(BaseFragment fragment, Bundle data) {
        fragment.setArguments(data);
        presentFragment(fragment);
    }

    public void backToPriorFragment() {
        FragmentTransaction ft = baseFramentManager.beginTransaction();
        ft.remove(FragmentStack.instance().pop());
        replaceFragment(FragmentStack.instance().getTop());
    }

    public <T> Bundle assembleData(String key, T obj) {
        Bundle bundle = new Bundle();
        if (obj instanceof Long) {
            bundle.putLong(key, ((Long) obj).longValue());
        }
        return bundle;
    }

    protected String Tag() {
        return this.getClass().getSimpleName();
    }

    public void onBackPressed() {
        backToPriorFragment();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){

        }else {

        }
    }
}
