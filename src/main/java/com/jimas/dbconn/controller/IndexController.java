package com.jimas.dbconn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jimas.common.ResultVo;
import com.jimas.dbconn.api.LogApi;
import com.jimas.dbconn.api.request.LogStatisticsRq;
import com.jimas.dbconn.api.response.LogStatisticsRs;
import com.jimas.dbconn.interceptor.MenuModel;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private LogApi logApi;
    
    @RequestMapping({"","/"})
    @MenuModel
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        LogStatisticsRq logStatisticsRq=new LogStatisticsRq();
        ResultVo<List<LogStatisticsRs>> rs = logApi.logSiteCount(logStatisticsRq);
        
        map.put("logStatRsList", rs.getResult());
        
        return "index";
    }
    
    
    @RequestMapping("basic")
    public String basic(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        
        return "basic";
    }
}
