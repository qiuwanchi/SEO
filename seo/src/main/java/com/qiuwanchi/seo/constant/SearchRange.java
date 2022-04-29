package com.qiuwanchi.seo.constant;

import lombok.Getter;
import lombok.Setter;

public enum SearchRange {

    NEWS("News", "新闻资讯"),
    SOLUTION_SERVICE_CASE("solution_service_case", "解决方案与服务案例");

    SearchRange(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Getter
    private String code;

    @Getter
    private String message;
}
