package com.jimas.dbconn.service.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimas.dbconn.dao.mongo.MenuRsRepository;
import com.jimas.dbconn.pojo.rest.MenuPojo;

@Service("MenuRsService")
public class MenuRsService {

    
    @Autowired
    private MenuRsRepository menuRsRepository;

    public void saveMenu(MenuPojo rs) {
        menuRsRepository.saveMenu(rs);
        
    }

    public MenuPojo getMenuRsBySiteSource(String siteSource) {
        return menuRsRepository.getMenuRsBySiteSource(siteSource);
    }


}
    
