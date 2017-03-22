package com.jimas.dbconn.constant;

/**
 * @Description restful 接口 枚举
 * @author weqinjia.liu
 * @Date 2017年3月14日
 */
public enum UrlEnum {
    
    MENU_QUERY("menu/queryMenu"),
    MENU_SAVE("menu/saveMenu"),
    LOG_INSERT("webLog/insertLog"),
    LOG_SITE_COUNT("webLog/countAccessBysiteSource"),
    LOG_SITE_IP_COUNT("webLog/ipCountAccessBysiteSource"),
    LOG_SITE_URL_IP_COUNT("webLog/ipCountAccessByUrlsiteSource"),
    LOG_DAY_COUNT("webLog/countAccessByDay"),
    LOG_SITE_URL_COUNT("webLog/countAccessByUrlsiteSource");
    
    private UrlEnum(String url){
        this.url=url;
    };
    
    private String url;

    public String getUrl() {
        return url;
    }

}
