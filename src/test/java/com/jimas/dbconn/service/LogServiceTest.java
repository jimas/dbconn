package com.jimas.dbconn.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jimas.common.ResultVo;
import com.jimas.dbconn.api.LogApi;
import com.jimas.dbconn.api.request.LogIpRq;
import com.jimas.dbconn.api.request.LogStatisticsRq;
import com.jimas.dbconn.api.request.LogUrlRq;
import com.jimas.dbconn.api.response.LogIpCountRs;
import com.jimas.dbconn.api.response.LogStatisticsRs;
import com.jimas.dbconn.api.response.LogUrlCountRs;
import com.jimas.dbconn.api.response.LogUrlStatisticsRs;

public class LogServiceTest extends BaseTest {

    @Autowired
    private LogApi LogService;
    @Test
    public void testInsertLog() {
    }

    @Test
    public void testLogSiteCount() {
        LogStatisticsRq logStatisticsRq=new LogStatisticsRq();
        ResultVo<List<LogStatisticsRs>> rs = LogService.logSiteCount(logStatisticsRq);
        System.out.println(rs);
    }

    @Test
    public void testLogSiteIpCount() {
        LogIpRq logIpRq=new LogIpRq();
        ResultVo<List<LogIpCountRs>> rs = LogService.logSiteIpCount(logIpRq);
        System.out.println(rs);
    }

    @Test
    public void testLogSiteUrlIpCount() {
        LogUrlRq logUrlRq=new LogUrlRq();
        ResultVo<List<LogUrlCountRs>> rs = LogService.logSiteUrlIpCount(logUrlRq);
        System.out.println(rs);
    }

    @Test
    public void testLogSiteUrlCount() {
        LogStatisticsRq logStatisticsRq=new LogStatisticsRq();
        ResultVo<List<LogUrlStatisticsRs>> rs = LogService.logSiteUrlCount(logStatisticsRq);
        System.out.println(rs);
    }
    @Test
    public void testCountAccessByDay() {
        LogStatisticsRq logStatisticsRq=new LogStatisticsRq();
        ResultVo<List<LogStatisticsRs>> rs = LogService.countAccessByDay(logStatisticsRq);
        System.out.println(rs);
    }
}
