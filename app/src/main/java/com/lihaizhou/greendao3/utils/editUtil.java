package com.lihaizhou.greendao3.utils;

/**
 * Created by Administrator on 2017/5/29.
 */

public class editUtil {

    public static boolean isNotEmpty(String s) {
        if (s != null && !s.equals("") || s.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(String s) {
        if (isNotEmpty(s)) {
            return false;
        } else {
            return true;
        }
    }

}
