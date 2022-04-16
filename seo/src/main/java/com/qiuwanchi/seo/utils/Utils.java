package com.qiuwanchi.seo.utils;

public class Utils {

    public static final String HTTP = "http://";

    public static boolean isStartsWith(String str, String startStr){
        if(str.startsWith(startStr)){
            return true;
        }
        return false;
    }
}
