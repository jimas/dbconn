package com.jimas.dbconn.pojo.rest;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 菜单详情
 * @author weqinjia.liu
 * @Date 2017年3月10日
 */
public class Menu implements Serializable{

    private static final long serialVersionUID = -8458994568025025268L;

    private String menuName;
    private String menuCode;
    private String parentCode;//父级code
    private String ancestorCode;//顶级Code
    private String menuUrl;//
    private String sortStr;//排序
    private String icon;//菜单icon
    private Integer level;//菜单等级
    
    private List<Menu> subMenuList;
    
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getMenuCode() {
        return menuCode;
    }
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    public String getParentCode() {
        return parentCode;
    }
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    public String getAncestorCode() {
        return ancestorCode;
    }
    public void setAncestorCode(String ancestorCode) {
        this.ancestorCode = ancestorCode;
    }
    public String getMenuUrl() {
        return menuUrl;
    }
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    public String getSortStr() {
        return sortStr;
    }
    public void setSortStr(String sortStr) {
        this.sortStr = sortStr;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
    public List<Menu> getSubMenuList() {
        return subMenuList;
    }
    public void setSubMenuList(List<Menu> subMenuList) {
        this.subMenuList = subMenuList;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu [");
        if (menuName != null) builder.append("menuName=").append(menuName).append(", ");
        if (menuCode != null) builder.append("menuCode=").append(menuCode).append(", ");
        if (parentCode != null) builder.append("parentCode=").append(parentCode).append(", ");
        if (ancestorCode != null) builder.append("ancestorCode=").append(ancestorCode).append(", ");
        if (menuUrl != null) builder.append("menuUrl=").append(menuUrl).append(", ");
        if (sortStr != null) builder.append("sortStr=").append(sortStr).append(", ");
        if (icon != null) builder.append("icon=").append(icon).append(", ");
        if (level != null) builder.append("level=").append(level).append(", ");
        if (subMenuList != null) builder.append("subMenuList=").append(subMenuList);
        builder.append("]");
        return builder.toString();
    }
    
    
    

}
