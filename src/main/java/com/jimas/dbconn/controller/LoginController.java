package com.jimas.dbconn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jimas.dbconn.interceptor.MenuModel;

/**
 * @Description 登录页面
 * @author weqinjia.liu
 * @Date 2017年3月22日
 */
@Controller
public class LoginController extends BaseController {
    @MenuModel
    @RequestMapping("/login")
    public String login(HttpServletRequest request,HttpServletResponse response,ModelMap map){
        return "login";
    }
    
    @MenuModel
    @RequestMapping("/register")
    public String register(HttpServletRequest request,HttpServletResponse response,ModelMap map){
        return "register";
    }
}
