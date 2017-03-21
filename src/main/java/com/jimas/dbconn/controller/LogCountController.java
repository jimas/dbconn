package com.jimas.dbconn.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jimas.common.ResultVo;
import com.jimas.common.util.DateUtil;
import com.jimas.dbconn.api.LogApi;
import com.jimas.dbconn.api.request.LogIpRq;
import com.jimas.dbconn.api.request.LogStatisticsRq;
import com.jimas.dbconn.api.response.LogIpCountRs;
import com.jimas.dbconn.api.response.LogStatisticsRs;
import com.jimas.dbconn.interceptor.MenuModel;
import com.jimas.dbconn.pojo.EChartPojo;
import com.jimas.dbconn.pojo.SeriesData;

@Controller
@RequestMapping("/log")
public class LogCountController extends BaseController {

    @Autowired
    private LogApi logApi;

    public String logCount(HttpServletRequest request, HttpServletResponse response, ModelMap map, String logAction) {

        LogIpRq logIpRq = new LogIpRq();
        logIpRq.setSiteSource(logAction);
        ResultVo<List<LogIpCountRs>> rs = logApi.logSiteIpCount(logIpRq);
        map.put("logIpRsList", rs.getResult());
        return "pages/log";
    }

    @RequestMapping("/logDay")
    @MenuModel
    public String logDayCount(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        LogStatisticsRq logStatisticsRq = new LogStatisticsRq();
        String[] legends = {"bootstrap" ,"dbconn"};
        int days = 5;
        logStatisticsRq.setDays(days);
        Date yesterday = DateUtils.addDays(new Date(), -1);
        logStatisticsRq.setStartDate(DateUtils.addDays(yesterday, -days));
        logStatisticsRq.setSiteSource(legends[0]);
        ResultVo<List<LogStatisticsRs>> bootstrap = logApi.logSiteCount(logStatisticsRq);
        logStatisticsRq.setSiteSource(legends[1]);
        ResultVo<List<LogStatisticsRs>> dbconn = logApi.logSiteCount(logStatisticsRq);
        List<LogStatisticsRs> result = bootstrap.getResult();
        List<LogStatisticsRs> result2 = dbconn.getResult();

        Set<String> dayList = DateUtil.getDayList(DateUtils.addDays(logStatisticsRq.getStartDate(), 1), yesterday);
        String[] axisCategory = new String[dayList.size()];
        EChartPojo eChartPojo = new EChartPojo();
        int i = 0;
        for (String string : dayList) {
            axisCategory[i++] = string;
        }
        eChartPojo.setAxisCategory(axisCategory);
        eChartPojo.setLegends(legends);
        List<SeriesData> seriesDatas = new ArrayList<SeriesData>();

        SeriesData seriesData = new SeriesData();
        seriesData.setLegend(legends[0]);
        String[] series = convertSeries(result, axisCategory);
        seriesData.setSeries(series);
        seriesDatas.add(seriesData);

        SeriesData seriesData2 = new SeriesData();
        seriesData2.setLegend(legends[1]);
        String[] series2 = convertSeries(result2, axisCategory);
        seriesData2.setSeries(series2);
        seriesDatas.add(seriesData2);

        eChartPojo.setSeriesDatas(seriesDatas);
        map.put("eChartPojo", eChartPojo);

        return "pages/log";
    }

    private String[] convertSeries(List<LogStatisticsRs> result, String[] axisCategory) {

        ArrayList<String> seriesList = new ArrayList<String>();

        for (int i = 0; i < axisCategory.length ; i++) {
            int f=0;
            for (LogStatisticsRs logStatisticsRs : result) {
                if (axisCategory[i].equals(DateUtil.getDateFormat(logStatisticsRs.getOperateDate()))) {
                    seriesList.add(logStatisticsRs.getAccessCount() + "");
                    continue;
                }else{
                    f++;
                }
            }
            if(f==result.size()){
                seriesList.add("0");
            }
        }
        String[] series = new String[seriesList.size()];
        int j = 0;
        for (String string : seriesList) {
            series[j++] = string;
        }
        return series;
    }

}
