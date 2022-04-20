package com.qiuwanchi.seo.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServiceCaseGeneratePageUtil {

    private static ServerConfig serverConfig;
    @Autowired
    private ServerConfig serverConfig2;

    @PostConstruct
    public void init(){
        serverConfig = serverConfig2;
    }

    public static String generatePageHtml(Page page, String firstCategory, String secondCategory){
        long currentPage = page.getCurrent();
        long totalPages = page.getPages();
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(getPrePageHtml(currentPage, firstCategory, secondCategory));
        stringBuffer.append(getIndexPageHtml(currentPage, firstCategory, secondCategory));

        for(long i = 1; i <= totalPages; i++){
            stringBuffer.append("<li");
            if (currentPage == i){
                stringBuffer.append(" class=\"page_active\"");
            }
            stringBuffer.append(">");
            stringBuffer.append(getALabelLeft(i, firstCategory, secondCategory));
            stringBuffer.append(i);
            stringBuffer.append(getALabelRight());

            stringBuffer.append("</li>");
        }

        stringBuffer.append(getLastPageHtml(currentPage, totalPages, firstCategory, secondCategory));
        stringBuffer.append(getNextPageHtml(currentPage, totalPages, firstCategory, secondCategory));
        return stringBuffer.toString();
    }

    /**
     * 获取上一页html
     * @param currentPage
     * @param firstCategory
     * @param secondCategory
     * @return
     */
    private static String getPrePageHtml(long currentPage, String firstCategory, String secondCategory){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<li");
        if(currentPage <= 1){
            stringBuffer.append(" class=\"noclick\"");
        }
        stringBuffer.append(">");

        if(currentPage != 1 && currentPage > 1){
            stringBuffer.append(getALabelLeft(currentPage - 1, firstCategory, secondCategory));
        }else{
            stringBuffer.append("<a href=\"javascript:;\">");
        }
        stringBuffer.append("上一页");

        stringBuffer.append(getALabelRight());
        stringBuffer.append("</li>");
        return stringBuffer.toString();
    }

    /**
     * 获取下一页html
     * @param currentPage
     * @param firstCategory
     * @param secondCategory
     * @return
     */
    private static String getNextPageHtml(long currentPage, long totalPages, String firstCategory, String secondCategory){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<li");
        if(currentPage >= totalPages){
            stringBuffer.append(" class=\"noclick\"");
        }
        stringBuffer.append(">");

        if(currentPage != totalPages && currentPage < totalPages){
            stringBuffer.append(getALabelLeft(currentPage + 1, firstCategory, secondCategory));
        }else{
            stringBuffer.append("<a href=\"javascript:;\">");
        }
        stringBuffer.append("下一页");
        stringBuffer.append(getALabelRight());
        stringBuffer.append("</li>");
        return stringBuffer.toString();
    }

    /**
     * 获取首页html
     * @param currentPage
     * @param firstCategory
     * @param secondCategory
     * @return
     */
    private static String   getIndexPageHtml(long currentPage, String firstCategory, String secondCategory){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<li");
        if(currentPage <= 1){
            stringBuffer.append(" class=\"noclick\"");
        }
        stringBuffer.append(">");

        if(currentPage != 1 && currentPage > 1){
            stringBuffer.append(getALabelLeft(1, firstCategory, secondCategory));
        }else{
            stringBuffer.append("<a href=\"javascript:;\">");
        }
        stringBuffer.append("首页");
        stringBuffer.append(getALabelRight());

        stringBuffer.append("</li>");
        return stringBuffer.toString();
    }

    /**
     * 获取首页html
     * @param currentPage
     * @param firstCategory
     * @param secondCategory
     * @return
     */
    private static String getLastPageHtml(long currentPage, long totalPages, String firstCategory, String secondCategory){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<li");
        if(currentPage >= totalPages){
            stringBuffer.append(" class=\"noclick\"");
        }
        stringBuffer.append(">");

        if(currentPage != totalPages && currentPage < totalPages){
            stringBuffer.append(getALabelLeft(totalPages, firstCategory, secondCategory));
        }else{
            stringBuffer.append("<a href=\"javascript:;\">");
        }
        stringBuffer.append("尾页");
        stringBuffer.append(getALabelRight());
        stringBuffer.append("</li>");

        return stringBuffer.toString();
    }

    /**
     * 获取a标签的左边
     * @param currentPage 第几页
     * @param firstCategory 一级类目
     * @param secondCategory 二级类目
     * @return
     */
    private static String getALabelLeft(long currentPage, String firstCategory, String secondCategory){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<a href=\"")
                .append(getJumpUrl(currentPage, firstCategory, secondCategory))
                .append("\">");
        return stringBuffer.toString();
    }

    /**
     * 获取a标签的右边
     * @return
     */
    private static String getALabelRight(){
        return "</a>";
    }

    private static String getJumpUrl(long currentPage, String firstCategory, String secondCategory){
        StringBuffer sb = new StringBuffer();
        sb.append(serverConfig.getUrl()).append("/");
        if(StringUtils.isNotBlank(firstCategory) && StringUtils.isNotBlank(secondCategory)){
            sb.append("serviceCase/").append(firstCategory).append("/").append(secondCategory).append("/page_").append(currentPage).append(".html");
        }else if(StringUtils.isNotBlank(firstCategory)){
            sb.append("serviceCase/").append(firstCategory).append("/page_").append(currentPage).append(".html");
        }else {
            sb.append("serviceCase/page_").append(currentPage).append(".html");
        }
        return sb.toString();
    }
}
