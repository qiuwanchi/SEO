package com.qiuwanchi.seo.constant;

import lombok.Getter;

public enum ThreeElementsOfColumnPageSeoEnum {

    INDEX("index", "首页"),
    ABOUT_US("aboutUs", "关于我们"),
    PRODUCTS("products", "公司产品"),
    SERVICE_CASE("serviceCase", "服务案例"),
    SOLUTION("solution", "解决方案"),
    NEWS("news", "新闻资讯"),
    CONTACT_US("contactUs", "联系我们"),
    ;

    ThreeElementsOfColumnPageSeoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Getter
    private String code;

    @Getter
    private String message;
}
