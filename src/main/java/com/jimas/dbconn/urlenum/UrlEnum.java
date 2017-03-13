package com.jimas.dbconn.urlenum;


public enum UrlEnum {
    
    MENU_QUERY("menu/queryMenu"),
    MENU_SAVE("menu/saveMenu");
    
    private UrlEnum(String url){
        this.url=url;
    };
    
    private String url;

    public String getUrl() {
        return url;
    }

}
