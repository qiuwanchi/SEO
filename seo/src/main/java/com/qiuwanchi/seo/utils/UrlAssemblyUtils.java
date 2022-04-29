package com.qiuwanchi.seo.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UrlAssemblyUtils {

    @Autowired
    private ServerConfig serverConfig2;

    private static ServerConfig serverConfig;

    @PostConstruct
    public void init(){
        serverConfig = serverConfig2;
    }

    public static String getImageUrl(String filePath){
        StringBuffer sb = new StringBuffer();
        sb.append(serverConfig.getUrl()).append("/image/");
        if(StringUtils.isNotBlank(filePath)){
            sb.append(filePath.substring(9));
        }
        return sb.toString();
    }

    public static String getVideoUrl(String filePath){
        StringBuffer sb = new StringBuffer();
        sb.append(serverConfig.getUrl()).append("/video/");
        if(StringUtils.isNotBlank(filePath)){
            sb.append(filePath.substring(9));
        }
        return sb.toString();
    }
}
