package com.b.kang.retrofit.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kang on 17-4-26.
 */
public class DateUtil {

    public static String PATTERN_ONE = "yyyyMMdd";

    public static String date(Date date, String pattern) {
        SimpleDateFormat dateFormater = new SimpleDateFormat(pattern);
        return dateFormater.format(date);
    }
}
