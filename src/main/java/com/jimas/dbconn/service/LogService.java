package com.jimas.dbconn.service;

import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimas.common.ResultVo;
import com.jimas.common.util.GsonUtil;
import com.jimas.dbconn.api.LogApi;
import com.jimas.dbconn.api.request.LogIpRq;
import com.jimas.dbconn.api.request.LogStatisticsRq;
import com.jimas.dbconn.api.request.LogUrlRq;
import com.jimas.dbconn.api.response.LogIpCountRs;
import com.jimas.dbconn.api.response.LogStatisticsRs;
import com.jimas.dbconn.api.response.LogUrlCountRs;
import com.jimas.dbconn.api.response.LogUrlStatisticsRs;
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
        return new ResultVo<LogPojo>(400," insertLog error ",null);
    }
    @SuppressWarnings("unchecked")
    @Override
    public ResultVo<List<LogStatisticsRs>> logSiteCount(LogStatisticsRq logStatisticsRq) {
        try {
            String json=restService.postHttp(UrlEnum.LOG_SITE_COUNT, logStatisticsRq, logStatisticsRq.getSiteSource());
            
            if(!StringUtils.isEmpty(json)){
                return  (ResultVo<List<LogStatisticsRs>>) GsonUtil.parseJson(json, ResultVo.class);
            }
        } catch (Exception e) {
            logger.error("LogService.logSiteCount error", e);
        }
        return new ResultVo<List<LogStatisticsRs>>(400," logSiteCount error ",null);
    }
    @SuppressWarnings("unchecked")
    @Override
    public ResultVo<List<LogIpCountRs>> logSiteIpCount(LogIpRq logIpRq) {
        try {
            String json=restService.postHttp(UrlEnum.LOG_SITE_IP_COUNT, logIpRq, logIpRq.getSiteSource());
            
            if(!StringUtils.isEmpty(json)){
                return  (ResultVo<List<LogIpCountRs>>) GsonUtil.parseJson(json, ResultVo.class);
            }
        } catch (Exception e) {
            logger.error("LogService.logSiteIpCount error", e);
        }
        return new ResultVo<List<LogIpCountRs>>(400," logSiteIpCount error ",null);
    }
    @SuppressWarnings("unchecked")
    @Override
    public ResultVo<List<LogUrlCountRs>> logSiteUrlIpCount(LogUrlRq logUrlRq) {
        try {
            String json=restService.postHttp(UrlEnum.LOG_SITE_URL_IP_COUNT, logUrlRq, logUrlRq.getSiteSource());
            
            if(!StringUtils.isEmpty(json)){
                return  (ResultVo<List<LogUrlCountRs>>) GsonUtil.parseJson(json, ResultVo.class);
            }
        } catch (Exception e) {
            logger.error("LogService.logSiteUrlIpCount error", e);
        }
        return new ResultVo<List<LogUrlCountRs>>(400," logSiteUrlIpCount error ",null);
    }
    @SuppressWarnings("unchecked")
    @Override
    public ResultVo<List<LogUrlStatisticsRs>> logSiteUrlCount(LogStatisticsRq logStatisticsRq) {
        try {
            String json=restService.postHttp(UrlEnum.LOG_SITE_URL_COUNT, logStatisticsRq, logStatisticsRq.getSiteSource());
            
            if(!StringUtils.isEmpty(json)){
                return  (ResultVo<List<LogUrlStatisticsRs>>) GsonUtil.parseJson(json, ResultVo.class);
            }
        } catch (Exception e) {
            logger.error("LogService.logSiteUrlIpCount error", e);
        }
        return new ResultVo<List<LogUrlStatisticsRs>>(400," logSiteUrlIpCount error ",null);
    }

}
