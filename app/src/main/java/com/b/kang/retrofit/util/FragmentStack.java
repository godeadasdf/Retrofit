package com.b.kang.retrofit.util;

import android.support.v4.app.Fragment;

import com.b.kang.retrofit.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kang on 17-4-24.
 */
public class FragmentStack {

    private List<BaseFragment> stack;

    private int size;

    private static class FragmentStackHolder {
        private static FragmentStack fragmentStack = new FragmentStack();
    }

    private FragmentStack() {}

    public static FragmentStack instance() {
        return FragmentStackHolder.fragmentStack;
    }

    public void init() {
        stack = new ArrayList<>();
        size = 0;
    }

    public BaseFragment getTop() {
        return stack.get(size - 1);
    }

    public BaseFragment pop() {
        if (size == 0) {
            return null;
        }
        BaseFragment pop = stack.get(size - 1);
        stack.remove(size - 1);
        size --;
        return pop;
    }

    public void push(BaseFragment push) {
        stack.add(push);
        size ++;
    }

    public int getSize() {
        return size;
    }
}
