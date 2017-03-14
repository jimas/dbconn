package com.jimas.dbconn.service;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimas.dbconn.api.LogApi;
import com.jimas.dbconn.http.RestService;
import com.jimas.dbconn.pojo.rest.LogPojo;
import com.jimas.dbconn.pojo.rest.ResultVo;
import com.jimas.dbconn.urlenum.UrlEnum;
import com.jimas.dbconn.util.GsonUtil;

@Service
public class LogService implements LogApi {
    @Autowired
    private RestService restService;
    @Override
    public ResultVo insertLog(LogPojo logPojo) {
        
        String json=restService.postHttp(UrlEnum.LOG_INSERT, logPojo, logPojo.getSiteSource());
        
        if(!StringUtils.isEmpty(json)){
            return  (ResultVo) GsonUtil.parseJson(json, ResultVo.class);
        }
        return null;
    }

}
