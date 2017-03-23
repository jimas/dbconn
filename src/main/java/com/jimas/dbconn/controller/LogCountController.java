package com.jimas.dbconn.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.jimas.dbconn.sourceconfig.ParamsConfig;

@Controller
@RequestMapping("/log")
public class LogCountController extends BaseController {

    @Autowired
    private LogApi logApi;
    @Autowired
    private ParamsConfig paramsConfig;

    @RequestMapping({"/logIp/{siteSource}","/logIp"})
    @MenuModel
    public String logIpCount(HttpServletRequest request, HttpServletResponse response, ModelMap map ,@PathVariable(required=false) String siteSource) {
        LogIpRq logIpRq = new LogIpRq();
        logIpRq.setSiteSource(siteSource);
        logIpRq.setRemoveIpList(Arrays.asList(paramsConfig.getExcludeIp()));
        logIpRq.setStart(DateUtils.addDays(new Date(), -6));
        logIpRq.setEnd(new Date());
        ResultVo<List<LogIpCountRs>> dbconnRs = logApi.logSiteIpCount(logIpRq);
        List<LogIpCountRs> result = dbconnRs.getResult();
                result.sort(new Comparator<LogIpCountRs>() {
                    @Override
                    public int compare(LogIpCountRs o1, LogIpCountRs o2) {
                        return o1.getOperateDate().before(o2.getOperateDate())?1:(o1.getOperateDate().equals(o2.getOperateDate())?0:-1);
                    }
                    
                });
        String[] legends=new String[result.size()];
        List<SeriesData> seriesDatas=new ArrayList<SeriesData>();
        int ii=0;
        for (LogIpCountRs logIpCountRs : result) {
            legends[ii++]=logIpCountRs.getId();
            SeriesData seriesData = new SeriesData();
            seriesData.setLegend(logIpCountRs.getId());
            String[] series=new String[]{logIpCountRs.getAccess_count()+""};
            seriesData.setSeries(series);
            seriesDatas.add(seriesData);
        }
        
        EChartPojo eChartPojo = new EChartPojo();
        eChartPojo.setLegends(legends);
        eChartPojo.setSeriesDatas(seriesDatas);
        map.put("eChartPojo", eChartPojo);
        map.put("siteSource", siteSource);
        return "pages/logIp";
    }

    @RequestMapping("/logDay")
    @MenuModel
    public String logDayCount(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        LogStatisticsRq logStatisticsRq = new LogStatisticsRq();
        String[] legends = {"bootstrap" ,"dbconn"};
        int days = 5;
        logStatisticsRq.setDays(days);
        Date today=new Date();
        logStatisticsRq.setStartDate(DateUtils.addDays(today, -days));
        logStatisticsRq.setSiteSource(legends[0]);
        ResultVo<List<LogStatisticsRs>> bootstrap = logApi.logSiteCount(logStatisticsRq);// bootstrap  过去5天访问量
        logStatisticsRq.setSiteSource(legends[1]);
        ResultVo<List<LogStatisticsRs>> dbconn = logApi.logSiteCount(logStatisticsRq);// dbconn 过去5天访问量
        List<LogStatisticsRs> result = bootstrap.getResult();
        List<LogStatisticsRs> result2 = dbconn.getResult();
        
        LogStatisticsRq dayAccess=new LogStatisticsRq();
        ResultVo<List<LogStatisticsRs>> todayRs = logApi.countAccessByDay(dayAccess);
        List<LogStatisticsRs> todayLogList = todayRs.getResult();
        
        result.add(getDayLog(todayLogList,today,legends[0]));
        result2.add(getDayLog(todayLogList,today,legends[1]));
        
        Set<String> dayList = DateUtil.getDayList(DateUtils.addDays(logStatisticsRq.getStartDate(), 1), today);
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
    


    //查询今日动态访问量
    @RequestMapping("/logActiveDay")
    @MenuModel
    public String logOneDayCount(HttpServletRequest request, HttpServletResponse response, ModelMap map) {

        return "pages/activeLog";
    }

    @ResponseBody
    @RequestMapping(value="/activeCountLog",method = RequestMethod.POST)
    public EChartPojo activeCountLog(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        LogStatisticsRq logStatisticsRq = new LogStatisticsRq();
        Date today=new Date();
        logStatisticsRq.setStartDate(today);
        ResultVo<List<LogStatisticsRs>> rs = logApi.countAccessByDay(logStatisticsRq);
        
        List<LogStatisticsRs> list = rs.getResult();
        String[] legends =  new String[list.size()];
        int ii=0;
        List<SeriesData> seriesDatas=new ArrayList<SeriesData>();
        for (LogStatisticsRs logStatisticsRs : list) {
            legends[ii++]=logStatisticsRs.getSiteSource();
            SeriesData seriesData = new SeriesData();
            seriesData.setLegend(logStatisticsRs.getSiteSource());
            String[] series={logStatisticsRs.getAccessCount()+""};
            seriesData.setSeries(series);
            seriesDatas.add(seriesData);
        }
        String[] axisCategory = {DateUtil.getDateFormat(today)};
        EChartPojo eChartPojo = new EChartPojo();
        eChartPojo.setAxisCategory(axisCategory);
        eChartPojo.setLegends(legends);
        eChartPojo.setSeriesDatas(seriesDatas);
        return eChartPojo;
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
    private LogStatisticsRs getDayLog(List<LogStatisticsRs> todayLogList, Date today, String siteSource) {
        for (LogStatisticsRs logStatisticsRs : todayLogList) {
            if(logStatisticsRs.getSiteSource().equals(siteSource)){
                logStatisticsRs.setOperateDate(today);
                return logStatisticsRs;
            }
        }
        return null;
    }
}
