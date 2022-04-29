package com.qiuwanchi.seo.constant;

import lombok.Getter;
import lombok.Setter;

public enum CategoryCode {

    FAQ("cjwd", "常见问答"),
    popular_science_knowledge("kpzs", "科普知识");

    CategoryCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Getter
    private String code;

    @Getter
    private String message;
}
