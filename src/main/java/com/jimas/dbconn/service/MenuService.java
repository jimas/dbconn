package com.jimas.dbconn.service;

import java.lang.reflect.Type;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.jimas.dbconn.api.MenuApi;
import com.jimas.dbconn.http.RestService;
import com.jimas.dbconn.pojo.rest.BaseKeyReq;
import com.jimas.dbconn.pojo.rest.Menu;
import com.jimas.dbconn.pojo.rest.MenuPojo;
import com.jimas.dbconn.pojo.rest.ResultVo;
import com.jimas.dbconn.urlenum.UrlEnum;
import com.jimas.dbconn.util.GsonUtil;
@Service("MenuService")
public class MenuService implements MenuApi {

    @Autowired
    private RestService restService;
    @SuppressWarnings("unchecked")
    @Override
    public MenuPojo<Menu> queryMenuPojoBySiteSource(String siteSource) {
        BaseKeyReq<String> baseKeyReq = new BaseKeyReq<String>();
        baseKeyReq.setSiteSource(siteSource);
        String postHttp = restService.postHttp(UrlEnum.MENU_QUERY, baseKeyReq, siteSource);
        if(!StringUtils.isEmpty(postHttp)){
            Type type = new TypeToken<ResultVo<MenuPojo<Menu>>>(){}.getType();
            ResultVo<MenuPojo<Menu>> resultVo = (ResultVo<MenuPojo<Menu>>) GsonUtil.parseJson(postHttp, type);
            if(resultVo.getStatus()==200){
                return  (MenuPojo<Menu>) resultVo.getResult();
            }
            
        }
        return new MenuPojo<Menu>();
    }

}
