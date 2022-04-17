package com.qiuwanchi.seo.utils;

public class Utils {

    public static final String HTTP = "http://";

    public static boolean isStartsWith(String str, String startStr){
        if(str.startsWith(startStr)){
            return true;
        }
        return false;
    }

    /**
     * html解码
     * @param content
     * @return
     */
    public static String htmlDecode(String content){
        if(content.length() == 0) {
            return "";
        }
        content = content.replaceAll("&amp;", "&");
        content = content.replaceAll("&lt;", "<");
        content = content.replaceAll("&gt;", ">");
        content = content.replaceAll("&nbsp;", " ");
        content = content.replaceAll("&#39;", "'");
        content = content.replaceAll("&quot;", "\"");
        content = content.replaceAll("&amp;", "&");

        content = content.replaceAll("＆amp;", "&");
        content = content.replaceAll("＆lt;", "<");
        content = content.replaceAll("＆gt;", ">");
        content = content.replaceAll("＆nbsp;", " ");
        content = content.replaceAll("＆#39;", "'");
        content = content.replaceAll("＆quot;", "\"");
        content = content.replaceAll("＆amp;", "&");

        return content;
    }
}
