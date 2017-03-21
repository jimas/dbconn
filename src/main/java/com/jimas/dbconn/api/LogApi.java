package com.jimas.dbconn.api;

import java.util.List;

import com.jimas.common.ResultVo;
import com.jimas.dbconn.api.request.LogIpRq;
import com.jimas.dbconn.api.request.LogStatisticsRq;
import com.jimas.dbconn.api.request.LogUrlRq;
import com.jimas.dbconn.api.response.LogIpCountRs;
import com.jimas.dbconn.api.response.LogStatisticsRs;
import com.jimas.dbconn.api.response.LogUrlCountRs;
import com.jimas.dbconn.api.response.LogUrlStatisticsRs;
import com.jimas.dbconn.pojo.rest.LogPojo;

public interface LogApi {
    
    public ResultVo<LogPojo> insertLog(LogPojo logPojo);
    /**
     * 系统每日访问量统计
     * @param logStatisticsRq
     * @return
     */
    public ResultVo<List<LogStatisticsRs>> countAccessByDay(LogStatisticsRq logStatisticsRq);
    /**
     * 系统访问量统计
     * @param logStatisticsRq
     * @return
     */
    public ResultVo<List<LogStatisticsRs>> logSiteCount(LogStatisticsRq logStatisticsRq);
    /**
     * 系统ip访问量统计
     * @param logIpRq
     * @return
     */
    public ResultVo<List<LogIpCountRs>> logSiteIpCount(LogIpRq logIpRq);
    /**
     * 系统url ip访问量统计
     * @param logUrlRq
     * @return
     */
    public ResultVo<List<LogUrlCountRs>> logSiteUrlIpCount(LogUrlRq logUrlRq);
    /**
     * 系统url 访问量统计
     * @param logStatisticsRq
     * @return
     */
    public ResultVo<List<LogUrlStatisticsRs>> logSiteUrlCount(LogStatisticsRq logStatisticsRq);
}
