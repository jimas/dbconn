package com.jimas.dbconn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicAction {
    
    @RequestMapping("/basic")
    public String hello(ModelMap map,HttpServletRequest request, HttpServletResponse response) {
        
        return "basic";  
    }

}
