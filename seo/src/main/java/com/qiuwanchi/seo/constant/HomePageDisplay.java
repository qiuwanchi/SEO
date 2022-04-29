package com.qiuwanchi.seo.constant;

import lombok.Getter;
import lombok.Setter;

public enum HomePageDisplay {

    DISPLAY("1", "首页显示");

    HomePageDisplay(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Getter
    private String code;

    @Getter
    private String message;

}
