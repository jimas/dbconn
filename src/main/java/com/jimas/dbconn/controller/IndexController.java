package com.jimas.dbconn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jimas.dbconn.interceptor.MenuModel;

@Controller
public class IndexController extends BaseController {

    @RequestMapping({"","/"})
    @MenuModel
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        
        return "index";
    }
    
    
    @RequestMapping("basic")
    public String basic(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        
        return "basic";
    }
}
