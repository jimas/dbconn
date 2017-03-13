package com.jimas.dbconn.pojo.rest;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * @Description 菜单对象
 * @author weqinjia.liu
 * @Date 2017年3月10日
 */
@Document(collection = "jimasMenu")
public class MenuPojo implements Serializable{

    private static final long serialVersionUID = -8132722404639210032L;
    
    @Id
    private String siteSource;//来源系统
    
    private List<?> menuList;//菜单列表


    public String getSiteSource() {
        return siteSource;
    }

    public void setSiteSource(String siteSource) {
        this.siteSource = siteSource;
    }

    public List<?> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<?> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MenuPojo [");
        if (siteSource != null) builder.append("siteSource=").append(siteSource).append(", ");
        if (menuList != null) builder.append("menuList=").append(menuList);
        builder.append("]");
        return builder.toString();
    }


    
    
}
