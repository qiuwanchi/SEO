package com.qiuwanchi.seo.utils;

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
        return serverConfig.getUrl() + "/image/" + filePath.substring(9);
    }

    public static String getVideoUrl(String filePath){
        return serverConfig.getUrl() + "/video/" + filePath.substring(9);
    }
}
