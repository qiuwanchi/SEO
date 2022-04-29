package com.qiuwanchi.seo.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";

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

    public static List<String> toList(String keywords){
        if(StringUtils.isBlank(keywords)){
            return Lists.newArrayList();
        }
        List<String> keywordsList = Lists.newArrayList();
        keywords = keywords.replaceAll("，", ",");
        String[] keywordsArr = keywords.split(",");
        for (String k : keywordsArr){
            keywordsList.add(k);
        }
        return keywordsList;
    }

    public static String replaceAll(String keywords){
        if(StringUtils.isBlank(keywords)){
            return keywords;
        }
        return keywords.replaceAll("，", ",");
    }

}
