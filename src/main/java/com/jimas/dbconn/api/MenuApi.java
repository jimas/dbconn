package com.jimas.dbconn.api;

import com.jimas.dbconn.pojo.rest.MenuPojo;

public interface MenuApi {
    
    public MenuPojo queryMenuPojoBySiteSource(String siteSource);

}
