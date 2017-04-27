package com.b.kang.retrofit.util;

/**
 * Created by kang on 17-4-27.
 */
public enum NetState {

    MOBILE2G(1),

    MOBILE3G(2),

    WIFI(3),

    NONE(4);

    private NetState(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    private int value;
}
