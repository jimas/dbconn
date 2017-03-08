package com.jimas.dbconn.controller.tools;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jimas.dbconn.controller.BaseController;
import com.jimas.dbconn.util.DateUtil;
@Controller
@RequestMapping("/date")
public class DateController extends BaseController {
    
    @RequestMapping("/toolsDate")
    public String toolsIndex(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Date date = new Date();
        String now = DateUtil.format(date,DateUtil.DATE_TIME_FORMAT);
        map.put("times_tamp",date.getTime());
        map.put("now", now);
        return "pages/date";
    }
    
    
}
