package com.jimas.dbconn.service;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimas.dbconn.api.MenuApi;
import com.jimas.dbconn.http.RestService;
import com.jimas.dbconn.pojo.rest.BaseKeyReq;
import com.jimas.dbconn.pojo.rest.MenuPojo;
import com.jimas.dbconn.pojo.rest.ResultVo;
import com.jimas.dbconn.urlenum.UrlEnum;
import com.jimas.dbconn.util.GsonUtil;
@Service("MenuService")
public class MenuService implements MenuApi {

    @Autowired
    private RestService restService;
    @Override
    public MenuPojo queryMenuPojoBySiteSource(String siteSource) {
        BaseKeyReq<String> baseKeyReq = new BaseKeyReq<String>();
        baseKeyReq.setSiteSource(siteSource);
        String postHttp = restService.postHttp(UrlEnum.MENU_QUERY, baseKeyReq, siteSource);
        if(!StringUtils.isEmpty(postHttp)){
            ResultVo resultVo = (ResultVo) GsonUtil.parseJson(postHttp, ResultVo.class);
            if(resultVo.getStatus()==200){
                return (MenuPojo) resultVo.getResult();
            }
            
        }
        return null;
    }

}
