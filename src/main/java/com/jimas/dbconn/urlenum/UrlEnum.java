package com.jimas.dbconn.urlenum;

/**
 * @Description restful 接口 枚举
 * @author weqinjia.liu
 * @Date 2017年3月14日
 */
public enum UrlEnum {
    
    MENU_QUERY("menu/queryMenu"),
    MENU_SAVE("menu/saveMenu"),
    LOG_INSERT("webLog/insertLog");
    
    private UrlEnum(String url){
        this.url=url;
    };
    
    private String url;

    public String getUrl() {
        return url;
    }

}
