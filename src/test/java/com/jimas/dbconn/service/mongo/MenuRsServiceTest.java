package com.jimas.dbconn.service.mongo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.reflect.TypeToken;
import com.jimas.common.ResultVo;
import com.jimas.common.util.GsonUtil;
import com.jimas.dbconn.constant.UrlEnum;
import com.jimas.dbconn.http.RestService;
import com.jimas.dbconn.pojo.rest.BaseKeyReq;
import com.jimas.dbconn.pojo.rest.Menu;
import com.jimas.dbconn.pojo.rest.MenuPojo;
import com.jimas.dbconn.service.BaseTest;

public class MenuRsServiceTest extends BaseTest {

    @Autowired
    private RestService restService;
    
    @Test
    public void testSaveMenu() {
        
        MenuPojo<Menu> rs=new MenuPojo<Menu>();
        rs.setSiteSource("dbconn");
        List<Menu> menuList=new ArrayList<Menu>();
        
        Menu home_menu = new Menu();
        home_menu.setLevel(1);
        home_menu.setMenuCode("HOME");
        home_menu.setMenuName("HOME");
        home_menu.setAncestorCode("HOME");
        home_menu.setParentCode("HOME");
        home_menu.setMenuUrl("/");
        home_menu.setSortStr("A");
        
        Menu tools_menu = new Menu();
        tools_menu.setLevel(1);
        tools_menu.setMenuCode("TOOLS");
        tools_menu.setAncestorCode("TOOLS");
        tools_menu.setParentCode("TOOLS");
        tools_menu.setMenuName("工具栏");
        tools_menu.setMenuUrl("#");
        tools_menu.setSortStr("C");
        
        List<Menu> subList=new ArrayList<Menu>();
        
        Menu json_menu = new Menu();
        json_menu.setLevel(2);
        json_menu.setMenuCode("JSON");
        json_menu.setAncestorCode("TOOLS");
        json_menu.setParentCode("TOOLS");
        json_menu.setMenuName("JSON格式化");
        json_menu.setMenuUrl("/json");
        json_menu.setSortStr("C1");
        subList.add(json_menu);
        
        Menu date_menu = new Menu();
        date_menu.setLevel(2);
        date_menu.setMenuCode("DATE");
        date_menu.setAncestorCode("TOOLS");
        date_menu.setParentCode("TOOLS");
        date_menu.setMenuName("日期格式化");
        date_menu.setMenuUrl("/date");
        date_menu.setSortStr("C2");
        subList.add(date_menu);
        
        Menu word_menu = new Menu();
        word_menu.setLevel(2);
        word_menu.setMenuCode("WORD");
        word_menu.setAncestorCode("TOOLS");
        word_menu.setParentCode("TOOLS");
        word_menu.setMenuName("在线字数统计");
        word_menu.setMenuUrl("/word");
        word_menu.setSortStr("C3");
        subList.add(word_menu);
        
        
        tools_menu.setSubMenuList(subList);//该siteSource tools_menu 一级菜单 下的二级菜单
        menuList.add(tools_menu);//该siteSource 某个一级菜单
        menuList.add(home_menu);//该siteSource 某个一级菜单
        rs.setMenuList(menuList);// 该siteSource 全站菜单
        String postHttp = restService.postHttp(UrlEnum.MENU_SAVE, rs,rs.getSiteSource());
        System.out.println(postHttp);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetMenuRsBySiteSource() {
        String siteSource="bootstrap";
        BaseKeyReq<String> baseKeyReq = new BaseKeyReq<String>();
        baseKeyReq.setSiteSource(siteSource);
        String json = restService.postHttp(UrlEnum.MENU_QUERY, baseKeyReq,siteSource);
        Type type = new TypeToken<ResultVo<MenuPojo<Menu>>>(){}.getType();
        ResultVo<MenuPojo<Menu>> resultVo = (ResultVo<MenuPojo<Menu>>) GsonUtil.parseJson(json, type);
        if(resultVo.getStatus()==200){
            MenuPojo<Menu> result = (MenuPojo<Menu>) resultVo.getResult();
            System.out.println(result);
        }
    }

}
