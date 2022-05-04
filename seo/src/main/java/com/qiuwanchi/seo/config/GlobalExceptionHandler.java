package com.qiuwanchi.seo.config;

import com.qiuwanchi.seo.utils.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ServerConfig serverConfig;

    public static final String ERROR_VIEW = "404";

    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("baseUrl", serverConfig.getUrl());
        mav.setViewName(ERROR_VIEW);
        return mav;
    }
}