package com.b.kang.retrofit.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.b.kang.retrofit.KApplication;
import com.b.kang.retrofit.R;
import com.b.kang.retrofit.database.dao.GreenDaoManager;
import com.b.kang.retrofit.util.FragmentStack;

/**
 * Created by kang on 17-4-20.
 */
public abstract class BaseFragment extends Fragment {

    protected FragmentManager baseFramentManager;

    protected View rootView;
    protected Bundle stateKeeper; //to keep state for fragment

    protected Context baseContext;
    protected Handler baseHandler;

    protected GreenDaoManager greenDaoManager;

    public BaseFragment() {
        stateKeeper = new Bundle();
        baseHandler = new Handler();
        greenDaoManager = KApplication.getGreenDaoManager();
    }

    protected void saveState() {
    }

    protected void restoreState() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseFramentManager = getActivity().getSupportFragmentManager();
        baseContext = getContext();
    }

    private void replaceFragment(BaseFragment fragment) {
        FragmentTransaction ft = baseFramentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(fragment.tag());
        ft.commit();
    }

    public void presentFragment(BaseFragment fragment) {
        replaceFragment(fragment);
        FragmentStack.instance().push(fragment);
    }

    //dynamic initialize in onActivityCreated static initialize in onCreateView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        restoreState();
    }

    public void presentFragmentWithData(BaseFragment fragment, Bundle data) {
        fragment.setArguments(data);
        presentFragment(fragment);
    }

    public void backToPriorFragment() {
        FragmentTransaction ft = baseFramentManager.beginTransaction();
        FragmentStack.instance().pop();
        baseFramentManager.popBackStack();
    }

    public <T> Bundle assembleData(String key, T obj) {
        Bundle bundle = new Bundle();
        if (obj instanceof Long) {
            bundle.putLong(key, ((Long) obj).longValue());
        }
        return bundle;
    }

    public String tag() {
        return this.getClass().getSimpleName();
    }

    public void onBackPressed() {
        backToPriorFragment();
    }

    //for extending
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    //similar to onResume
    protected void onVisible() {

    }

    //similar to onPause
    protected void onInVisible() {

    }

    //for rotate
    @Override
    public void onSaveInstanceState(Bundle outState) {
        saveState();
    }

    //for stack back
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveState();
    }
}
