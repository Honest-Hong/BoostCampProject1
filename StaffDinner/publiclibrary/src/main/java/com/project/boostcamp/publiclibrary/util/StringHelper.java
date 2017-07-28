package com.project.boostcamp.publiclibrary.util;

/**
 * Created by Hong Tae Joon on 2017-07-26.
 */

public class StringHelper {
    public static String cutEnd(String str, int length) {
        if(str.length() > length - 3) {
            return str.substring(0, length - 2) + "...";
        }
        return str;
    }

    public static String cutStart(String str, int length) {
        if(str.length() > length - 3) {
            int startIndex = str.length() - length;
            return "..." + str.substring(startIndex);
        }
        return str;
    }
}
