package com.jimas.dbconn.service;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimas.common.ResultVo;
import com.jimas.common.util.GsonUtil;
import com.jimas.dbconn.api.LogApi;
import com.jimas.dbconn.http.RestService;
import com.jimas.dbconn.pojo.rest.LogPojo;
import com.jimas.dbconn.urlenum.UrlEnum;

@Service
public class LogService implements LogApi {
    private static final Logger logger=Logger.getLogger(LogService.class);
    @Autowired
    private RestService restService;
    @SuppressWarnings("unchecked")
    @Override
    public ResultVo<LogPojo> insertLog(LogPojo logPojo) {
        try {
            String json=restService.postHttp(UrlEnum.LOG_INSERT, logPojo, logPojo.getSiteSource());
            
            if(!StringUtils.isEmpty(json)){
                return  (ResultVo<LogPojo>) GsonUtil.parseJson(json, ResultVo.class);
            }
        } catch (Exception e) {
            logger.error("LogService.insertLog error", e);
        }
        return null;
    }

}
