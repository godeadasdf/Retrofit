package com.b.kang.retrofit.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.b.kang.retrofit.R;

/**
 * Created by kang on 17-4-20.
 */
public class BaseFragment extends Fragment {

    protected FragmentManager baseFramentManager;

    public BaseFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseFramentManager = getActivity().getSupportFragmentManager();
    }

    public void presentFragment(BaseFragment fragment) {
        FragmentTransaction ft = baseFramentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    public void presentFragmentWithData(BaseFragment fragment, Bundle data) {
        fragment.setArguments(data);
        presentFragment(fragment);
    }

    public <T> Bundle assembleData(String key,T obj) {
        Bundle bundle = new Bundle();
        if (obj instanceof Long) {
            bundle.putLong(key, ((Long)obj).longValue());
        }
        return bundle;
    }

    protected String Tag() {
        return this.getClass().getSimpleName();
    }
}
