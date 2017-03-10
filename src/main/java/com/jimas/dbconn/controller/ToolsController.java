package com.jimas.dbconn.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jimas.dbconn.util.DateUtil;

@Controller
public class ToolsController extends BaseController {

    @RequestMapping("word")
    public String wordCount(HttpServletRequest request,HttpServletResponse response,ModelMap map){
        
        return "pages/word";
    }
    @RequestMapping("json")
    public String jsonFormat(HttpServletRequest request,HttpServletResponse response,ModelMap map){
        
        return "pages/json";
    }
    @RequestMapping("/date")
    public String toolsIndex(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Date date = new Date();
        String now = DateUtil.format(date,DateUtil.DATE_TIME_FORMAT);
        map.put("times_tamp",date.getTime()/1000);// 秒级别
        map.put("now", now);
        return "pages/date";
    }
}
