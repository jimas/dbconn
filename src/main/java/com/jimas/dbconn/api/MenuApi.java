package com.jimas.dbconn.api;

import com.jimas.dbconn.pojo.rest.Menu;
import com.jimas.dbconn.pojo.rest.MenuPojo;

public interface MenuApi {
    
    public MenuPojo<Menu> queryMenuPojoBySiteSource(String siteSource);

}
